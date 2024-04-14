package com.travel.travtronics.srm.dao.impl;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.travel.travtronics.converter.AttractionsConvert;
import com.travel.travtronics.request.AttractionsRequest;
import com.travel.travtronics.request.SrAttractionsLinesRequest;
import com.travel.travtronics.response.dto.SrAttractionsLinesResponse;
import com.travel.travtronics.response.dto.SrAttractionsResponse;
import com.travel.travtronics.srm.dao.AttrRequestDao;
import com.travel.travtronics.srm.dao.CommonAsyncDao;
import com.travel.travtronics.srm.model.HolidayRequestLine;
import com.travel.travtronics.srm.model.SrAttractionsLinePaxModel;
import com.travel.travtronics.srm.model.SrAttractionsLinesModel;
import com.travel.travtronics.srm.model.SrAttractionsModel;
import com.travel.travtronics.srm.repository.HolidayRequestLineRepository;
import com.travel.travtronics.srm.repository.SrAttractionsLinePaxRepository;
import com.travel.travtronics.srm.repository.SrAttractionsLinesRepository;
import com.travel.travtronics.srm.repository.SrAttractionsRepository;

@Repository("AttractionDao")
public class AttrRequestDaoImpl implements AttrRequestDao {

	@Autowired
	HolidayRequestLineRepository holidayRequestLineRepository;

	@Autowired
	CommonAsyncDao commonAsyncDao;

	ZonedDateTime instance = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	String currentDateTime = formatter.format(instance); // 15-02-2022 12:43

	@Autowired
	SrAttractionsRepository attractionsRepository;

	@Autowired
	SrAttractionsLinesRepository attractionsLinesRepository;

	@Autowired
	SrAttractionsLinePaxRepository attractionsLinePaxRepository;

	@Override
	public SrAttractionsResponse attrRequestFromPackage(AttractionsRequest anxRequest, String token) throws Exception {

		SrAttractionsResponse responseData = new SrAttractionsResponse();

		SrAttractionsModel headerInfo = attractionsRepository
				.save(AttractionsConvert.convertHedareJsonToModel(anxRequest));

		if (headerInfo != null && headerInfo.getAttractionId() != null && headerInfo.getAttractionId() > 0) {

			Long headerId = headerInfo.getAttractionId();
			Long requestId = headerInfo.getAttractionRequestId();
			Integer userId = headerInfo.getAttractionCreatedBy();
			String device = headerInfo.getAttractionCreatedDevice();
			String ip = headerInfo.getAttractionCreatedIp();

			List<SrAttractionsLinesResponse> linesResponse = new ArrayList<>();

			if (anxRequest.getLines() != null && !anxRequest.getLines().isEmpty() && anxRequest.getLines().size() > 0) {

				for (SrAttractionsLinesRequest line : anxRequest.getLines()) {

					SrAttractionsLinesModel lineInfo = attractionsLinesRepository.save(
							AttractionsConvert.convertLinesJsonToModel(line, requestId, headerId, userId, device, ip));

					if (lineInfo != null && lineInfo.getAttractionLineId() != null
							&& lineInfo.getAttractionLineId() > 0) {

						Long lineId = lineInfo.getAttractionLineId();

						List<SrAttractionsLinePaxModel> paxInfo = attractionsLinePaxRepository
								.saveAll(AttractionsConvert.convertPaxLinesJsonToModel(line.getPassengers(), lineId,
										userId, device, ip));

						SrAttractionsLinesResponse linesData = AttractionsConvert
								.convertLinesModelToResponseData(lineInfo, paxInfo);

						linesResponse.add(linesData);
					}
				}
			}
			responseData = AttractionsConvert.convertModelAttractionsToResponseData(headerInfo, linesResponse);

			commonAsyncDao.saveSrSummaryData(headerInfo.getAttractionRequestId().intValue(),
					headerInfo.getAttractionId().intValue(), 4, 0, headerInfo.getAttractionCreatedBy(), token);
		}

		/*********** START Package line status Change *******************/
		if (headerInfo.getAttractionRequestId() > 0) {
			Optional<HolidayRequestLine> packageLine = holidayRequestLineRepository.findByRequestIdAndRequestLineId(
					Long.valueOf(headerInfo.getAttractionRequestId()), Long.valueOf(5501));
			if (packageLine.isPresent()) {
				packageLine.get().setLineStatusId(67); // 67 - WIP
				holidayRequestLineRepository.save(packageLine.get());
			}
		}
		/*********** END Package line status Change ********************/

		return responseData;

	}

}
