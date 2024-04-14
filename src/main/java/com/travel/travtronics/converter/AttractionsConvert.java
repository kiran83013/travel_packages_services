package com.travel.travtronics.converter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.travel.travtronics.request.AttractionsRequest;
import com.travel.travtronics.request.SrAttractionsLinePaxRequest;
import com.travel.travtronics.request.SrAttractionsLinesRequest;
import com.travel.travtronics.response.dto.SrAttractionsLinesResponse;
import com.travel.travtronics.response.dto.SrAttractionsResponse;
import com.travel.travtronics.srm.model.SrAttractionsLinePaxModel;
import com.travel.travtronics.srm.model.SrAttractionsLinesModel;
import com.travel.travtronics.srm.model.SrAttractionsModel;

public class AttractionsConvert {

	ZonedDateTime instance = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	String currentDateTime = formatter.format(instance); // 15-02-2022 12:43

	public static SrAttractionsModel convertHedareJsonToModel(AttractionsRequest headerInfo) {

		SrAttractionsModel model = new SrAttractionsModel();

		model.setAttractionRequestId(headerInfo.getAttractionRequestId());
		model.setAttractionName(headerInfo.getAttractionName());
		model.setAttractionDescription(headerInfo.getAttractionDescription());
		model.setAttractionStatus(1);
		model.setAttractionCreatedBy(headerInfo.getAttractionCreatedBy());
		model.setAttractionCreatedDate(new AttractionsConvert().currentDateTime);
		model.setAttractionCreatedDevice(headerInfo.getAttractionCreatedDevice());
		model.setAttractionCreatedIp(headerInfo.getAttractionCreatedIp());

		return model;

	}

//	public static SrAttractionsModel convertUpdateHedareJsonToModel(Long attractionSrId,
//			UpdateAttractionsRequest inputData, SrAttractionsModel headerInfo) {
//
//		// SrAttractionsModel model = headerInfo;
//
//		headerInfo.setAttractionId(attractionSrId);
//		headerInfo.setAttractionRequestId(inputData.getAttractionRequestId());
//		headerInfo.setAttractionName(inputData.getAttractionName());
//		headerInfo.setAttractionDescription(inputData.getAttractionDescription());
//		headerInfo.setAttractionStatus(inputData.getAttractionStatus());
//		headerInfo.setAttractionUpdatedBy(inputData.getAttractionUpdatedBy());
//		headerInfo.setAttractionUpdatedDate(new AttractionsConvert().currentDateTime);
//		headerInfo.setAttractionUpdatedDevice(inputData.getAttractionUpdatedDevice());
//		headerInfo.setAttractionUpdatedIp(inputData.getAttractionUpdatedIp());
//
//		return headerInfo;
//
//	}

	public static SrAttractionsLinesModel convertLinesJsonToModel(SrAttractionsLinesRequest lineInfo, Long requestId,
			Long headerId, Integer userId, String device, String ip) {

		SrAttractionsLinesModel model = new SrAttractionsLinesModel();

		model.setAttractionHeaderId(headerId);
		model.setAttractionRequestId(requestId);
		model.setAttractionId(lineInfo.getAttractionId());
		model.setAttractionLineName(lineInfo.getAttractionLineName());
		model.setAttractionLineCity(lineInfo.getAttractionLineCity());
		model.setAttractionLineLocation(lineInfo.getAttractionLineLocation());
		model.setAttractionLineCountry(lineInfo.getAttractionLineCountry());
		if (Objects.nonNull(lineInfo.getAttractionLinePaxCount()) && lineInfo.getAttractionLinePaxCount() > 0)
			model.setAttractionLinePaxCount(lineInfo.getAttractionLinePaxCount());
		else
			model.setAttractionLinePaxCount(1);
		model.setAttractionLineDay(lineInfo.getAttractionLineDay());
		model.setAttractionLineDate(lineInfo.getAttractionLineDate());
		model.setAttractionLineStatus(1);
		model.setAttractionLineCreatedBy(userId);
		model.setAttractionLineCreatedDate(new AttractionsConvert().currentDateTime);
		model.setAttractionLineCreatedDevice(device);
		model.setAttractionLineCreatedIp(ip);

		// model.setAttractionStatusId(lineInfo.getAttractionStatusId() != null ?
		// lineInfo.getAttractionStatusId() : 0);
		model.setAttractionStatusId(14); // Awt Fulfillment

		return model;

	}

//	public static SrAttractionsLinesModel convertUpdateLinesJsonToModel(SrAttractionsLinesModel lineInfoData,
//			UpdateSrAttractionsLinesRequest lineInfo, Long requestId, Long headerId, Integer userId, String device,
//			String ip) {
//
//		SrAttractionsLinesModel model = new SrAttractionsLinesModel();
//
//		if (lineInfoData != null && lineInfoData.getAttractionLineId() != null
//				&& lineInfoData.getAttractionLineId() > 0) {
//			model = lineInfoData;
//		}
//
//		model.setAttractionLineId(lineInfo.getAttractionLineId());
//		model.setAttractionHeaderId(headerId);
//		model.setAttractionRequestId(requestId);
//		model.setAttractionId(lineInfo.getAttractionId());
//		model.setAttractionLineName(lineInfo.getAttractionLineName());
//		model.setAttractionLineCity(lineInfo.getAttractionLineCity());
//		model.setAttractionLineLocation(lineInfo.getAttractionLineLocation());
//		model.setAttractionLineCountry(lineInfo.getAttractionLineCountry());
//		model.setAttractionLinePaxCount(lineInfo.getAttractionLinePaxCount());
//		model.setAttractionLineDay(lineInfo.getAttractionLineDay());
//		model.setAttractionLineDate(lineInfo.getAttractionLineDate());
//
//		model.setAttractionLineStatus(
//				lineInfo.getAttractionLineStatusId() != null ? lineInfo.getAttractionLineStatusId()
//						: lineInfoData.getAttractionStatusId());
//
//		if (lineInfo.getAttractionLineId() != null && lineInfo.getAttractionLineId() > 0) {
//			model.setAttractionLineStatus(lineInfo.getAttractionLinePassengerStatus());
//			model.setAttractionLineUpdatedBy(userId);
//			model.setAttractionLineUpdatedDate(new AttractionsConvert().currentDateTime);
//			model.setAttractionLineUpdatedDevice(device);
//			model.setAttractionLineUpdatedIp(ip);
//		} else {
//			model.setAttractionLineStatus(1);
//			model.setAttractionLineCreatedBy(userId);
//			model.setAttractionLineCreatedDate(new AttractionsConvert().currentDateTime);
//			model.setAttractionLineCreatedDevice(device);
//			model.setAttractionLineCreatedIp(ip);
//		}
//
//		return model;
//
//	}

	public static List<SrAttractionsLinePaxModel> convertPaxLinesJsonToModel(List<SrAttractionsLinePaxRequest> paxInfo,
			Long lineId, Integer userId, String device, String ip) {

		List<SrAttractionsLinePaxModel> modelPax = new ArrayList<>();

		if (paxInfo != null && paxInfo.size() > 0) {
			for (SrAttractionsLinePaxRequest pax : paxInfo) {

				SrAttractionsLinePaxModel model = new SrAttractionsLinePaxModel();

				model.setAttractionLineId(lineId);
				model.setAttractionLinePaxId(pax.getAttractionLinePaxId());
				model.setAttractionLinePassengerType(pax.getAttractionLinePassengerType());
				model.setAttractionLinePassengerTitle(pax.getAttractionLinePassengerTitle());
				model.setAttractionLinePassengerFristName(pax.getAttractionLinePassengerFristName());
				model.setAttractionLinePassengerMiddleName(pax.getAttractionLinePassengerMiddleName());
				model.setAttractionLinePassengerLastName(pax.getAttractionLinePassengerLastName());
				model.setAttractionLinePassengerDob(pax.getAttractionLinePassengerDob());
				model.setAttractionLinePassengerGender(pax.getAttractionLinePassengerGender());
				model.setAttractionLinePassengerPhone(pax.getAttractionLinePassengerPhone());
				model.setAttractionLinePassengerEmail(pax.getAttractionLinePassengerEmail());
				model.setAttractionLinePassengerStatus(1);
				model.setAttractionLinePassengerCreatedBy(userId);
				model.setAttractionLinePassengerCreatedDate(new AttractionsConvert().currentDateTime);
				model.setAttractionLinePassengerCreatedDevice(device);
				model.setAttractionLinePassengerCreatedIp(ip);

				modelPax.add(model);
			}
		}

		return modelPax;

	}

	public static SrAttractionsLinesResponse convertLinesModelToResponseData(SrAttractionsLinesModel lineInfo,
			List<SrAttractionsLinePaxModel> paxInfo) {

		SrAttractionsLinesResponse response = new SrAttractionsLinesResponse();
		if (lineInfo != null && lineInfo.getAttractionLineId() > 0) {
			response.setAttractionLineId(lineInfo.getAttractionLineId());
			response.setAttractionHeaderId(lineInfo.getAttractionHeaderId());
			response.setAttractionRequestId(lineInfo.getAttractionRequestId());
			response.setAttractionId(lineInfo.getAttractionId());
			response.setAttractionLineName(lineInfo.getAttractionLineName());
			response.setAttractionLineCity(lineInfo.getAttractionLineCity());
			response.setAttractionLineLocation(lineInfo.getAttractionLineLocation());
			response.setAttractionLineCountry(lineInfo.getAttractionLineCountry());
			response.setAttractionLinePaxCount(lineInfo.getAttractionLinePaxCount());
			response.setAttractionLineDay(lineInfo.getAttractionLineDay());
			response.setAttractionLineDate(lineInfo.getAttractionLineDate());
			response.setAttractionLineStatus(lineInfo.getAttractionLineStatus());
			response.setAttractionLineCreatedBy(lineInfo.getAttractionLineCreatedBy());
			response.setAttractionLineCreatedDate(lineInfo.getAttractionLineCreatedDate());
			response.setAttractionLineCreatedDevice(lineInfo.getAttractionLineCreatedDevice());
			response.setAttractionLineCreatedIp(lineInfo.getAttractionLineCreatedIp());
			response.setPassengers(paxInfo);
		}
		return response;
	}

	public static SrAttractionsResponse convertModelAttractionsToResponseData(SrAttractionsModel headerInfo,
			List<SrAttractionsLinesResponse> lineInfo) {

		SrAttractionsResponse response = new SrAttractionsResponse();

		if (headerInfo != null && headerInfo.getAttractionId() > 0) {
			response.setAttractionId(headerInfo.getAttractionId());
			response.setAttractionRequestId(headerInfo.getAttractionRequestId());
			response.setAttractionName(headerInfo.getAttractionName());
			response.setAttractionDescription(headerInfo.getAttractionDescription());
			response.setAttractionStatus(headerInfo.getAttractionStatus());
			response.setAttractionCreatedBy(headerInfo.getAttractionCreatedBy());
			response.setAttractionCreatedDate(headerInfo.getAttractionCreatedDate());
			response.setAttractionCreatedDevice(headerInfo.getAttractionCreatedDevice());
			response.setAttractionCreatedIp(headerInfo.getAttractionCreatedIp());
			response.setLines(lineInfo);
		}

		return response;
	}
	/*
	 * rfq models
	 */

//	public static RfqSrAttractionsModel convertRfqHeaderJsonToModel(RfqAttractionsRequest headerInfo, Long rfqId) {
//
//		RfqSrAttractionsModel model = new RfqSrAttractionsModel();
//		model.setRfqAttractionId(rfqId);
//		model.setAttractionRequestId(headerInfo.getAttractionRequestId());
//		model.setAttractionName(headerInfo.getAttractionName());
//		model.setAttractionDescription(headerInfo.getAttractionDescription());
//		if (Objects.nonNull(headerInfo.getAttractionStatus()) && headerInfo.getAttractionStatus() != 0)
//			model.setAttractionStatus(headerInfo.getAttractionStatus());
//		else
//			model.setAttractionStatus(1);
//		model.setAttractionCreatedBy(headerInfo.getAttractionCreatedBy());
//		model.setAttractionCreatedDate(new AttractionsConvert().currentDateTime);
//		model.setAttractionCreatedDevice(headerInfo.getAttractionCreatedDevice());
//		model.setAttractionCreatedIp(headerInfo.getAttractionCreatedIp());
//		model.setAttractionAttribute1(headerInfo.getAttractionAttribute1());
//		model.setAttractionAttribute2(headerInfo.getAttractionAttribute2());
//		model.setAttractionAttribute3(headerInfo.getAttractionAttribute3());
//		model.setAttractionRequestLineId(headerInfo.getAttractionRequestLineId());
//		// model.setRfqAttractionUuid(UUID.randomUUID().toString());
//		String uuid = rfqId + "-" + headerInfo.getAttractionRequestId() + "-" + headerInfo.getAttractionRequestLineId();
//		model.setRfqAttractionUuid(uuid);
//		return model;
//
//	}
//
//	public static RfqSrAttractionsModel updateRfqHeaderJsonToModel(RfqAttractionsRequest headerInfo,
//			RfqSrAttractionsModel model) {
//		model.setAttractionRequestId(headerInfo.getAttractionRequestId());
//		model.setAttractionName(headerInfo.getAttractionName());
//		model.setAttractionDescription(headerInfo.getAttractionDescription());
//
//		model.setAttractionStatus(headerInfo.getAttractionStatus());
//
//		model.setAttractionUpdatedBy(headerInfo.getAttractionUpdatedBy());
//		model.setAttractionUpdatedDate(new AttractionsConvert().currentDateTime);
//		model.setAttractionUpdatedDevice(headerInfo.getAttractionUpdatedDevice());
//		model.setAttractionUpdatedIp(headerInfo.getAttractionUpdatedIp());
//		model.setAttractionAttribute1(headerInfo.getAttractionAttribute1());
//		model.setAttractionAttribute2(headerInfo.getAttractionAttribute2());
//		model.setAttractionAttribute3(headerInfo.getAttractionAttribute3());
//		model.setAttractionRequestLineId(headerInfo.getAttractionRequestLineId());
//
//		return model;
//	}
//
//	public static List<RfqSrAttractionsLinePaxModel> convertRfqPaxLinesJsonToModel(
//			List<RfqSrAttractionsLinePaxRequest> paxInfo, Long rfqId, Long lineId) {
//
//		return CollectionUtils.isNotEmpty(paxInfo) ? paxInfo.stream().map(pax -> {
//			RfqSrAttractionsLinePaxModel model = new RfqSrAttractionsLinePaxModel();
//			// private Long attractionLinePassengerId;
//
//			if (Objects.nonNull(pax.getAttractionLinePassengerId()) && pax.getAttractionLinePassengerId() != 0)
//				model.setAttractionLinePassengerId(pax.getAttractionLinePassengerId());
//			model.setAttractionLineId(lineId);
//			model.setAttractionLinePaxId(pax.getAttractionLinePaxId());
//			model.setAttractionLinePassengerType(pax.getAttractionLinePassengerType());
//			model.setAttractionLinePassengerTitle(pax.getAttractionLinePassengerTitle());
//			model.setAttractionLinePassengerFristName(pax.getAttractionLinePassengerFristName());
//			model.setAttractionLinePassengerMiddleName(pax.getAttractionLinePassengerMiddleName());
//			model.setAttractionLinePassengerLastName(pax.getAttractionLinePassengerLastName());
//			model.setAttractionLinePassengerDob(pax.getAttractionLinePassengerDob());
//			model.setAttractionLinePassengerGender(pax.getAttractionLinePassengerGender());
//			model.setAttractionLinePassengerPhone(pax.getAttractionLinePassengerPhone());
//			model.setAttractionLinePassengerEmail(pax.getAttractionLinePassengerEmail());
//			if (Objects.nonNull(pax.getAttractionLinePassengerStatus()) && pax.getAttractionLinePassengerStatus() != 0)
//				model.setAttractionLinePassengerStatus(pax.getAttractionLinePassengerStatus());
//			else
//				model.setAttractionLinePassengerStatus(1);
//			model.setAttractionLinePassengerCreatedBy(pax.getAttractionLinePassengerCreatedBy());
//			model.setAttractionLinePassengerCreatedDate(new AttractionsConvert().currentDateTime);
//			model.setAttractionLinePassengerCreatedDevice(pax.getAttractionLinePassengerCreatedDevice());
//			model.setAttractionLinePassengerCreatedIp(pax.getAttractionLinePassengerCreatedIp());
//			model.setRfqAttractionId(rfqId);
//			return model;
//		}).collect(Collectors.toList()) : Collections.emptyList();
//	}
//
//	public static List<RfqSrAttractionsLinePaxRequest> convertRfqPaxLinesModelToJson(
//			List<RfqSrAttractionsLinePaxModel> requests) {
//		return requests.stream().map(pax -> {
//			RfqSrAttractionsLinePaxRequest model = new RfqSrAttractionsLinePaxRequest();
//
//			model.setAttractionLineId(pax.getAttractionLineId());
//			model.setAttractionLinePaxId(pax.getAttractionLinePaxId());
//			model.setAttractionLinePassengerType(pax.getAttractionLinePassengerType());
//			model.setAttractionLinePassengerTitle(pax.getAttractionLinePassengerTitle());
//			model.setAttractionLinePassengerFristName(pax.getAttractionLinePassengerFristName());
//			model.setAttractionLinePassengerMiddleName(pax.getAttractionLinePassengerMiddleName());
//			model.setAttractionLinePassengerLastName(pax.getAttractionLinePassengerLastName());
//			model.setAttractionLinePassengerDob(pax.getAttractionLinePassengerDob());
//			model.setAttractionLinePassengerGender(pax.getAttractionLinePassengerGender());
//			model.setAttractionLinePassengerPhone(pax.getAttractionLinePassengerPhone());
//			model.setAttractionLinePassengerEmail(pax.getAttractionLinePassengerEmail());
//			model.setAttractionLinePassengerStatus(pax.getAttractionLinePassengerStatus());
//			model.setAttractionLinePassengerCreatedBy(pax.getAttractionLinePassengerCreatedBy());
//			model.setAttractionLinePassengerCreatedDate(pax.getAttractionLinePassengerCreatedDate());
//			model.setAttractionLinePassengerCreatedDevice(pax.getAttractionLinePassengerCreatedDevice());
//			model.setAttractionLinePassengerCreatedIp(pax.getAttractionLinePassengerCreatedIp());
//			model.setRfqAttractionId(pax.getRfqAttractionId());
//			model.setAttractionLinePassengerUpdatedBy(pax.getAttractionLinePassengerUpdatedBy());
//			model.setAttractionLinePassengerUpdatedDate(pax.getAttractionLinePassengerUpdatedDate());
//			model.setAttractionLinePassengerUpdatedDevice(pax.getAttractionLinePassengerUpdatedDevice());
//			model.setAttractionLinePassengerUpdatedIp(pax.getAttractionLinePassengerUpdatedIp());
//			model.setAttractionLinePassengerCreatedDate(pax.getAttractionLinePassengerCreatedDate());
//			model.setAttractionLinePassengerCreatedDevice(pax.getAttractionLinePassengerCreatedDevice());
//			model.setAttractionLinePassengerCreatedIp(pax.getAttractionLinePassengerCreatedIp());
//			model.setAttractionLinePassengerId(pax.getAttractionLinePassengerId());
//			return model;
//		}).collect(Collectors.toList());
//	}
//
//	public static RfqSrAttractionsLinesModel convertRfqLinesJsonToModel(RfqAttractionsLineRequest lineInfo,
//			Long rfqAttractionId, Long requestId, Long requestLineId, String uuid) {
//		RfqSrAttractionsLinesModel model = new RfqSrAttractionsLinesModel();
//		model.setAttractionHeaderLineId(lineInfo.getAttractionHeaderLineId());
//		model.setAttractionHeaderId(lineInfo.getAttractionHeaderId());
//		model.setAttractionRequestId(requestId);
//		model.setAttractionId(lineInfo.getAttractionId());
//		model.setAttractionLineName(lineInfo.getAttractionLineName());
//		model.setAttractionLineCity(lineInfo.getAttractionLineCity());
//		model.setAttractionLineLocation(lineInfo.getAttractionLineLocation());
//		model.setAttractionLineCountry(lineInfo.getAttractionLineCountry());
//		model.setAttractionLinePaxCount(lineInfo.getAttractionLinePaxCount());
//		model.setAttractionLineDay(lineInfo.getAttractionLineDay());
//		model.setAttractionLineDate(lineInfo.getAttractionLineDate());
//		if (Objects.nonNull(lineInfo.getAttractionLineStatus()) && lineInfo.getAttractionLineStatus() != 0)
//			model.setAttractionLineStatus(lineInfo.getAttractionLineStatus());
//		else
//			model.setAttractionLineStatus(1);
//		model.setAttractionLineCreatedBy(lineInfo.getAttractionLineCreatedBy());
//		model.setAttractionLineCreatedDate(new AttractionsConvert().currentDateTime);
//		model.setAttractionLineCreatedDevice(lineInfo.getAttractionLineCreatedDevice());
//		model.setAttractionLineCreatedIp(lineInfo.getAttractionLineCreatedIp());
//		model.setRfqAttractionId(rfqAttractionId);
//		model.setAttractionHeaderId(requestLineId);
//		model.setRfqAttractionUuid(uuid);
//		return model;
//	}
//
//	/*
//	 * public static RfqSrAttractionsLinesModel
//	 * updateRfqLinesJsonToModel(RfqAttractionsLineRequest lineInfo,
//	 * RfqSrAttractionsLinesModel model, Long rqfId, Long requestId, Long reqLineId)
//	 * {
//	 * 
//	 * model.setAttractionHeaderId(reqLineId);
//	 * model.setAttractionHeaderLineId(lineInfo.getAttractionHeaderLineId());
//	 * model.setAttractionRequestId(requestId);
//	 * model.setAttractionId(lineInfo.getAttractionId());
//	 * model.setAttractionLineName(lineInfo.getAttractionLineName());
//	 * model.setAttractionLineCity(lineInfo.getAttractionLineCity());
//	 * model.setAttractionLineLocation(lineInfo.getAttractionLineLocation());
//	 * model.setAttractionLineCountry(lineInfo.getAttractionLineCountry());
//	 * model.setAttractionLinePaxCount(lineInfo.getAttractionLinePaxCount());
//	 * model.setAttractionLineDay(lineInfo.getAttractionLineDay());
//	 * model.setAttractionLineDate(lineInfo.getAttractionLineDate());
//	 * 
//	 * model.setAttractionLineStatus(lineInfo.getAttractionLineStatus());
//	 * model.setAttractionLineUpdatedBy(lineInfo.getAttractionLineUpdatedBy());
//	 * model.setAttractionLineUpdatedDate(new AttractionsConvert().currentDateTime);
//	 * model.setAttractionLineUpdatedDevice(lineInfo.getAttractionLineUpdatedDevice(
//	 * )); model.setAttractionLineUpdatedIp(lineInfo.getAttractionLineUpdatedIp());
//	 * model.setRfqAttractionId(rqfId);
//	 * 
//	 * return model; }
//	 */
//
//	public static RfqSrAttractionsLinesModel updateRfqLinesJsonToModel(RfqAttractionsLineRequest lineInfo,
//			RfqSrAttractionsLinesModel model) {
//
//		model.setAttractionHeaderId(lineInfo.getAttractionHeaderId());
//		model.setAttractionHeaderLineId(lineInfo.getAttractionHeaderLineId());
//		model.setAttractionRequestId(lineInfo.getAttractionRequestId());
//		model.setAttractionId(lineInfo.getAttractionId());
//		model.setAttractionLineName(lineInfo.getAttractionLineName());
//		model.setAttractionLineCity(lineInfo.getAttractionLineCity());
//		model.setAttractionLineLocation(lineInfo.getAttractionLineLocation());
//		model.setAttractionLineCountry(lineInfo.getAttractionLineCountry());
//		model.setAttractionLinePaxCount(lineInfo.getAttractionLinePaxCount());
//		model.setAttractionLineDay(lineInfo.getAttractionLineDay());
//		model.setAttractionLineDate(lineInfo.getAttractionLineDate());
//
//		model.setAttractionLineStatus(lineInfo.getAttractionLineStatus());
//		model.setAttractionLineUpdatedBy(lineInfo.getAttractionLineUpdatedBy());
//		model.setAttractionLineUpdatedDate(new AttractionsConvert().currentDateTime);
//		model.setAttractionLineUpdatedDevice(lineInfo.getAttractionLineUpdatedDevice());
//		model.setAttractionLineUpdatedIp(lineInfo.getAttractionLineUpdatedIp());
//		model.setRfqAttractionId(lineInfo.getRfqAttractionId());
//
//		return model;
//	}
//
//	public static RfqAttractionsLineRequest generateRfqLineResponse(RfqSrAttractionsLinesModel model,
//			List<RfqSrAttractionsLinePaxModel> paxInfo) {
//		RfqAttractionsLineRequest line = new RfqAttractionsLineRequest();
//		line.setAttractionLineId(model.getAttractionLineId());
//		line.setAttractionHeaderId(model.getAttractionHeaderId());
//		line.setAttractionHeaderLineId(model.getAttractionHeaderLineId());
//		line.setAttractionRequestId(model.getAttractionRequestId());
//		line.setAttractionId(model.getAttractionId());
//		line.setAttractionLineName(model.getAttractionLineName());
//		line.setAttractionLineCity(model.getAttractionLineCity());
//		line.setAttractionLineLocation(model.getAttractionLineLocation());
//		line.setAttractionLineCountry(model.getAttractionLineCountry());
//		line.setAttractionLinePaxCount(model.getAttractionLinePaxCount());
//		line.setAttractionLineDay(model.getAttractionLineDay());
//		line.setAttractionLineDate(model.getAttractionLineDate());
//		line.setAttractionLineStatus(model.getAttractionLineStatus());
//		line.setAttractionLineCreatedBy(model.getAttractionLineCreatedBy());
//		line.setAttractionLineCreatedDate(model.getAttractionLineCreatedDate());
//		line.setAttractionLineCreatedDevice(model.getAttractionLineCreatedDevice());
//		line.setAttractionLineCreatedIp(model.getAttractionLineCreatedIp());
//		line.setRfqAttractionId(model.getRfqAttractionId());
//
//		line.setAttractionLineUpdatedBy(model.getAttractionLineUpdatedBy());
//		line.setAttractionLineUpdatedDate(model.getAttractionLineUpdatedDate());
//		line.setAttractionLineUpdatedDevice(model.getAttractionLineUpdatedDevice());
//		line.setAttractionLineUpdatedIp(model.getAttractionLineUpdatedIp());
//		line.setRfqAttractionUuid(model.getRfqAttractionUuid());
//		if (CollectionUtils.isNotEmpty(paxInfo))
//			line.setPassengers(convertRfqPaxLinesModelToJson(paxInfo));
//		return line;
//
//	}
//
//	public static RfqAttractionsRequest generateRfqAttractionsResponse(RfqSrAttractionsModel model,
//			List<RfqAttractionsLineRequest> lines) {
//		RfqAttractionsRequest response = new RfqAttractionsRequest();
//		response.setRfqAttractionId(model.getRfqAttractionId());
//		response.setAttractionRequestId(model.getAttractionRequestId());
//		response.setAttractionName(model.getAttractionName());
//		response.setAttractionDescription(model.getAttractionDescription());
//		response.setAttractionStatus(model.getAttractionStatus());
//		response.setAttractionCreatedBy(model.getAttractionCreatedBy());
//		response.setAttractionCreatedDate(model.getAttractionCreatedDate());
//		response.setAttractionCreatedDevice(model.getAttractionCreatedDevice());
//		response.setAttractionCreatedIp(model.getAttractionCreatedIp());
//		response.setAttractionAttribute1(model.getAttractionAttribute1());
//		response.setAttractionAttribute2(model.getAttractionAttribute2());
//		response.setAttractionRequestLineId(model.getAttractionRequestLineId());
//		response.setAttractionAttribute3(model.getAttractionAttribute3());
//		response.setRfqAttractionUuid(model.getRfqAttractionUuid());
//		if (CollectionUtils.isNotEmpty(lines))
//			response.setLines(lines);
//
//		return response;
//	}
//
//	public static SrAttractionsLinesResponse convertLinesModelToResponseDataInfo(AttrLineEntity lineInfo,
//			List<SrAttractionsLinePaxModel> paxsInfo) {
//		SrAttractionsLinesResponse response = new SrAttractionsLinesResponse();
//		if (lineInfo != null && lineInfo.getAttractionLineId() > 0) {
//			response.setAttractionLineId(lineInfo.getAttractionLineId());
//			response.setAttractionHeaderId(lineInfo.getAttractionHeaderId());
//			response.setAttractionRequestId(lineInfo.getAttractionRequestId());
//			response.setAttractionId(lineInfo.getAttractionId());
//			response.setAttractionLineName(lineInfo.getAttractionLineName());
//			response.setAttractionLineCity(lineInfo.getAttractionLineCity());
//			response.setAttractionLineLocation(lineInfo.getAttractionLineLocation());
//			response.setAttractionLineCountry(lineInfo.getAttractionLineCountry());
//			response.setAttractionLinePaxCount(lineInfo.getAttractionLinePaxCount());
//			response.setAttractionLineDay(lineInfo.getAttractionLineDay());
//			response.setAttractionLineDate(lineInfo.getAttractionLineDate());
//			response.setAttractionLineStatus(lineInfo.getAttractionLineStatus());
//			response.setAttractionLineCreatedBy(lineInfo.getAttractionLineCreatedBy());
//			response.setAttractionLineCreatedDate(lineInfo.getAttractionLineCreatedDate());
//			response.setAttractionLineCreatedDevice(lineInfo.getAttractionLineCreatedDevice());
//			response.setAttractionLineCreatedIp(lineInfo.getAttractionLineCreatedIp());
//			response.setAttractionStatus(lineInfo.getAttractionStatus());
//			response.setAttractionStatusId(lineInfo.getAttractionStatusId());
//			response.setPassengers(paxsInfo);
//		}
//		return response;
//
//	}

}
