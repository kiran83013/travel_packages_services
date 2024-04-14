package com.travel.travtronics.srm.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.travtronics.converter.AnxConverter;
import com.travel.travtronics.request.dto.AnxRequestLine;
import com.travel.travtronics.request.dto.PackageAnxDTO;
import com.travel.travtronics.request.dto.PaxRequestModel;
import com.travel.travtronics.request.dto.RequestLinePaxDto;
import com.travel.travtronics.srm.dao.AnxRequestDao;
import com.travel.travtronics.srm.dao.CommonAsyncDao;
import com.travel.travtronics.srm.model.HolidayRequestLine;
import com.travel.travtronics.srm.model.ServiceRequest;
import com.travel.travtronics.srm.model.SrAnxLinesModel;
import com.travel.travtronics.srm.model.SrAnxPax;
import com.travel.travtronics.srm.repository.HolidayRequestLineRepository;
import com.travel.travtronics.srm.repository.ServiceRequestLineRepository;
import com.travel.travtronics.srm.repository.ServiceRequestRepository;
import com.travel.travtronics.srm.repository.SrAnxAddonsRepository;
import com.travel.travtronics.srm.repository.SrAnxLinesRepository;
import com.travel.travtronics.srm.repository.SrAnxPaxRepository;

@Repository("anxRequestDao")
public class AnxRequestDaoImpl implements AnxRequestDao {

	@Autowired
	HolidayRequestLineRepository holidayRequestLineRepository;

	@Autowired
	CommonAsyncDao commonAsyncDao;

	@Autowired
	ServiceRequestLineRepository srLineRepository;

	@Autowired
	SrAnxLinesRepository srAnxLinesRepository;

	@Autowired
	SrAnxPaxRepository srAnxPaxRepository;

	@Autowired
	SrAnxAddonsRepository srAnxAddonsRepository;

	@Autowired
	ServiceRequestRepository srRepository;

	@Override
	public Map<String, Object> anxRequestFromPackage(PackageAnxDTO anxRequest, String token) throws Exception {
		AnxRequestLine requestData = anxRequest.getRequestData();

		if (requestData.getAnxLineRequestId() == null || requestData.getAnxLineRequestId() <= 0) {

			throw new Exception("LineRequestId should not be empty or zero");

		}

		if (requestData.getAnxLineTypeId() == null || requestData.getAnxLineTypeId() <= 0) {
			throw new Exception("LineTypeId should not be empty or zero");

		}

		if (requestData.getAnxLineType() == null || requestData.getAnxLineType().isBlank()) {
			throw new Exception("LineType should not be empty");

		}

		//if (requestData.getAnxLineJson() == null || requestData.getAnxLineJson().isEmpty()) {
			//throw new Exception("LineJson should not be empty");

		//}

		Optional<ServiceRequest> requestInfo = srRepository
				.findByRequestId(Long.valueOf(requestData.getAnxLineRequestId()));

		if (requestInfo.isPresent() == false) {

			throw new Exception("Invalid requestId.");

		}
		Long srLineId = srLineRepository.generateSeqenceNumber(requestData.getAnxLineRequestId().toString(), "77", 2);

		String UniqueId = String.valueOf(requestData.getAnxLineRequestId()) + "-" + String.valueOf(srLineId);
		// UUID.randomUUID().toString().replace("-", "");

		SrAnxLinesModel lineInfo = srAnxLinesRepository
				.save(AnxConverter.convertAnxRequestDataToAnxSrLineModel(UniqueId, srLineId, requestData));

		/*********** START Package line status Change *******************/
		if (lineInfo.getAnxLineRequestId() > 0) {
			Optional<HolidayRequestLine> packageLine = holidayRequestLineRepository
					.findByRequestIdAndRequestLineId(Long.valueOf(lineInfo.getAnxLineRequestId()), Long.valueOf(5501));
			if (packageLine.isPresent()) {
				packageLine.get().setLineStatusId(67); // 67 - WIP
				holidayRequestLineRepository.save(packageLine.get());
			}
		}
		/*********** END Package line status Change ********************/

		commonAsyncDao.saveSrSummaryData(lineInfo.getAnxLineRequestId(), lineInfo.getAnxLineId().intValue(), 3, 0,
				lineInfo.getAnxLineCreatedBy(), token);
		Map<String, Object> anxResponse = new LinkedHashMap<>();

		anxResponse.put("requestData", lineInfo);

		if (anxRequest.getPaxData() != null && anxRequest.getPaxData().getPaxData() != null
				&& !anxRequest.getPaxData().getPaxData().isEmpty()) {

			List<SrAnxPax> paxsavedData = createModifyRequestLinePax(anxRequest.getPaxData());
			anxResponse.put("paxData", paxsavedData);
		}

		return anxResponse;

	}

	public List<SrAnxPax> createModifyRequestLinePax(RequestLinePaxDto model) throws Exception {
		if (Objects.isNull(model.getCreatedBy())) {
			throw new Exception("createdBy should not be null.");

		}
		if (Objects.isNull(model.getRequestId())) {
			throw new Exception("requestId Should not be null.");

		}
		if (Objects.isNull(model.getRequestLineId())) {
			throw new Exception("requestLineId Should not be null");

		}
		if (Objects.isNull(model.getUpdatedBy())) {
			throw new Exception("updatedBy Should not be null");

		}

		List<PaxRequestModel> paxDtos = new ArrayList<>();
		for (PaxRequestModel pax : model.getPaxData()) {
			if (model.getCreatedBy() != null && model.getCreatedBy() != 0)
				pax.setCreatedBy(model.getCreatedBy());
			if (model.getUpdatedBy() != null && model.getUpdatedBy() != 0)
				pax.setUpdatedBy(model.getUpdatedBy());
			if (model.getRequestId() != null && model.getRequestId() != 0)
				pax.setRequestId(model.getRequestId());
			if (model.getRequestLineId() != null && model.getRequestLineId() != 0)
				pax.setRequestLineId(model.getRequestLineId());

			paxDtos.add(pax);
		}

		List<SrAnxPax> savedPaxInfo = paxDtos.stream().map(pax -> {

			Long requestLinePaxId = pax.getRequestLinePaxId() != null ? pax.getRequestLinePaxId() : 0L;
			Optional<SrAnxPax> validatepax = srAnxPaxRepository.findByRequestLinePaxId(requestLinePaxId);
			SrAnxPax paxServiceRequestLine = null;
			if (validatepax.isPresent()) {// update if present
				SrAnxPax save = srAnxPaxRepository.save(AnxConverter.updatePaxDtoToPaxModel(pax, validatepax.get()));
				paxServiceRequestLine = save;
			} else {// create new one
				SrAnxPax save = srAnxPaxRepository.save(AnxConverter.convertPaxDtoToPaxModel(pax));
				paxServiceRequestLine = save;
			}

			return paxServiceRequestLine;
		}).collect(Collectors.toList());

		return savedPaxInfo;
	}
}
