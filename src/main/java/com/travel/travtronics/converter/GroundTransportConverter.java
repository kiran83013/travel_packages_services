package com.travel.travtronics.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.travel.travtronics.request.GroundPaxRequest;
import com.travel.travtronics.request.GroundPaxRequest.GroundPaxRequestBuilder;
import com.travel.travtronics.request.GroundTransportRequest;
import com.travel.travtronics.request.GroundTransportRequest.GroundTransportRequestBuilder;
import com.travel.travtronics.srm.model.GTLine;
import com.travel.travtronics.srm.model.GTLine.GTLineBuilder;
import com.travel.travtronics.srm.model.GTPax;
import com.travel.travtronics.srm.model.GTPax.GTPaxBuilder;
import com.travel.travtronics.srm.model.HolidayRequestPax;

public class GroundTransportConverter {

	public static GTLine convertGroundTransportRequestTOEntity(GroundTransportRequest request) {

		GTLineBuilder groundTranportBuilder = GTLine.builder();

		groundTranportBuilder.requestId(request.getRequestId());
		groundTranportBuilder.requestLineId(request.getRequestLineId());
		groundTranportBuilder.departureCity(request.getDepartureCity());
		groundTranportBuilder.departureCountry(request.getDepartureCountry());
		groundTranportBuilder.departureLocation(request.getDepartureLocation());
		groundTranportBuilder.departureDate(request.getDepartureDate());
		groundTranportBuilder.arrivalCity(request.getArrivalCity());
		groundTranportBuilder.arrivalCountry(request.getArrivalCountry());
		groundTranportBuilder.arrivalLocation(request.getArrivalLocation());
		groundTranportBuilder.returnDate(request.getReturnDate());
		groundTranportBuilder.noOfAdults(request.getNoOfAdults() != null ? request.getNoOfAdults() : 0);
		groundTranportBuilder.noOfChilds(request.getNoOfChilds() != null ? request.getNoOfChilds() : 0);
		groundTranportBuilder.noOfInfants(request.getNoOfInfants() != null ? request.getNoOfInfants() : 0);
		groundTranportBuilder.type(request.getType());
		groundTranportBuilder.specialRequests(request.getSpecialRequests());
		groundTranportBuilder.createdBy(request.getCreatedBy());
		groundTranportBuilder.vehicleType(request.getVehicleType());
		groundTranportBuilder.requestLineUuid(request.getRequestId() + "-" + request.getRequestLineId());
		groundTranportBuilder.typeName(request.getTypeName());
		groundTranportBuilder.createdDate(LocalDateTime.now());
		groundTranportBuilder.lineStatusId(request.getLineStatusId() != null ? request.getLineStatusId() : 14);
		return groundTranportBuilder.build();
	}

	public static GroundPaxRequest convertHolidayPaxToGroundPax(HolidayRequestPax request, Long userId) {

		GroundPaxRequestBuilder paxBuilder = GroundPaxRequest.builder();

		paxBuilder.createdBy(userId);

		paxBuilder.requestId(request.getRequestId());
		paxBuilder.requestLineId(request.getRequestLineId());
		paxBuilder.paxId(request.getPaxId());
		paxBuilder.statusId(request.getStatusId());

		paxBuilder.firstName(request.getFirstName());
		paxBuilder.lastName(request.getLastName());
		paxBuilder.email(request.getEmail());
		paxBuilder.phone(request.getPhone());
		paxBuilder.nationality(request.getNationality());
		paxBuilder.dob(request.getDob());
		paxBuilder.nationality(request.getNationality());
		paxBuilder.issuedCountry(request.getIssuedCountry());
		paxBuilder.paxType(request.getPaxType());
		paxBuilder.passport(request.getPassport());
		paxBuilder.passportExpiredDate(request.getPassportExpiredDate());
		paxBuilder.passportIssueDate(request.getPassportIssueDate());
		paxBuilder.paxIsDeleted(request.getPaxIsDeleted());
		paxBuilder.requestLinePaxId(request.getRequestLinePaxId());
		paxBuilder.paxTypeName(request.getPaxCode());
		paxBuilder.nationalityName(request.getNationlaityName());
		paxBuilder.issuedCountryName(request.getIssuedCountryName());
		paxBuilder.prefix(request.getPrefix());
		return paxBuilder.build();

	}

	public static List<GroundPaxRequest> convertListHolidayPaxToListGroundPax(List<HolidayRequestPax> request,
			Long userId) {

		return request.stream().map(pax -> convertHolidayPaxToGroundPax(pax, userId)).collect(Collectors.toList());

	}

	public static void updateGroundTransportRequestTOEntity(GroundTransportRequest request, GTLine groundTransport) {

		if (request.getDepartureCity() != null)
			groundTransport.setDepartureCity(request.getDepartureCity());
		if (request.getDepartureCountry() != null)
			groundTransport.setDepartureCountry(request.getDepartureCountry());
		if (request.getDepartureLocation() != null)
			groundTransport.setDepartureLocation(request.getDepartureLocation());
		if (request.getDepartureDate() != null)
			groundTransport.setDepartureDate(request.getDepartureDate());
		if (request.getArrivalCity() != null)
			groundTransport.setArrivalCity(request.getArrivalCity());
		if (request.getArrivalCountry() != null)
			groundTransport.setArrivalCountry(request.getArrivalCountry());
		if (request.getArrivalLocation() != null)
			groundTransport.setArrivalLocation(request.getArrivalLocation());
		if (request.getReturnDate() != null)
			groundTransport.setReturnDate(request.getReturnDate());
		if (request.getNoOfAdults() != null)
			groundTransport.setNoOfAdults(request.getNoOfAdults());
		if (request.getNoOfChilds() != null)
			groundTransport.setNoOfChilds(request.getNoOfChilds());
		if (request.getNoOfInfants() != null)
			groundTransport.setNoOfInfants(request.getNoOfInfants());
		if (request.getType() != null)
			groundTransport.setType(request.getType());
		if (request.getSpecialRequests() != null)
			groundTransport.setSpecialRequests(request.getSpecialRequests());
		if (request.getVehicleType() != null)
			groundTransport.setVehicleType(request.getVehicleType());
		if (request.getTypeName() != null)
			groundTransport.setTypeName(request.getTypeName());
		if (request.getLineStatusId() != null)
			groundTransport.setLineStatusId(request.getLineStatusId());
		groundTransport.setRequestLineUuid(request.getRequestLineUuid());
		groundTransport.setUpdatedBy(request.getUpdatedBy());
		groundTransport.setUpdatedDate(LocalDateTime.now());

	}

	public static GroundTransportRequest generateGroundTransportResponse(GTLine request, List<GTPax> pax) {

		GroundTransportRequestBuilder groundTranportBuilder = GroundTransportRequest.builder();
		groundTranportBuilder.requestId(request.getRequestId());
		groundTranportBuilder.requestLineId(request.getRequestLineId());
		groundTranportBuilder.requestLineUuid(request.getRequestLineUuid());
		groundTranportBuilder.departureCity(request.getDepartureCity());
		groundTranportBuilder.departureCountry(request.getDepartureCountry());
		groundTranportBuilder.departureLocation(request.getDepartureLocation());
		groundTranportBuilder.departureDate(request.getDepartureDate());
		groundTranportBuilder.arrivalCity(request.getArrivalCity());
		groundTranportBuilder.arrivalCountry(request.getArrivalCountry());
		groundTranportBuilder.arrivalLocation(request.getArrivalLocation());
		groundTranportBuilder.returnDate(request.getReturnDate());
		groundTranportBuilder.noOfAdults(request.getNoOfAdults());
		groundTranportBuilder.noOfChilds(request.getNoOfChilds());
		groundTranportBuilder.noOfInfants(request.getNoOfInfants());
		groundTranportBuilder.type(request.getType());
		groundTranportBuilder.specialRequests(request.getSpecialRequests());
		groundTranportBuilder.createdBy(request.getCreatedBy());
		groundTranportBuilder.vehicleType(request.getVehicleType());
		groundTranportBuilder.pax(convertGroundTransportEntityToRequest(pax));
		groundTranportBuilder.typeName(request.getTypeName());
		groundTranportBuilder.lineStatusId(request.getLineStatusId());
		groundTranportBuilder.createdDate(request.getCreatedDate());

		groundTranportBuilder.updatedBy(request.getUpdatedBy());
		groundTranportBuilder.updatedDate(request.getUpdatedDate());
		return groundTranportBuilder.build();

	}

//	public static GroundTransportRequest generateGroundTransportResponseData(GTLineEntity request, List<GTPax> pax) {
//
//		GroundTransportRequestBuilder groundTranportBuilder = GroundTransportRequest.builder();
//		groundTranportBuilder.requestId(request.getRequestId());
//		groundTranportBuilder.requestLineId(request.getRequestLineId());
//		groundTranportBuilder.requestLineUuid(request.getRequestLineUuid());
//		groundTranportBuilder.departureCity(request.getDepartureCity());
//		groundTranportBuilder.departureCountry(request.getDepartureCountry());
//		groundTranportBuilder.departureLocation(request.getDepartureLocation());
//		groundTranportBuilder.departureDate(request.getDepartureDate());
//		groundTranportBuilder.arrivalCity(request.getArrivalCity());
//		groundTranportBuilder.arrivalCountry(request.getArrivalCountry());
//		groundTranportBuilder.arrivalLocation(request.getArrivalLocation());
//		groundTranportBuilder.returnDate(request.getReturnDate());
//		groundTranportBuilder.noOfAdults(request.getNoOfAdults());
//		groundTranportBuilder.noOfChilds(request.getNoOfChilds());
//		groundTranportBuilder.noOfInfants(request.getNoOfInfants());
//		groundTranportBuilder.type(request.getType());
//		groundTranportBuilder.specialRequests(request.getSpecialRequests());
//		groundTranportBuilder.createdBy(request.getCreatedBy());
//		groundTranportBuilder.vehicleType(request.getVehicleType());
//		groundTranportBuilder.pax(convertGroundTransportEntityToRequest(pax));
//		groundTranportBuilder.typeName(request.getTypeName());
//		groundTranportBuilder.lineStatusId(request.getLineStatusId());
//		groundTranportBuilder.createdDate(request.getCreatedDate());
//		groundTranportBuilder.lineStatus(request.getLineStatus());
//		groundTranportBuilder.updatedBy(request.getUpdatedBy());
//		groundTranportBuilder.updatedDate(request.getUpdatedDate());
//		return groundTranportBuilder.build();
//
//	}

	public static GTPax convertGroundTransportPaxRequestTOEntity(GroundPaxRequest request) {

		GTPaxBuilder paxBuilder = GTPax.builder();

		paxBuilder.createdBy(request.getCreatedBy());
		paxBuilder.createdDate(new Date());

		paxBuilder.requestId(request.getRequestId());
		paxBuilder.requestLineId(request.getRequestLineId());
		paxBuilder.paxId(request.getPaxId());
		paxBuilder.statusId(request.getStatusId());
		paxBuilder.createdDate(request.getCreatedDate());

		paxBuilder.firstName(request.getFirstName());
		paxBuilder.lastName(request.getLastName());
		paxBuilder.email(request.getEmail());
		paxBuilder.phone(request.getPhone());
		paxBuilder.nationality(request.getNationality());
		paxBuilder.dob(request.getDob());
		paxBuilder.nationality(request.getNationality());
		paxBuilder.issuedCountry(request.getIssuedCountry());
		paxBuilder.paxType(request.getPaxType());
		paxBuilder.passport(request.getPassport());
		paxBuilder.passportExpiredDate(request.getPassportExpiredDate());
		paxBuilder.passportIssueDate(request.getPassportIssueDate());
		paxBuilder.paxIsDeleted(request.getPaxIsDeleted());
		paxBuilder.requestLinePaxId(request.getRequestLinePaxId());
		paxBuilder.nationalityName(request.getNationalityName());
		paxBuilder.issuedCountryName(request.getIssuedCountryName());
		paxBuilder.paxTypeName(request.getPaxTypeName());
		paxBuilder.prefix(request.getPrefix());
		return paxBuilder.build();

	}

	public static GroundPaxRequest convertGroundTransportPaxEntityToRequest(GTPax request) {

		GroundPaxRequestBuilder paxBuilder = GroundPaxRequest.builder();

		paxBuilder.createdBy(request.getCreatedBy());
		paxBuilder.createdDate(request.getCreatedDate());
		paxBuilder.updatedBy(request.getUpdatedBy());
		paxBuilder.updatedDate(request.getUpdatedDate());

		paxBuilder.requestId(request.getRequestId());
		paxBuilder.requestLineId(request.getRequestLineId());
		paxBuilder.paxId(request.getPaxId());
		paxBuilder.statusId(request.getStatusId());

		paxBuilder.firstName(request.getFirstName());
		paxBuilder.lastName(request.getLastName());
		paxBuilder.email(request.getEmail());
		paxBuilder.phone(request.getPhone());
		paxBuilder.nationality(request.getNationality());
		paxBuilder.dob(request.getDob());
		paxBuilder.nationality(request.getNationality());
		paxBuilder.issuedCountry(request.getIssuedCountry());
		paxBuilder.paxType(request.getPaxType());
		paxBuilder.passport(request.getPassport());
		paxBuilder.passportExpiredDate(request.getPassportExpiredDate());
		paxBuilder.passportIssueDate(request.getPassportIssueDate());
		paxBuilder.paxIsDeleted(request.getPaxIsDeleted());
		paxBuilder.requestLinePaxId(request.getRequestLinePaxId());
		paxBuilder.issuedCountryName(request.getIssuedCountryName());
		paxBuilder.nationalityName(request.getNationalityName());
		paxBuilder.paxTypeName(request.getPaxTypeName());
		paxBuilder.prefix(request.getPrefix());
		return paxBuilder.build();

	}

	public static List<GroundPaxRequest> convertGroundTransportEntityToRequest(List<GTPax> request) {

		return request.stream().map(GroundTransportConverter::convertGroundTransportPaxEntityToRequest)
				.collect(Collectors.toList());

	}

	public static void updateGroundTransportPaxRequestTOEntity(GroundPaxRequest request, GTPax paxBuilder) {

		paxBuilder.setRequestId(request.getRequestId());
		paxBuilder.setRequestLineId(request.getRequestLineId());
		if (request.getPaxId() != null)
			paxBuilder.setPaxId(request.getPaxId());
		if (request.getStatusId() != null)
			paxBuilder.setStatusId(request.getStatusId());
		if (request.getFirstName() != null)

			paxBuilder.setFirstName(request.getFirstName());
		if (request.getLastName() != null)
			paxBuilder.setLastName(request.getLastName());
		if (request.getEmail() != null)
			paxBuilder.setEmail(request.getEmail());
		if (request.getPhone() != null)
			paxBuilder.setPhone(request.getPhone());
		if (request.getNationality() != null)
			paxBuilder.setNationality(request.getNationality());
		if (request.getDob() != null)
			paxBuilder.setDob(request.getDob());
		if (request.getNationality() != null)
			paxBuilder.setNationality(request.getNationality());
		if (request.getIssuedCountry() != null)
			paxBuilder.setIssuedCountry(request.getIssuedCountry());
		if (request.getPaxType() != null)
			paxBuilder.setPaxType(request.getPaxType());
		if (request.getPassport() != null)
			paxBuilder.setPassport(request.getPassport());
		if (request.getPassportExpiredDate() != null)
			paxBuilder.setPassportExpiredDate(request.getPassportExpiredDate());
		if (request.getPassportIssueDate() != null)
			paxBuilder.setPassportIssueDate(request.getPassportIssueDate());
		if (request.getPaxIsDeleted() != null)
			paxBuilder.setPaxIsDeleted(request.getPaxIsDeleted());
		if (request.getRequestLinePaxId() != null)
			paxBuilder.setRequestLinePaxId(request.getRequestLinePaxId());
		if (request.getNationalityName() != null)
			paxBuilder.setNationalityName(request.getNationalityName());
		if (request.getIssuedCountryName() != null)
			paxBuilder.setIssuedCountryName(request.getIssuedCountryName());
		if (request.getPaxTypeName() != null)
			paxBuilder.setPaxTypeName(request.getPaxTypeName());
		if (request.getPrefix() != null)
			paxBuilder.setPrefix(request.getPrefix());
		paxBuilder.setUpdatedBy(request.getUpdatedBy());
		paxBuilder.setUpdatedDate(new Date());

	}

	/*
	 * rfq
	 */

//	public static RfqGTLine convertRfqGTRequestToEntity(RfqGroundTransportRequest request) {
//		if (request == null) {
//			return null;
//		}
//
//		RfqGTLineBuilder rfqGTLine = RfqGTLine.builder();
//
//		rfqGTLine.arrivalCity(request.getArrivalCity());
//		rfqGTLine.arrivalCountry(request.getArrivalCountry());
//		rfqGTLine.arrivalLocation(request.getArrivalLocation());
//		rfqGTLine.createdBy(request.getCreatedBy());
//		rfqGTLine.createdDate(request.getCreatedDate());
//		rfqGTLine.departureCity(request.getDepartureCity());
//		rfqGTLine.departureCountry(request.getDepartureCountry());
//		rfqGTLine.departureDate(request.getDepartureDate());
//		rfqGTLine.departureLocation(request.getDepartureLocation());
//		rfqGTLine.lineStatusId(request.getLineStatusId());
//		rfqGTLine.noOfAdults(request.getNoOfAdults());
//		rfqGTLine.noOfChilds(request.getNoOfChilds());
//		rfqGTLine.noOfInfants(request.getNoOfInfants());
//		rfqGTLine.requestId(request.getRequestId());
//		rfqGTLine.requestLineId(request.getRequestLineId());
//		rfqGTLine.returnDate(request.getReturnDate());
//		rfqGTLine.rfqId(request.getRfqId());
//		rfqGTLine.rfqUuid(request.getRfqId() + "-" + request.getRequestId() + "-" + request.getRequestLineId());
//		List<Map<Object, Object>> list = request.getSpecialRequests();
//		if (list != null) {
//			rfqGTLine.specialRequests(new ArrayList<Map<Object, Object>>(list));
//		}
//		rfqGTLine.type(request.getType());
//		rfqGTLine.typeName(request.getTypeName());
//		rfqGTLine.updatedBy(request.getUpdatedBy());
//		rfqGTLine.updatedDate(request.getUpdatedDate());
//		rfqGTLine.vehicleType(request.getVehicleType());
//
//		return rfqGTLine.build();
//	}
//
//	public static RfqGroundTransportRequest convertRfqGTEntityToRequest(RfqGTLine rfq) {
//		if (rfq == null) {
//			return null;
//		}
//
//		RfqGroundTransportRequestBuilder rfqGroundTransportRequest = RfqGroundTransportRequest.builder();
//
//		rfqGroundTransportRequest.arrivalCity(rfq.getArrivalCity());
//		rfqGroundTransportRequest.arrivalCountry(rfq.getArrivalCountry());
//		rfqGroundTransportRequest.arrivalLocation(rfq.getArrivalLocation());
//		rfqGroundTransportRequest.createdBy(rfq.getCreatedBy());
//		rfqGroundTransportRequest.createdDate(rfq.getCreatedDate());
//		rfqGroundTransportRequest.departureCity(rfq.getDepartureCity());
//		rfqGroundTransportRequest.departureCountry(rfq.getDepartureCountry());
//		rfqGroundTransportRequest.departureDate(rfq.getDepartureDate());
//		rfqGroundTransportRequest.departureLocation(rfq.getDepartureLocation());
//		rfqGroundTransportRequest.lineStatusId(rfq.getLineStatusId());
//		rfqGroundTransportRequest.noOfAdults(rfq.getNoOfAdults());
//		rfqGroundTransportRequest.noOfChilds(rfq.getNoOfChilds());
//		rfqGroundTransportRequest.noOfInfants(rfq.getNoOfInfants());
//		rfqGroundTransportRequest.requestId(rfq.getRequestId());
//		rfqGroundTransportRequest.requestLineId(rfq.getRequestLineId());
//		rfqGroundTransportRequest.returnDate(rfq.getReturnDate());
//		rfqGroundTransportRequest.rfqId(rfq.getRfqId());
//		rfqGroundTransportRequest.rfqUuid(rfq.getRfqUuid());
//		List<Map<Object, Object>> list = rfq.getSpecialRequests();
//		if (list != null) {
//			rfqGroundTransportRequest.specialRequests(new ArrayList<Map<Object, Object>>(list));
//		}
//		rfqGroundTransportRequest.type(rfq.getType());
//		rfqGroundTransportRequest.typeName(rfq.getTypeName());
//		rfqGroundTransportRequest.updatedBy(rfq.getUpdatedBy());
//		rfqGroundTransportRequest.updatedDate(rfq.getUpdatedDate());
//		rfqGroundTransportRequest.vehicleType(rfq.getVehicleType());
//
//		return rfqGroundTransportRequest.build();
//	}
//
//	public static List<RfqGTLine> convertRfqGTRequestsToEntities(List<RfqGroundTransportRequest> request) {
//		if (request == null) {
//			return null;
//		}
//
//		List<RfqGTLine> list = new ArrayList<RfqGTLine>(request.size());
//		for (RfqGroundTransportRequest rfqGroundTransportRequest : request) {
//			list.add(convertRfqGTRequestToEntity(rfqGroundTransportRequest));
//		}
//
//		return list;
//	}
//
//	public static List<RfqGroundTransportRequest> convertRfqGTEntitiesToRequests(List<RfqGTLine> rfq) {
//		if (rfq == null) {
//			return null;
//		}
//
//		List<RfqGroundTransportRequest> list = new ArrayList<RfqGroundTransportRequest>(rfq.size());
//		for (RfqGTLine rfqGTLine : rfq) {
//			list.add(convertRfqGTEntityToRequest(rfqGTLine));
//		}
//
//		return list;
//	}
//
//	public static RfqGTPax convertRfqGTPaxRequestToEntity(RfqGTPaxRequest request) {
//		if (request == null) {
//			return null;
//		}
//
//		RfqGTPaxBuilder rfqGTPax = RfqGTPax.builder();
//
//		rfqGTPax.createdBy(request.getCreatedBy());
//		rfqGTPax.createdDate(request.getCreatedDate());
//		rfqGTPax.dob(request.getDob());
//		rfqGTPax.email(request.getEmail());
//		rfqGTPax.firstName(request.getFirstName());
//		rfqGTPax.issuedCountry(request.getIssuedCountry());
//		rfqGTPax.issuedCountryName(request.getIssuedCountryName());
//		rfqGTPax.lastName(request.getLastName());
//		rfqGTPax.nationality(request.getNationality());
//		rfqGTPax.nationalityName(request.getNationalityName());
//		rfqGTPax.passport(request.getPassport());
//		rfqGTPax.passportExpiredDate(request.getPassportExpiredDate());
//		rfqGTPax.passportIssueDate(request.getPassportIssueDate());
//		rfqGTPax.paxId(request.getPaxId());
//		rfqGTPax.paxIsDeleted(request.getPaxIsDeleted());
//		rfqGTPax.paxType(request.getPaxType());
//		rfqGTPax.paxTypeName(request.getPaxTypeName());
//		rfqGTPax.phone(request.getPhone());
//		rfqGTPax.requestId(request.getRequestId());
//		rfqGTPax.requestLineId(request.getRequestLineId());
//		rfqGTPax.rfqId(request.getRfqId());
//		rfqGTPax.statusId(request.getStatusId());
//		rfqGTPax.updatedBy(request.getUpdatedBy());
//		rfqGTPax.updatedDate(request.getUpdatedDate());
//		rfqGTPax.prefix(request.getPrefix());
//		return rfqGTPax.build();
//	}
//
//	public static RfqGTPaxRequest convertRfqGTPaxEntityTORequest(RfqGTPax pax) {
//		if (pax == null) {
//			return null;
//		}
//
//		RfqGTPaxRequestBuilder gTRfqPaxRequest = RfqGTPaxRequest.builder();
//
//		gTRfqPaxRequest.createdBy(pax.getCreatedBy());
//		gTRfqPaxRequest.createdDate(pax.getCreatedDate());
//		gTRfqPaxRequest.dob(pax.getDob());
//		gTRfqPaxRequest.email(pax.getEmail());
//		gTRfqPaxRequest.firstName(pax.getFirstName());
//		gTRfqPaxRequest.issuedCountry(pax.getIssuedCountry());
//		gTRfqPaxRequest.issuedCountryName(pax.getIssuedCountryName());
//		gTRfqPaxRequest.lastName(pax.getLastName());
//		gTRfqPaxRequest.nationality(pax.getNationality());
//		gTRfqPaxRequest.nationalityName(pax.getNationalityName());
//		gTRfqPaxRequest.passport(pax.getPassport());
//		gTRfqPaxRequest.passportExpiredDate(pax.getPassportExpiredDate());
//		gTRfqPaxRequest.passportIssueDate(pax.getPassportIssueDate());
//		gTRfqPaxRequest.paxId(pax.getPaxId());
//		gTRfqPaxRequest.paxIsDeleted(pax.getPaxIsDeleted());
//		gTRfqPaxRequest.paxType(pax.getPaxType());
//		gTRfqPaxRequest.paxTypeName(pax.getPaxTypeName());
//		gTRfqPaxRequest.phone(pax.getPhone());
//		gTRfqPaxRequest.requestId(pax.getRequestId());
//		gTRfqPaxRequest.requestLineId(pax.getRequestLineId());
//		gTRfqPaxRequest.rfqId(pax.getRfqId());
//		gTRfqPaxRequest.statusId(pax.getStatusId());
//		gTRfqPaxRequest.updatedBy(pax.getUpdatedBy());
//		gTRfqPaxRequest.updatedDate(pax.getUpdatedDate());
//		gTRfqPaxRequest.prefix(pax.getPrefix());
//		return gTRfqPaxRequest.build();
//	}
//
//	public static List<RfqGTPax> convertRfqGTPaxRequestsToEntities(List<RfqGTPaxRequest> request, Integer rfqId) {
//		if (request == null) {
//			return null;
//		}
//
//		List<RfqGTPax> list = new ArrayList<RfqGTPax>(request.size());
//		for (RfqGTPaxRequest rfqGTPaxRequest : request) {
//			rfqGTPaxRequest.setRfqId(rfqId);
//			list.add(convertRfqGTPaxRequestToEntity(rfqGTPaxRequest));
//		}
//
//		return list;
//	}
//
//	public static List<RfqGTPaxRequest> convertRfqGTPaxEntitiesTORequests(List<RfqGTPax> pax) {
//		if (pax == null) {
//			return null;
//		}
//
//		List<RfqGTPaxRequest> list = new ArrayList<RfqGTPaxRequest>(pax.size());
//		for (RfqGTPax rfqGTPax : pax) {
//			list.add(convertRfqGTPaxEntityTORequest(rfqGTPax));
//		}
//
//		return list;
//	}
//
//	public static void updateRfqGTRequestToEntity(RfqGroundTransportRequest request, RfqGTLine rfqGt) {
//		if (request == null) {
//			return;
//		}
//
//		if (request.getArrivalCity() != null) {
//			rfqGt.setArrivalCity(request.getArrivalCity());
//		}
//		if (request.getArrivalCountry() != null) {
//			rfqGt.setArrivalCountry(request.getArrivalCountry());
//		}
//		if (request.getArrivalLocation() != null) {
//			rfqGt.setArrivalLocation(request.getArrivalLocation());
//		}
//		if (request.getDepartureCity() != null) {
//			rfqGt.setDepartureCity(request.getDepartureCity());
//		}
//		if (request.getDepartureCountry() != null) {
//			rfqGt.setDepartureCountry(request.getDepartureCountry());
//		}
//		if (request.getDepartureDate() != null) {
//			rfqGt.setDepartureDate(request.getDepartureDate());
//		}
//		if (request.getDepartureLocation() != null) {
//			rfqGt.setDepartureLocation(request.getDepartureLocation());
//		}
//		if (request.getLineStatusId() != null) {
//			rfqGt.setLineStatusId(request.getLineStatusId());
//		}
//		if (request.getNoOfAdults() != null) {
//			rfqGt.setNoOfAdults(request.getNoOfAdults());
//		}
//		if (request.getNoOfChilds() != null) {
//			rfqGt.setNoOfChilds(request.getNoOfChilds());
//		}
//		if (request.getNoOfInfants() != null) {
//			rfqGt.setNoOfInfants(request.getNoOfInfants());
//		}
//		if (request.getRequestId() != null) {
//			rfqGt.setRequestId(request.getRequestId());
//		}
//		if (request.getRequestLineId() != null) {
//			rfqGt.setRequestLineId(request.getRequestLineId());
//		}
//		if (request.getReturnDate() != null) {
//			rfqGt.setReturnDate(request.getReturnDate());
//		}
//		if (request.getRfqId() != null) {
//			rfqGt.setRfqId(request.getRfqId());
//		}
//		if (request.getRfqUuid() != null) {
//			rfqGt.setRfqUuid(request.getRfqUuid());
//		}
//		if (rfqGt.getSpecialRequests() != null) {
//			List<Map<Object, Object>> list = request.getSpecialRequests();
//			if (list != null) {
//				rfqGt.getSpecialRequests().clear();
//				rfqGt.getSpecialRequests().addAll(list);
//			}
//		}
//		if (request.getType() != null) {
//			rfqGt.setType(request.getType());
//		}
//		if (request.getTypeName() != null) {
//			rfqGt.setTypeName(request.getTypeName());
//		}
//		if (request.getUpdatedBy() != null) {
//			rfqGt.setUpdatedBy(request.getUpdatedBy());
//		}
//		if (request.getUpdatedDate() != null) {
//			rfqGt.setUpdatedDate(request.getUpdatedDate());
//		}
//		if (request.getVehicleType() != null) {
//			rfqGt.setVehicleType(request.getVehicleType());
//		}
//	}
//
//	public static RfqGroundTransportRequest generateRfqGtResponse(RfqGTLine line, List<RfqGTPax> savedRfqPax,
//			List<RfqGTSupplierMapping> rfqSupplierRelation) {
//		RfqGroundTransportRequest generatedResponse = convertRfqGTEntityToRequest(line);
//		generatedResponse.setRfqPax(convertRfqGTPaxEntitiesTORequests(savedRfqPax));
//		generatedResponse.setRfqSupplierRelation(rfqSupplierRelation);
//		return generatedResponse;
//	}

}
