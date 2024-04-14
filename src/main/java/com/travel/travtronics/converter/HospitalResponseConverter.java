package com.travel.travtronics.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.travel.travtronics.request.dto.SrHospitalAddonsDto;
import com.travel.travtronics.request.dto.SrHospitalLinesDto;
import com.travel.travtronics.request.dto.SrHospitalPassengersDto;
import com.travel.travtronics.request.dto.SrHospitalRoomsDto;
import com.travel.travtronics.srm.model.SrHospitalAddons;
import com.travel.travtronics.srm.model.SrHospitalLines;
import com.travel.travtronics.srm.model.SrHospitalPassengers;
import com.travel.travtronics.srm.model.SrHospitalRooms;

public class HospitalResponseConverter {

	public static SrHospitalLinesDto convertHospitalLinesModelToJson(SrHospitalLines request) {
		SrHospitalLinesDto model = new SrHospitalLinesDto();
		model.setLineUuid(request.getLineUuid());
		model.setId(request.getId());
		model.setLineSrId(request.getLineSrId());
		model.setLineLocation(request.getLineLocation());
		model.setLineCity(request.getLineCity());
		model.setLineCheckInDate(request.getLineCheckInDate());
		model.setLineCheckOutDate(request.getLineCheckOutDate());
		model.setLineNoOfNights(request.getLineNoOfNights());
		model.setLineTotalDays(request.getLineTotalDays());
		model.setLineNationality(request.getLineNationality());
		model.setLineCountry(request.getLineCountry());
		model.setLineCountryResidency(request.getLineCountryResidency());
		model.setLineRoomCount(request.getLineRoomCount());
		model.setLineHospitalName(request.getLineHospitalName());
		model.setLinePropertyType(request.getLinePropertyType());
		model.setLineMealType(request.getLineMealType());
		model.setLineRatings(request.getLineRatings());
		model.setLineAdultCount(request.getLineAdultCount());
		model.setLineChildCount(request.getLineChildCount());
		model.setLineInfantCount(request.getLineInfantCount());
		model.setLineSearchType(request.getLineSearchType());
		model.setLineLatitude(request.getLineLatitude());
		model.setLineLongitude(request.getLineLongitude());
		model.setLineRadius(request.getLineRadius());
		model.setLineMarkUpType(request.getLineMarkUpType());
		model.setLineMarkupPercentage(request.getLineMarkupPercentage());
		model.setLineMarkupAmount(request.getLineMarkupAmount());
		model.setLineAddonsRequired(request.getLineAddonsRequired());
		model.setLineApis(request.getLineApis());
		model.setLineCreatedBy(request.getLineCreatedBy());
		model.setLineCreatedDate(request.getLineCreatedDate());
		model.setLineCreatedDevice(request.getLineCreatedDevice());
		model.setLineCreatedIp(request.getLineCreatedIp());
		model.setLineUpdatedBy(request.getLineUpdatedBy());
		model.setLineUpdatedDate(request.getLineUpdatedDate());
		model.setLineUpdatedDevice(request.getLineUpdatedDevice());
		model.setLineUpdatedIp(request.getLineUpdatedIp());
		model.setLineAttr1(request.getLineAttr1());
		model.setLineAttr2(request.getLineAttr2());
		model.setLineAttr3(request.getLineAttr3());
		model.setLineAttr4(request.getLineAttr4());
		model.setLineAttr5(request.getLineAttr5());
		model.setLineAttr6(request.getLineAttr6());
		model.setLineAttr7(request.getLineAttr7());
		model.setLineAttr8(request.getLineAttr8());
		model.setLineAttr9(request.getLineAttr9());
		model.setLineAttr10(request.getLineAttr10());
		model.setLineAttr11(request.getLineAttr11());
		model.setLineAttr12(request.getLineAttr12());
		model.setLineAttr13(request.getLineAttr13());
		model.setLineAttr14(request.getLineAttr14());
		model.setLineAttr15(request.getLineAttr15());
		model.setLineAttr17(request.getLineAttr17());
		model.setLineAttr18(request.getLineAttr18());
		model.setLineAttr19(request.getLineAttr19());
		model.setLineAttr20(request.getLineAttr20());
		model.setLineAttr16(request.getLineAttr16());
		model.setLpoAmount(request.getLpoAmount());
		model.setLpoDate(request.getLpoDate());
		model.setLpoNumber(request.getLpoNumber());
		model.setLineStatusId(request.getLineStatusId());
		return model;

	}

	public static SrHospitalPassengersDto convertPassengersModelToJson(SrHospitalPassengers request) {
		SrHospitalPassengersDto model = new SrHospitalPassengersDto();
		model.setId(request.getId());
		model.setPassengerSrId(request.getPassengerSrId());
		model.setPassengerLineId(request.getPassengerLineId());
		model.setPassengerPaxId(request.getPassengerPaxId());
		model.setPassengerRoomId(request.getPassengerRoomId());
		model.setPassengerRoomId(request.getPassengerRoomId());
		model.setPassengerStatus(request.getPassengerStatus());
		model.setPassengerType(request.getPassengerType());
		model.setPassengerTitle(request.getPassengerTitle());
		model.setPassengerFirstName(request.getPassengerFirstName());
		model.setPassengerLastName(request.getPassengerLastName());
		model.setPassengerMiddleName(request.getPassengerMiddleName());
		model.setPassengerEmail(request.getPassengerEmail());
		model.setPassengerPhone(request.getPassengerPhone());
		model.setPassengerCoutry(request.getPassengerCoutry());
		model.setPassengerCountryResidency(request.getPassengerCountryResidency());
		model.setPassengerNationality(request.getPassengerNationality());
		model.setPassengerAddonsRequired(request.getPassengerAddonsRequired());
		model.setPassengerCreatedBy(request.getPassengerCreatedBy());
		model.setPassengerCreatedDate(request.getPassengerCreatedDate());
		model.setPassengerCreatedDevice(request.getPassengerCreatedDevice());
		model.setPassengerCreatedIp(request.getPassengerCreatedIp());
		model.setPassengerUpdatedBy(request.getPassengerUpdatedBy());
		model.setPassengerUpdatedDate(request.getPassengerUpdatedDate());
		model.setPassengerUpdatedDevice(request.getPassengerUpdatedDevice());
		model.setPassengerUpdatedIp(request.getPassengerUpdatedIp());
		model.setPassengerAttr1(request.getPassengerAttr1());
		model.setPassengerAttr2(request.getPassengerAttr2());
		model.setPassengerAttr3(request.getPassengerAttr3());
		model.setPassengerAttr4(request.getPassengerAttr4());
		model.setPassengerAttr5(request.getPassengerAttr5());

		model.setPassengerAttr6(request.getPassengerAttr6());
		model.setPassengerAttr7(request.getPassengerAttr7());
		model.setPassengerAttr8(request.getPassengerAttr8());
		model.setPassengerAttr9(request.getPassengerAttr9());
		model.setPassengerAttr10(request.getPassengerAttr10());
		model.setPassengerIsDeleted(request.getPassengerIsDeleted());
		model.setPassengerDob(request.getPassengerDob());
		return model;

	}

	public static List<SrHospitalPassengersDto> convertListPassgengerJsonToModels(List<SrHospitalPassengers> requests) {
		return requests.stream().map(model -> convertPassengersModelToJson(model)).collect(Collectors.toList());
	}

	public static SrHospitalRoomsDto convertRoomsModelToJson(SrHospitalRooms request) {
		SrHospitalRoomsDto model = new SrHospitalRoomsDto();
		model.setId(request.getId());
		model.setRoomSrId(request.getRoomSrId());
		model.setRoomLineId(request.getRoomLineId());
		model.setRoomNumber(request.getRoomNumber());
		model.setRoomAdultCount(request.getRoomAdultCount());
		model.setRoomChildCount(request.getRoomChildCount());
		model.setRoomInfantCount(request.getRoomInfantCount());
		model.setRoomChildAges(request.getRoomChildAges());
		model.setRoomInfantAges(request.getRoomInfantAges());
		model.setRoomStatus(request.getRoomStatus());
		model.setRoomAddonsRequired(request.getRoomAddonsRequired());
		model.setRoomCreatedBy(request.getRoomCreatedBy());
		model.setRoomCreatedDate(request.getRoomCreatedDate());
		model.setRoomCreatedDevice(request.getRoomCreatedDevice());
		model.setRoomCreatedIp(request.getRoomCreatedIp());
		model.setRoomUpdatedBy(request.getRoomUpdatedBy());
		model.setRoomUpdatedDate(request.getRoomUpdatedDate());
		model.setRoomUpdatedDevice(request.getRoomUpdatedDevice());
		model.setRoomUpdatedIp(request.getRoomUpdatedIp());
		model.setRoomAttr1(request.getRoomAttr1());
		model.setRoomAttr2(request.getRoomAttr2());
		model.setRoomAttr3(request.getRoomAttr3());
		model.setRoomAttr4(request.getRoomAttr4());
		model.setRoomAttr5(request.getRoomAttr5());
		model.setRoomAttr6(request.getRoomAttr6());
		model.setRoomAttr7(request.getRoomAttr7());
		model.setRoomAttr8(request.getRoomAttr8());
		model.setRoomAttr9(request.getRoomAttr9());
		model.setRoomAttr10(request.getRoomAttr10());
		model.setRoomIsDeleted(request.getRoomIsDeleted());
		// model.setRoomPasengersInfo(passengersRequest);
		return model;

	}

	public static List<SrHospitalRoomsDto> convertListRoomJsonToModels(List<SrHospitalRooms> requests) {
		return requests.stream().map(model -> convertRoomsModelToJson(model)).collect(Collectors.toList());
	}

	public static SrHospitalAddonsDto convertSrAddonsModelToModel(SrHospitalAddons request) {

		SrHospitalAddonsDto model = new SrHospitalAddonsDto();
		model.setId(request.getId());
		model.setAddonSrId(request.getAddonSrId());
		model.setAddonLineId(request.getAddonLineId());
		model.setAddonPassengerId(request.getAddonPassengerId());
		model.setAddonRoomId(request.getAddonRoomId());
		model.setAddonStatus(request.getAddonStatus());
		model.setAddonFor(request.getAddonFor());
		model.setAddonType(request.getAddonType());
		model.setAddonTypeId(request.getAddonTypeId());
		model.setAddonSubType(request.getAddonSubType());
		model.setAddonSubTypeId(request.getAddonSubTypeId());
		model.setAddonTitle(request.getAddonTitle());
		model.setAddonDescription(request.getAddonDescription());
		model.setAddonExtraCost(request.getAddonExtraCost());
		model.setAddonCreatedBy(request.getAddonCreatedBy());
		model.setAddonCreatedDate(request.getAddonCreatedDate());
		model.setAddonCreatedDevice(request.getAddonCreatedDevice());
		model.setAddonCreatedIp(request.getAddonCreatedIp());
		model.setAddonUpdatedBy(request.getAddonUpdatedBy());
		model.setAddonUpdatedDate(request.getAddonUpdatedDate());
		model.setAddonUpdatedDevice(request.getAddonUpdatedDevice());
		model.setAddonUpdatedIp(request.getAddonUpdatedIp());
		model.setAddonAttr1(request.getAddonAttr1());
		model.setAddonAttr2(request.getAddonAttr2());
		model.setAddonAttr3(request.getAddonAttr3());
		model.setAddonAttr4(request.getAddonAttr4());
		model.setAddonAttr5(request.getAddonAttr5());
		model.setAddonAttr6(request.getAddonAttr6());
		model.setAddonAttr7(request.getAddonAttr7());
		model.setAddonAttr8(request.getAddonAttr8());
		model.setAddonAttr9(request.getAddonAttr9());
		model.setAddonAttr10(request.getAddonAttr10());
		model.setAddonNights(request.getAddonNights());
		model.setAddonCount(request.getAddonCount());
		model.setAddonWithBooking(request.getAddonWithBooking());
		model.setAddonRequired(request.getAddonRequired());
		model.setAddonRemarks(request.getAddonRemarks());
		model.setAddonPassengers(request.getAddonPassengers());
		model.setAddonIsDeleted(request.getAddonIsDeleted());
		return model;
	}

	public static SrHospitalRoomsDto convertPostRoomsModelToJson(SrHospitalRooms request,
			List<SrHospitalPassengers> passengersRequest) {
		SrHospitalRoomsDto model = new SrHospitalRoomsDto();
		model.setId(request.getId());
		model.setRoomSrId(request.getRoomSrId());
		model.setRoomLineId(request.getRoomLineId());
		model.setRoomNumber(request.getRoomNumber());
		model.setRoomAdultCount(request.getRoomAdultCount());
		model.setRoomChildCount(request.getRoomChildCount());
		model.setRoomInfantCount(request.getRoomInfantCount());
		model.setRoomChildAges(request.getRoomChildAges());
		model.setRoomInfantAges(request.getRoomInfantAges());
		model.setRoomStatus(request.getRoomStatus());
		model.setRoomAddonsRequired(request.getRoomAddonsRequired());
		model.setRoomCreatedBy(request.getRoomCreatedBy());
		model.setRoomCreatedDate(request.getRoomCreatedDate());
		model.setRoomCreatedDevice(request.getRoomCreatedDevice());
		model.setRoomCreatedIp(request.getRoomCreatedIp());
		model.setRoomUpdatedBy(request.getRoomUpdatedBy());
		model.setRoomUpdatedDate(request.getRoomUpdatedDate());
		model.setRoomUpdatedDevice(request.getRoomUpdatedDevice());
		model.setRoomUpdatedIp(request.getRoomUpdatedIp());
		model.setRoomAttr1(request.getRoomAttr1());
		model.setRoomAttr2(request.getRoomAttr2());
		model.setRoomAttr3(request.getRoomAttr3());
		model.setRoomAttr4(request.getRoomAttr4());
		model.setRoomAttr5(request.getRoomAttr5());
		model.setRoomAttr6(request.getRoomAttr6());
		model.setRoomAttr7(request.getRoomAttr7());
		model.setRoomAttr8(request.getRoomAttr8());
		model.setRoomAttr9(request.getRoomAttr9());
		model.setRoomAttr10(request.getRoomAttr10());
		model.setRoomIsDeleted(request.getRoomIsDeleted());
		model.setRoomPassengersInfo(convertListPassgengerJsonToModels(passengersRequest));
		return model;

	}

//	public static SrHospitalLinesDto convertHospitalLinesModelToJsonResponse(HospitalLineEntity request) {
//		SrHospitalLinesDto model = new SrHospitalLinesDto();
//		model.setLineUuid(request.getLineUuid());
//		model.setId(request.getId());
//		model.setLineSrId(request.getLineSrId());
//		model.setLineLocation(request.getLineLocation());
//		model.setLineCity(request.getLineCity());
//		model.setLineCheckInDate(request.getLineCheckInDate());
//		model.setLineCheckOutDate(request.getLineCheckOutDate());
//		model.setLineNoOfNights(request.getLineNoOfNights());
//		model.setLineTotalDays(request.getLineTotalDays());
//		model.setLineNationality(request.getLineNationality());
//		model.setLineCountry(request.getLineCountry());
//		model.setLineCountryResidency(request.getLineCountryResidency());
//		model.setLineRoomCount(request.getLineRoomCount());
//		model.setLineHospitalName(request.getLineHospitalName());
//		model.setLinePropertyType(request.getLinePropertyType());
//		model.setLineMealType(request.getLineMealType());
//		model.setLineRatings(request.getLineRatings());
//		model.setLineAdultCount(request.getLineAdultCount());
//		model.setLineChildCount(request.getLineChildCount());
//		model.setLineInfantCount(request.getLineInfantCount());
//		model.setLineSearchType(request.getLineSearchType());
//		model.setLineLatitude(request.getLineLatitude());
//		model.setLineLongitude(request.getLineLongitude());
//		model.setLineRadius(request.getLineRadius());
//		model.setLineMarkUpType(request.getLineMarkUpType());
//		model.setLineMarkupPercentage(request.getLineMarkupPercentage());
//		model.setLineMarkupAmount(request.getLineMarkupAmount());
//		model.setLineAddonsRequired(request.getLineAddonsRequired());
//		model.setLineApis(request.getLineApis());
//		model.setLineCreatedBy(request.getLineCreatedBy());
//		model.setLineCreatedDate(request.getLineCreatedDate());
//		model.setLineCreatedDevice(request.getLineCreatedDevice());
//		model.setLineCreatedIp(request.getLineCreatedIp());
//		model.setLineUpdatedBy(request.getLineUpdatedBy());
//		model.setLineUpdatedDate(request.getLineUpdatedDate());
//		model.setLineUpdatedDevice(request.getLineUpdatedDevice());
//		model.setLineUpdatedIp(request.getLineUpdatedIp());
//		model.setLineAttr1(request.getLineAttr1());
//		model.setLineAttr2(request.getLineAttr2());
//		model.setLineAttr3(request.getLineAttr3());
//		model.setLineAttr4(request.getLineAttr4());
//		model.setLineAttr5(request.getLineAttr5());
//		model.setLineAttr6(request.getLineAttr6());
//		model.setLineAttr7(request.getLineAttr7());
//		model.setLineAttr8(request.getLineAttr8());
//		model.setLineAttr9(request.getLineAttr9());
//		model.setLineAttr10(request.getLineAttr10());
//		model.setLineAttr11(request.getLineAttr11());
//		model.setLineAttr12(request.getLineAttr12());
//		model.setLineAttr13(request.getLineAttr13());
//		model.setLineAttr14(request.getLineAttr14());
//		model.setLineAttr15(request.getLineAttr15());
//		model.setLineAttr17(request.getLineAttr17());
//		model.setLineAttr18(request.getLineAttr18());
//		model.setLineAttr19(request.getLineAttr19());
//		model.setLineAttr20(request.getLineAttr20());
//		model.setLineAttr16(request.getLineAttr16());
//		model.setLpoAmount(request.getLpoAmount());
//		model.setLpoDate(request.getLpoDate());
//		model.setLpoNumber(request.getLpoNumber());
//		model.setLineStatusId(request.getLineStatusId());
//		model.setLineStatus(request.getLineStatus());
//		return model;
//
//	}

}
