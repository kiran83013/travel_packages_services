package com.travel.travtronics.converter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.travel.travtronics.request.dto.HolidayPassengersRequest;
import com.travel.travtronics.request.dto.HolidayPaxModel;
import com.travel.travtronics.request.dto.HolidayRoomsRequest;
import com.travel.travtronics.request.dto.HolidayServiceRequestHeader;
import com.travel.travtronics.request.dto.HolidayServiceRequestSegments;
import com.travel.travtronics.response.HolidayResponse;
import com.travel.travtronics.response.dto.HolidayRequestLineResponse;
import com.travel.travtronics.response.dto.HolidaySegmentResponse;
import com.travel.travtronics.srm.model.HolidayPackagePassengersModel;
import com.travel.travtronics.srm.model.HolidayPackageRoomsModel;
import com.travel.travtronics.srm.model.HolidayRequestLine;
import com.travel.travtronics.srm.model.HolidayRequestPax;
import com.travel.travtronics.srm.model.HolidayRequestSegments;

public class HolidayPackageConverter {

	public static HolidayRequestPax convertDtoToEntity(HolidayPaxModel paxModel, Long requestLineId) {

		HolidayRequestPax pax = new HolidayRequestPax();

		pax.setPaxId(paxModel.getPaxId());
		pax.setPrefix(paxModel.getPrefix());
		pax.setFirstName(paxModel.getFirstName());
		pax.setLastName(paxModel.getLastName());
		pax.setEmail(paxModel.getEmail());
		pax.setPhone(paxModel.getPhone());
		pax.setDob(paxModel.getDob());
		pax.setNationality(paxModel.getNationality());
		pax.setNationlaityName(paxModel.getNationlaityName());
		pax.setIssuedCountry(paxModel.getIssuedCountry());
		pax.setIssuedCountryName(paxModel.getIssuedCountryName());
		pax.setPassport(paxModel.getPassport());
		pax.setPaxType(paxModel.getPaxType());
		pax.setPaxCode(paxModel.getPaxCode());
		pax.setPassportExpiredDate(paxModel.getPassportExpiredDate());
		pax.setPassportIssueDate(paxModel.getPassportIssueDate());
		pax.setPaxIsDeleted(paxModel.getPaxIsDeleted());
		pax.setRequestId(paxModel.getRequestId());
		pax.setStatusId(paxModel.getStatusId());
		pax.setCreatedBy(paxModel.getCreatedBy());
		pax.setUpdatedBy(paxModel.getUpdatedBy());
		pax.setRequestLineId(requestLineId);
		pax.setAssign(paxModel.getAssign());
		return pax;

	}

	public static HolidayRequestLine createHolidayRequestLine(HolidayServiceRequestHeader lineHeader,
			HolidayServiceRequestSegments segemnts, Long requestLineId) {
		HolidayRequestLine line = new HolidayRequestLine();
		// line.setLineUuid(UUID.randomUUID().toString().replace("-", ""));
		line.setLineUuid(String.valueOf(lineHeader.getRequestId()) + "-" + String.valueOf(requestLineId));
		line.setCreatedBy(lineHeader.getCreatedBy());
		line.setPassengerTypeId(lineHeader.getPassengerTypeId());

		line.setCreatedDate(lineHeader.getCreatedDate());
		line.setDealCode(lineHeader.getDealCode());
		line.setExpandableParametersCode(lineHeader.getExpandableParametersCode());

		line.setRequestLineId(requestLineId);
		line.setRequestId(lineHeader.getRequestId());
		line.setAddons(segemnts.getAddons());
		line.setHolidaysCount(Integer.valueOf(segemnts.getHolidayDays()));
		line.setNoofAdt(segemnts.getNoofAdt());
		line.setNoofChd(segemnts.getNoofChd());
		line.setNoofInf(segemnts.getNoofInf());
		line.setLpoAmount(segemnts.getLpoAmount());
		line.setLpoDate(segemnts.getLpoDate());
		line.setLpoNumber(segemnts.getLpoNumber());
		line.setLpoDate(segemnts.getLpoDate());
		line.setLpoNumber(segemnts.getLpoNumber());
		line.setAttractions(segemnts.getAttractions());
		line.setAirlineCode(segemnts.getAirlineCode());
		line.setExclusions(segemnts.getExclusions());
		line.setLineStatusId(15); // Open status
		line.setHotelSelectionData(segemnts.getHotelSelectionData());
		line.setHotelSelectionType(segemnts.getHotelSelectionType());
		line.setHospitalSelectionData(segemnts.getHospitalSelectionData());
		line.setHospitalSelectionType(segemnts.getHospitalSelectionType());
		line.setTransportFlag(Objects.isNull(segemnts.getTransportFlag()) ? false : segemnts.getTransportFlag());
		line.setAccomodationHotelFlag(
				Objects.isNull(segemnts.getAccomodationHotelFlag()) ? false : segemnts.getAccomodationHotelFlag());
		line.setAccomodationHospitalFlag(Objects.isNull(segemnts.getAccomodationHospitalFlag()) ? false
				: segemnts.getAccomodationHospitalFlag());
		return line;
	}

	public static HolidayRequestSegments createSegmentsEntity(HolidayServiceRequestSegments sements,
			Long requestLineId) {
		HolidayRequestSegments model = new HolidayRequestSegments();
		model.setRequestId(sements.getRequestId());
		model.setRequestLineId(requestLineId);
		model.setFromCode(sements.getFromCode());
		model.setToCode(sements.getToCode());
		model.setDepatureDate(sements.getDepatureDate());
		model.setFromAirportOrCityName(sements.getFromAirportOrCityName());
		model.setFromCountryName(sements.getFromCountryName());
		model.setToAirportOrCityName(sements.getToAirportOrCityName());
		model.setToCountryName(sements.getToCountryName());
		model.setClassName(sements.getClassName());
		model.setFlexClassName(sements.getFlexClassName());
		model.setRbd(sements.getRbd());
		model.setFlexDepature(sements.getFlexDepature());
		model.setFlexReturn(sements.getFlexReturn());
		model.setReturnDate(sements.getReturnDate());
		model.setValidateCarrier(sements.getValidateCarrier());
		model.setBudgetFrom(sements.getBudgetFrom());
		model.setBudgetTo(sements.getBudgetTo());
		model.setTransitPointCode(sements.getTransitPointCode());
		model.setExcludePointCode(sements.getExcludePointCode());
		model.setCreatedBy(sements.getCreatedBy());
		model.setTeamLeader(sements.getTeamLeader());
		model.setHolidayDays(sements.getHolidayDays());
		model.setHotelRatings(sements.getHotelRatings());
		model.setModeOfTransport(sements.getModeOfTransport());
		model.setAirlineCode(sements.getAirlineCode());
		model.setLineRoomCount(sements.getLineRoomCount());
		model.setFlexAirLineCode(sements.getFlexAirLineCode());
		model.setFlexFromCode(sements.getFlexFromCode());
		model.setFlexToCode(sements.getFlexToCode());
		return model;
	}

	public static List<HolidayPaxModel> convertEntityToDto(List<HolidayRequestPax> paxModel) {

		return paxModel.stream().map(person -> {

			HolidayPaxModel pax = new HolidayPaxModel();

			pax.setRequestLinePaxId(person.getRequestLinePaxId());
			pax.setRequestLinePaxId(person.getRequestLinePaxId());
			pax.setPaxId(person.getPaxId());
			pax.setPrefix(person.getPrefix());
			pax.setFirstName(person.getFirstName());
			pax.setLastName(person.getLastName());
			pax.setEmail(person.getEmail());
			pax.setPhone(person.getPhone());
			pax.setDob(person.getDob());
			pax.setNationality(person.getNationality());
			pax.setNationlaityName(person.getNationlaityName());
			pax.setIssuedCountry(person.getIssuedCountry());
			pax.setIssuedCountryName(person.getIssuedCountryName());
			pax.setPassport(person.getPassport());
			pax.setPaxType(person.getPaxType());
			pax.setPaxCode(person.getPaxCode());
			pax.setPassportExpiredDate(person.getPassportExpiredDate());
			pax.setPassportIssueDate(person.getPassportIssueDate());
			pax.setPaxIsDeleted(person.getPaxIsDeleted());
			pax.setRequestId(person.getRequestId());
			pax.setStatusId(person.getStatusId());
			pax.setCreatedBy(person.getCreatedBy());
			pax.setUpdatedBy(person.getUpdatedBy());
			pax.setCreatedDate(person.getCreatedDate());
			pax.setUpdatedDate(person.getUpdatedDate());
			pax.setRequestLineId(person.getRequestLineId());
			pax.setAssign(person.getAssign());
			return pax;
		}).collect(Collectors.toList());

	}

	public static HolidayRequestLineResponse convertLineEntityToDto(HolidayRequestLine line) {
		HolidayRequestLineResponse response = new HolidayRequestLineResponse();
		response.setLineUuid(line.getLineUuid());
		response.setCreatedBy(line.getCreatedBy());
		response.setPassengerTypeId(line.getPassengerTypeId());
		response.setLineStatusId(line.getLineStatusId());
		response.setCreatedDate(line.getCreatedDate());
		response.setDealCode(line.getDealCode());
		response.setExpandableParametersCode(line.getExpandableParametersCode());
		response.setRequestLineId(line.getRequestLineId());
		response.setRequestId(line.getRequestId());
		response.setAddons(line.getAddons());
		response.setNoofAdt(line.getNoofAdt());
		response.setNoofChd(line.getNoofChd());
		response.setNoofInf(line.getNoofInf());
		response.setLpoAmount(line.getLpoAmount());
		response.setLpoDate(line.getLpoDate());
		response.setLpoNumber(line.getLpoNumber());
		response.setUpdatedBy(line.getUpdatedBy());
		response.setUpdatedDate(line.getUpdatedDate());
		response.setAttractions(line.getAttractions());
		response.setAirlineCode(line.getAirlineCode());
		response.setExclusions(line.getExclusions());
		response.setHotelSelectionData(line.getHotelSelectionData());
		response.setHotelSelectionType(line.getHotelSelectionType());
		response.setHospitalSelectionData(line.getHospitalSelectionData());
		response.setHospitalSelectionType(line.getHospitalSelectionType());
		response.setTransportFlag(line.getTransportFlag());
		response.setAccomodationHotelFlag(line.getAccomodationHotelFlag());
		response.setAccomodationHospitalFlag(line.getAccomodationHospitalFlag());
		return response;

	}

	public static HolidaySegmentResponse createSegmentsDtofromEntity(HolidayRequestSegments sements) {
		HolidaySegmentResponse model = new HolidaySegmentResponse();

		model.setRequestId(sements.getRequestId());
		model.setRequestLineId(sements.getRequestLineId());
		model.setFromCode(sements.getFromCode());
		model.setToCode(sements.getToCode());
		model.setDepatureDate(sements.getDepatureDate());
		model.setFromAirportOrCityName(sements.getFromAirportOrCityName());
		model.setFromCountryName(sements.getFromCountryName());
		model.setToAirportOrCityName(sements.getToAirportOrCityName());
		model.setToCountryName(sements.getToCountryName());
		model.setClassName(sements.getClassName());
		model.setFlexClassName(sements.getFlexClassName());
		model.setRbd(sements.getRbd());
		model.setFlexDepature(sements.getFlexDepature());
		model.setFlexReturn(sements.getFlexReturn());
		model.setReturnDate(sements.getReturnDate());
		if (sements.getValidateCarrier() != null)
			model.setValidateCarrier(sements.getValidateCarrier());
		else
			model.setValidateCarrier(false);
		model.setBudgetFrom(sements.getBudgetFrom());
		model.setBudgetTo(sements.getBudgetTo());
		model.setTransitPointCode(sements.getTransitPointCode());
		model.setExcludePointCode(sements.getExcludePointCode());
		model.setCreatedBy(sements.getCreatedBy());
		model.setTeamLeader(sements.getTeamLeader());
		model.setHolidayDays(sements.getHolidayDays());
		model.setHotelRatings(sements.getHotelRatings());
		model.setModeOfTransport(sements.getModeOfTransport());
		model.setRequestSegmentId(sements.getRequestSegmentId());
		model.setPropertyType(sements.getPropertyType());
		model.setLineRoomCount(sements.getLineRoomCount());
		model.setFlexFromCode(sements.getFlexFromCode());
		model.setFlexToCode(sements.getFlexToCode());
		model.setFlexAirLineCode(sements.getFlexAirLineCode());
		return model;

	}

	public static HolidayRequestLine modifyHolidayRequestLine(HolidayServiceRequestHeader lineHeader,
			HolidayServiceRequestSegments segemnts, HolidayRequestLine line) {
		line.setLineUuid(segemnts.getLineUuid());
		line.setRequestLineId(segemnts.getRequestLineId());
		line.setRequestId(lineHeader.getRequestId());
		line.setPassengerTypeId(lineHeader.getPassengerTypeId());
		line.setLineStatusId(lineHeader.getLineStatusId());
		line.setDealCode(lineHeader.getDealCode());
		line.setExpandableParametersCode(lineHeader.getExpandableParametersCode());

		line.setAddons(segemnts.getAddons());
		line.setNoofAdt(segemnts.getNoofAdt());
		line.setNoofChd(segemnts.getNoofChd());
		line.setNoofInf(segemnts.getNoofInf());
		line.setLpoAmount(segemnts.getLpoAmount());
		line.setLpoDate(segemnts.getLpoDate());
		line.setLpoNumber(segemnts.getLpoNumber());
		line.setUpdatedDate(lineHeader.getUpdatedDate());
		line.setUpdatedBy(lineHeader.getUpdatedBy());
		line.setAttractions(segemnts.getAttractions());
		line.setAirlineCode(segemnts.getAirlineCode());
		line.setExclusions(segemnts.getExclusions());
		line.setHotelSelectionData(segemnts.getHotelSelectionData());
		line.setHotelSelectionType(segemnts.getHotelSelectionType());
		line.setHospitalSelectionData(segemnts.getHospitalSelectionData());
		line.setHospitalSelectionType(segemnts.getHospitalSelectionType());
		return line;
	}

	public static HolidayRequestSegments modifySegmentsEntity(HolidayServiceRequestSegments sements,
			HolidayRequestSegments model) {
		model.setRequestId(sements.getRequestId());
		model.setRequestLineId(sements.getRequestLineId());
		model.setRequestSegmentId(sements.getRequestSegmentId());
		model.setFromCode(sements.getFromCode());
		model.setToCode(sements.getToCode());
		model.setDepatureDate(sements.getDepatureDate());
		model.setFromAirportOrCityName(sements.getFromAirportOrCityName());
		model.setFromCountryName(sements.getFromCountryName());
		model.setToAirportOrCityName(sements.getToAirportOrCityName());
		model.setToCountryName(sements.getToCountryName());
		model.setClassName(sements.getClassName());
		model.setFlexClassName(sements.getFlexClassName());
		model.setRbd(sements.getRbd());
		model.setFlexDepature(sements.getFlexDepature());
		model.setFlexReturn(sements.getFlexReturn());
		model.setReturnDate(sements.getReturnDate());
		model.setValidateCarrier(sements.getValidateCarrier());
		model.setBudgetFrom(sements.getBudgetFrom());
		model.setBudgetTo(sements.getBudgetTo());
		model.setTransitPointCode(sements.getTransitPointCode());
		model.setExcludePointCode(sements.getExcludePointCode());
		model.setCreatedBy(sements.getCreatedBy());
		model.setTeamLeader(sements.getTeamLeader());
		model.setHolidayDays(sements.getHolidayDays());
		model.setHotelRatings(sements.getHotelRatings());
		model.setModeOfTransport(sements.getModeOfTransport());
		model.setLineRoomCount(sements.getLineRoomCount());
		return model;
	}

	public static HolidayRequestPax modifyDtoToEntity(HolidayPaxModel paxModel, Long requestLineId,
			HolidayRequestPax pax) {

		pax.setRequestLinePaxId(pax.getRequestLinePaxId());

		pax.setPaxId(paxModel.getPaxId());
		pax.setPrefix(paxModel.getPrefix());
		pax.setFirstName(paxModel.getFirstName());
		pax.setLastName(paxModel.getLastName());
		pax.setEmail(paxModel.getEmail());
		pax.setPhone(paxModel.getPhone());
		pax.setDob(paxModel.getDob());
		pax.setNationality(paxModel.getNationality());
		pax.setNationlaityName(paxModel.getNationlaityName());
		pax.setIssuedCountry(paxModel.getIssuedCountry());
		pax.setIssuedCountryName(paxModel.getIssuedCountryName());
		pax.setPassport(paxModel.getPassport());
		pax.setPaxType(paxModel.getPaxType());
		pax.setPaxCode(paxModel.getPaxCode());
		pax.setPassportExpiredDate(paxModel.getPassportExpiredDate());
		pax.setPassportIssueDate(paxModel.getPassportIssueDate());
		pax.setPaxIsDeleted(paxModel.getPaxIsDeleted());
		pax.setRequestId(paxModel.getRequestId());
		pax.setStatusId(paxModel.getStatusId());
		pax.setCreatedBy(paxModel.getCreatedBy());
		pax.setUpdatedBy(paxModel.getUpdatedBy());
		pax.setRequestLineId(requestLineId);
		pax.setAssign(paxModel.getAssign());
		return pax;

	}

	public static HolidayResponse generateResponseForHolidayInfo(HolidayRequestLineResponse line,
			HolidaySegmentResponse serviceSegments, List<HolidayPaxModel> holidayPersonList,
			List<HolidayRoomsRequest> roomData) {

		HolidayResponse response = new HolidayResponse();

		response.setLineUuid(line.getLineUuid());
		response.setCreatedBy(line.getCreatedBy());
		response.setPassengerTypeId(line.getPassengerTypeId());
		response.setLineStatusId(line.getLineStatusId());
		response.setCreatedDate(line.getCreatedDate());
		response.setDealCode(line.getDealCode());
		response.setExpandableParametersCode(line.getExpandableParametersCode());
		response.setRequestLineId(line.getRequestLineId());
		response.setRequestId(line.getRequestId());
		response.setAddons(line.getAddons());
		response.setNoofAdt(line.getNoofAdt());
		response.setNoofChd(line.getNoofChd());
		response.setNoofInf(line.getNoofInf());
		response.setLpoAmount(line.getLpoAmount());
		response.setLpoDate(line.getLpoDate());
		response.setLpoNumber(line.getLpoNumber());
		response.setUpdatedBy(line.getUpdatedBy());
		response.setUpdatedDate(line.getUpdatedDate());
		response.setAttractions(line.getAttractions());
		response.setAirlineCode(line.getAirlineCode());
		response.setExclusions(line.getExclusions());
		response.setHotelSelectionData(line.getHotelSelectionData());
		response.setHotelSelectionType(line.getHotelSelectionType());
		response.setHospitalSelectionData(line.getHospitalSelectionData());
		response.setHospitalSelectionType(line.getHospitalSelectionType());
		response.setTransportFlag(line.getTransportFlag());
		response.setAccomodationHotelFlag(line.getAccomodationHotelFlag());
		response.setAccomodationHospitalFlag(line.getAccomodationHospitalFlag());
		if (Objects.nonNull(serviceSegments))
			response.setServiceSegments(serviceSegments);
		if (CollectionUtils.isNotEmpty(holidayPersonList))
			response.setHolidayPersonList(holidayPersonList);
		if (CollectionUtils.isNotEmpty(roomData))
			response.setRoomData(roomData);
		return response;

	}

	/*
	 * room and passengers mappers
	 */

	public static HolidayPackageRoomsModel convertHolidayRoomJsonToEntity(HolidayRoomsRequest roomRequest,
			Long roomLineId) {

		HolidayPackageRoomsModel entity = new HolidayPackageRoomsModel();
		entity.setRoomName(roomRequest.getRoomName());
		entity.setRoomType(roomRequest.getRoomType());
		entity.setRoomAddonsRequired(roomRequest.getRoomAddonsRequired());
		entity.setRoomAdultCount(roomRequest.getRoomAdultCount());
		entity.setRoomAttr1(roomRequest.getRoomAttr1());
		entity.setRoomAttr2(roomRequest.getRoomAttr2());
		entity.setRoomAttr3(roomRequest.getRoomAttr3());

		entity.setRoomAttr4(roomRequest.getRoomAttr4());

		entity.setRoomAttr5(roomRequest.getRoomAttr5());
		entity.setRoomAttr6(roomRequest.getRoomAttr6());
		entity.setRoomAttr7(roomRequest.getRoomAttr7());
		entity.setRoomAttr8(roomRequest.getRoomAttr8());
		entity.setRoomAttr9(roomRequest.getRoomAttr9());
		entity.setRoomAttr10(roomRequest.getRoomAttr10());
		entity.setRoomChildAges(roomRequest.getRoomChildAges());
		entity.setRoomChildCount(roomRequest.getRoomChildCount());
		entity.setRoomInfantAges(roomRequest.getRoomInfantAges());
		entity.setRoomInfantCount(roomRequest.getRoomInfantCount());
		entity.setRoomIsDeleted(roomRequest.getRoomIsDeleted());
		entity.setRoomCreatedBy(roomRequest.getRoomCreatedBy());
		entity.setRoomCreatedDate(roomRequest.getRoomCreatedDate());
		entity.setRoomCreatedDevice(roomRequest.getRoomCreatedDevice());
		entity.setRoomCreatedIp(roomRequest.getRoomCreatedIp());
		entity.setRoomLineId(roomLineId.intValue());
		entity.setRoomSrId(roomRequest.getRoomSrId());
		entity.setRoomStatus(roomRequest.getRoomStatus());
		entity.setRoomNumber(roomRequest.getRoomNumber());
		return entity;

	}

	public static HolidayPackageRoomsModel updateHolidayRoomJsonToEntity(HolidayRoomsRequest roomRequest,
			Long roomLineId, HolidayPackageRoomsModel entity) {

		if (Objects.nonNull(roomRequest.getId()) && roomRequest.getId() != 0)
			entity.setId(roomRequest.getId());
		else
			entity.setId(0);
		entity.setRoomName(roomRequest.getRoomName());
		entity.setRoomType(roomRequest.getRoomType());
		entity.setRoomAddonsRequired(roomRequest.getRoomAddonsRequired());
		entity.setRoomAdultCount(roomRequest.getRoomAdultCount());
		entity.setRoomAttr1(roomRequest.getRoomAttr1());
		entity.setRoomAttr2(roomRequest.getRoomAttr2());
		entity.setRoomAttr3(roomRequest.getRoomAttr3());

		entity.setRoomAttr4(roomRequest.getRoomAttr4());

		entity.setRoomAttr5(roomRequest.getRoomAttr5());
		entity.setRoomAttr6(roomRequest.getRoomAttr6());
		entity.setRoomAttr7(roomRequest.getRoomAttr7());
		entity.setRoomAttr8(roomRequest.getRoomAttr8());
		entity.setRoomAttr9(roomRequest.getRoomAttr9());
		entity.setRoomAttr10(roomRequest.getRoomAttr10());
		entity.setRoomChildAges(roomRequest.getRoomChildAges());
		entity.setRoomChildCount(roomRequest.getRoomChildCount());
		entity.setRoomInfantAges(roomRequest.getRoomInfantAges());
		entity.setRoomInfantCount(roomRequest.getRoomInfantCount());
		entity.setRoomIsDeleted(roomRequest.getRoomIsDeleted());
		entity.setRoomUpdatedBy(roomRequest.getRoomCreatedBy());
		entity.setRoomUpdatedDate(roomRequest.getRoomCreatedDate());
		entity.setRoomUpdatedDevice(roomRequest.getRoomCreatedDevice());
		entity.setRoomUpdatedIp(roomRequest.getRoomCreatedIp());
		entity.setRoomLineId(roomLineId.intValue());
		entity.setRoomSrId(roomRequest.getRoomSrId());
		entity.setRoomStatus(roomRequest.getRoomStatus());
		entity.setRoomNumber(roomRequest.getRoomNumber());

		return entity;
	}

	public static List<HolidayPackagePassengersModel> convertHolidayPassengersJsonToEntity(
			List<HolidayPassengersRequest> roomPassengersInfo, Integer roomId, Long passengerLineId) {

		return roomPassengersInfo.stream().map(eachpax -> {
			HolidayPackagePassengersModel entity = new HolidayPackagePassengersModel();
			if (eachpax.getId() != null && eachpax.getId() != 0)
				entity.setId(entity.getId());
			else
				entity.setId(0);

			entity.setPassengerAddonsRequired(eachpax.getPassengerAddonsRequired());
			entity.setPassengerAttr1(eachpax.getPassengerAttr1());
			entity.setPassengerAttr2(eachpax.getPassengerAttr2());
			entity.setPassengerAttr3(eachpax.getPassengerAttr3());
			entity.setPassengerAttr4(eachpax.getPassengerAttr4());
			entity.setPassengerAttr5(eachpax.getPassengerAttr5());
			entity.setPassengerAttr6(eachpax.getPassengerAttr6());
			entity.setPassengerAttr7(eachpax.getPassengerAttr7());
			entity.setPassengerAttr8(eachpax.getPassengerAttr8());
			entity.setPassengerAttr9(eachpax.getPassengerAttr9());
			entity.setPassengerAttr10(eachpax.getPassengerAttr10());
			entity.setPassengerCountry(eachpax.getPassengerCountry());
			entity.setPassengerCountryResidency(eachpax.getPassengerCountryResidency());
			entity.setPassengerCreatedBy(eachpax.getPassengerCreatedBy());
			entity.setPassengerCreatedDevice(eachpax.getPassengerCreatedDevice());
			entity.setPassengerCreatedIp(eachpax.getPassengerCreatedIp());
			entity.setPassengerCreatedDate(eachpax.getPassengerCreatedDate());
			entity.setPassengerFirstName(eachpax.getPassengerFirstName());
			entity.setPassengerLastName(eachpax.getPassengerLastName());
			entity.setPassengerMiddleName(eachpax.getPassengerMiddleName());
			entity.setPassengerEmail(eachpax.getPassengerEmail());
			entity.setPassengerIsDeleted(eachpax.getPassengerIsDeleted());
			entity.setPassengerNationality(eachpax.getPassengerNationality());
			entity.setPassengerPaxId(eachpax.getPassengerPaxId());
			entity.setPassengerPhone(eachpax.getPassengerPhone());
			entity.setPassengerRoomId(roomId);
			entity.setPassengerLineId(passengerLineId.intValue());
			entity.setPassengerSrId(eachpax.getPassengerSrId());
			entity.setPassengerStatus(eachpax.getPassengerStatus());
			entity.setPassengerType(eachpax.getPassengerType());
			entity.setPassengerTitle(eachpax.getPassengerTitle());

			entity.setPassengerUpdatedBy(eachpax.getPassengerUpdatedBy());
			entity.setPassengerUpdatedDevice(eachpax.getPassengerUpdatedDevice());
			entity.setPassengerUpdatedIp(eachpax.getPassengerUpdatedIp());
			entity.setPassengerUpdatedDate(eachpax.getPassengerUpdatedDate());
			return entity;

		}).collect(Collectors.toList());

	}

	public static List<HolidayPassengersRequest> convertHolidayPassengersEntityToJson(
			List<HolidayPackagePassengersModel> pasengersInfo) {
		return pasengersInfo.stream().map(eachpax -> {
			HolidayPassengersRequest entity = new HolidayPassengersRequest();

			entity.setId(eachpax.getId());

			entity.setPassengerAddonsRequired(eachpax.getPassengerAddonsRequired());
			entity.setPassengerAttr1(eachpax.getPassengerAttr1());
			entity.setPassengerAttr2(eachpax.getPassengerAttr2());
			entity.setPassengerAttr3(eachpax.getPassengerAttr3());
			entity.setPassengerAttr4(eachpax.getPassengerAttr4());
			entity.setPassengerAttr5(eachpax.getPassengerAttr5());
			entity.setPassengerAttr6(eachpax.getPassengerAttr6());
			entity.setPassengerAttr7(eachpax.getPassengerAttr7());
			entity.setPassengerAttr8(eachpax.getPassengerAttr8());
			entity.setPassengerAttr9(eachpax.getPassengerAttr9());
			entity.setPassengerAttr10(eachpax.getPassengerAttr10());
			entity.setPassengerCountry(eachpax.getPassengerCountry());
			entity.setPassengerCountryResidency(eachpax.getPassengerCountryResidency());
			entity.setPassengerCreatedBy(eachpax.getPassengerCreatedBy());
			entity.setPassengerCreatedDevice(eachpax.getPassengerCreatedDevice());
			entity.setPassengerCreatedIp(eachpax.getPassengerCreatedIp());
			entity.setPassengerCreatedDate(eachpax.getPassengerCreatedDate());
			entity.setPassengerFirstName(eachpax.getPassengerFirstName());
			entity.setPassengerLastName(eachpax.getPassengerLastName());
			entity.setPassengerMiddleName(eachpax.getPassengerMiddleName());
			entity.setPassengerEmail(eachpax.getPassengerEmail());
			entity.setPassengerIsDeleted(eachpax.getPassengerIsDeleted());
			entity.setPassengerNationality(eachpax.getPassengerNationality());
			entity.setPassengerPaxId(eachpax.getPassengerPaxId());
			entity.setPassengerPhone(eachpax.getPassengerPhone());
			entity.setPassengerRoomId(eachpax.getPassengerRoomId());
			entity.setPassengerLineId(eachpax.getPassengerLineId());
			entity.setPassengerSrId(eachpax.getPassengerSrId());
			entity.setPassengerStatus(eachpax.getPassengerStatus());
			entity.setPassengerType(eachpax.getPassengerType());
			entity.setPassengerTitle(eachpax.getPassengerTitle());

			entity.setPassengerUpdatedBy(eachpax.getPassengerUpdatedBy());
			entity.setPassengerUpdatedDevice(eachpax.getPassengerUpdatedDevice());
			entity.setPassengerUpdatedIp(eachpax.getPassengerUpdatedIp());
			entity.setPassengerUpdatedDate(eachpax.getPassengerUpdatedDate());
			return entity;

		}).collect(Collectors.toList());
	}

	public static HolidayRoomsRequest convertPassengersRoomEntityToJson(HolidayPackageRoomsModel roomRequest,
			List<HolidayPackagePassengersModel> pasengersInfo) {

		HolidayRoomsRequest response = new HolidayRoomsRequest();
		response.setId(roomRequest.getId());
		response.setRoomName(roomRequest.getRoomName());
		response.setRoomType(roomRequest.getRoomType());
		response.setRoomAddonsRequired(roomRequest.getRoomAddonsRequired());
		response.setRoomAdultCount(roomRequest.getRoomAdultCount());
		response.setRoomAttr1(roomRequest.getRoomAttr1());
		response.setRoomAttr2(roomRequest.getRoomAttr2());
		response.setRoomAttr3(roomRequest.getRoomAttr3());

		response.setRoomAttr4(roomRequest.getRoomAttr4());

		response.setRoomAttr5(roomRequest.getRoomAttr5());
		response.setRoomAttr6(roomRequest.getRoomAttr6());
		response.setRoomAttr7(roomRequest.getRoomAttr7());
		response.setRoomAttr8(roomRequest.getRoomAttr8());
		response.setRoomAttr9(roomRequest.getRoomAttr9());
		response.setRoomAttr10(roomRequest.getRoomAttr10());
		response.setRoomChildAges(roomRequest.getRoomChildAges());
		response.setRoomChildCount(roomRequest.getRoomChildCount());
		response.setRoomInfantAges(roomRequest.getRoomInfantAges());
		response.setRoomInfantCount(roomRequest.getRoomInfantCount());
		response.setRoomIsDeleted(roomRequest.getRoomIsDeleted());
		response.setRoomCreatedBy(roomRequest.getRoomCreatedBy());
		response.setRoomCreatedDate(roomRequest.getRoomCreatedDate());
		response.setRoomCreatedDevice(roomRequest.getRoomCreatedDevice());
		response.setRoomCreatedIp(roomRequest.getRoomCreatedIp());
		response.setRoomLineId(roomRequest.getRoomLineId());
		response.setRoomSrId(roomRequest.getRoomSrId());
		response.setRoomStatus(roomRequest.getRoomStatus());
		response.setRoomNumber(roomRequest.getRoomNumber());
		response.setRoomUpdatedBy(roomRequest.getRoomCreatedBy());
		response.setRoomUpdatedDate(roomRequest.getRoomCreatedDate());
		response.setRoomUpdatedDevice(roomRequest.getRoomCreatedDevice());
		response.setRoomUpdatedIp(roomRequest.getRoomCreatedIp());
		if (CollectionUtils.isNotEmpty(pasengersInfo))
			response.setRoomPassengersInfo(convertHolidayPassengersEntityToJson(pasengersInfo));
		return response;

	}

}
