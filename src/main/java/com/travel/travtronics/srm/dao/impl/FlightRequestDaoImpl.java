package com.travel.travtronics.srm.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.travtronics.converter.PaxConverter;
import com.travel.travtronics.request.dto.PackageFlightDTO;
import com.travel.travtronics.request.dto.PaxRequestModel;
import com.travel.travtronics.request.dto.RequestLinePaxDto;
import com.travel.travtronics.request.dto.ServiceRequestLineModel;
import com.travel.travtronics.srm.dao.CommonAsyncDao;
import com.travel.travtronics.srm.dao.FlightRequestDao;
import com.travel.travtronics.srm.model.HolidayRequestLine;
import com.travel.travtronics.srm.model.PaxServiceRequestLine;
import com.travel.travtronics.srm.model.ServiceRequestLine;
import com.travel.travtronics.srm.model.ServiceRequestSegment;
import com.travel.travtronics.srm.repository.HolidayRequestLineRepository;
import com.travel.travtronics.srm.repository.PaxServiceRequestLineRepository;
import com.travel.travtronics.srm.repository.ServiceRequestLineRepository;
import com.travel.travtronics.srm.repository.ServiceRequestSegmentRepository;

@Repository("flightRequestDao")
public class FlightRequestDaoImpl implements FlightRequestDao {

	@Autowired
	ServiceRequestLineRepository srLineRepository;

	@Autowired
	ServiceRequestSegmentRepository srSegmentRepository;

	@Autowired
	PaxServiceRequestLineRepository paxSRLineRepository;

	@Autowired
	HolidayRequestLineRepository holidayRequestLineRepository;

	@Autowired
	CommonAsyncDao commonAsyncDao;

	public Map<String, Object> flightRequestFromPackage(PackageFlightDTO flightData, String authToken)
			throws Exception {

		ServiceRequestLineModel model = flightData.getRequestData();
		Long requestLineId = fetchSeqGenerator(model.getServiceRequestLine().getRequestId().toString(), "99", 2);
		model.getServiceRequestLine().setRequestLineId(requestLineId);

		String uuid = String.valueOf(model.getServiceRequestLine().getRequestId()) + "-"
				+ String.valueOf(requestLineId);

		model.getServiceRequestLine().setLineUuid(uuid);

		Optional<ServiceRequestLine> srValidtion = srLineRepository.findByRequestLineIdAndRequestId(
				model.getServiceRequestLine().getRequestLineId(), model.getServiceRequestLine().getRequestId());

		if (srValidtion.isPresent()) {
			throw new Exception("invalid-request..sr-and-line-already-exists");

		}
		ServiceRequestLineModel response = new ServiceRequestLineModel();

		Optional<ServiceRequestLine> requestLineValidation = srLineRepository
				.findTopByRequestIdOrderByCreatedDateDesc(model.getServiceRequestLine().getRequestId());

		/*
		 * generate line id by number
		 */

		ServiceRequestLine srLine = new ServiceRequestLine();

		/*
		 * Long lineId = model.getServiceRequestLine().getRequestLineId() != null ?
		 * model.getServiceRequestLine().getRequestLineId() : 0L;
		 */

		if (requestLineValidation.isPresent()) {
			Integer lineNo = requestLineValidation.get().getLineNo() + 1;
			model.getServiceRequestLine().setLineNo(lineNo);
			model.getServiceRequestLine().setLineStatusId(14); // set status Awt Fulfillment
			srLine = srLineRepository.save(model.getServiceRequestLine());
		} else {
			model.getServiceRequestLine().setLineNo(1);
			model.getServiceRequestLine().setLineStatusId(14); // set status Awt Fulfillment

			srLine = srLineRepository.save(model.getServiceRequestLine());
		}

		/*********** START Package line status Change *******************/
		if (srLine.getRequestLineId() > 0) {
			Optional<HolidayRequestLine> packageLine = holidayRequestLineRepository
					.findByRequestIdAndRequestLineId(srLine.getRequestId(), Long.valueOf(5501));
			if (packageLine.isPresent()) {
				packageLine.get().setLineStatusId(67); // 67 - WIP
				holidayRequestLineRepository.save(packageLine.get());
			}
		}
		/*********** END Package line status Change ********************/

		for (ServiceRequestSegment segment : model.getServiceRequestSegment()) {
			segment.setRequestlineID(srLine.getRequestLineId());
			if (Objects.isNull(segment.getFlightSegmentStatus()) || segment.getFlightSegmentStatus().isBlank()
					|| segment.getFlightSegmentStatus().equalsIgnoreCase("Active"))
				segment.setFlightSegmentStatus("Active");
			else
				segment.setFlightSegmentStatus("InActive");
		}
		List<ServiceRequestSegment> segments = srSegmentRepository.saveAll(model.getServiceRequestSegment());

		response.setServiceRequestLine(srLine);
		response.setServiceRequestSegment(segments);

		Map<String, Object> flightResponse = new LinkedHashMap<>();
		flightResponse.put("requestData", response);

		if (flightData.getPaxData() != null && flightData.getPaxData().getPaxData() != null
				&& !flightData.getPaxData().getPaxData().isEmpty()) {

			List<PaxServiceRequestLine> paxInfo = createRequestLinePax(flightData.getPaxData());
			flightResponse.put("paxData", paxInfo);
		}

		commonAsyncDao.saveSrSummaryData(srLine.getRequestId().intValue(), srLine.getRequestLineId().intValue(), 1, 0,
				srLine.getCreatedBy().intValue(), authToken);
		return flightResponse;
	}

	public List<PaxServiceRequestLine> createRequestLinePax(RequestLinePaxDto model) throws Exception {

		if (Objects.isNull(model.getCreatedBy())) {
			throw new Exception("createdBy should not be null");

		}
		if (Objects.isNull(model.getRequestId())) {
			throw new Exception("requestId should not be null");
		}
		if (Objects.isNull(model.getRequestLineId())) {
			throw new Exception("requestLineId should not be null");
		}
		if (Objects.isNull(model.getUpdatedBy())) {
			throw new Exception("updatedby should not be null");
		}

		List<PaxRequestModel> paxDtos = new ArrayList<>();

		for (PaxRequestModel pax : model.getPaxData()) {
			if (model.getCreatedBy() != 0 && model.getCreatedBy() != null)
				pax.setCreatedBy(model.getCreatedBy());
			if (model.getUpdatedBy() != 0 && model.getUpdatedBy() != null)
				pax.setUpdatedBy(model.getUpdatedBy());
			if (model.getRequestId() != 0 && model.getRequestId() != null)
				pax.setRequestId(model.getRequestId());
			if (model.getRequestLineId() != 0 && model.getRequestLineId() != null)
				pax.setRequestLineId(model.getRequestLineId());

			paxDtos.add(pax);
		}

		List<PaxServiceRequestLine> paxData = paxSRLineRepository.saveAll(PaxConverter.convertDtosToModels(paxDtos));

		return paxData;
	}

	public Long fetchSeqGenerator(String sr, String constant, Integer no) {
		return srLineRepository.generateSeqenceNumber(sr, constant, no);

	}

}
