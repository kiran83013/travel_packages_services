package com.travel.travtronics.converter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

public class HospitalRequestConverter {

	public static SrHospitalLines convertHospitalLinesJsonToModel(SrHospitalLinesDto request) {

		SrHospitalLines model = new SrHospitalLines();

		// model.setLineUuid(UUID.randomUUID().toString().replace("-", ""));
		model.setLineUuid(String.valueOf(request.getLineSrId()) + "-" + String.valueOf(request.getId()));
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

		/*
		 * date issue not compatable when package created so maintaing zone
		 */
		model.setLineCreatedDate(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
				.format(ZonedDateTime.now(ZoneId.of("Asia/Kolkata"))));
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
		model.setLineStatusId(14); // set status Awt Fulfillment
		return model;
	}

	public static SrHospitalPassengers convertPassengersJsonToModel(SrHospitalPassengersDto request) {

		SrHospitalPassengers model = new SrHospitalPassengers();

		model.setPassengerSrId(request.getPassengerSrId());
		model.setPassengerLineId(request.getPassengerLineId());
		model.setPassengerPaxId(request.getPassengerPaxId());
		model.setPassengerRoomId(request.getPassengerRoomId());
		// model.setPassengerRoomId(request.getPassengerRoomId());
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

	public static SrHospitalRooms convertRoomsJsonToModel(SrHospitalRoomsDto request) {

		SrHospitalRooms model = new SrHospitalRooms();
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
		return model;

	}

	public static List<SrHospitalRooms> convertListRoomJsonToModels(List<SrHospitalRoomsDto> requests) {
		return requests.stream().map(HospitalRequestConverter::convertRoomsJsonToModel).collect(Collectors.toList());
	}

	public static SrHospitalAddons convertSrAddonsJsonToModel(SrHospitalAddonsDto request) {
		SrHospitalAddons model = new SrHospitalAddons();
		if (request.getId() != null && request.getId() != 0)
			model.setId(request.getId());

		model.setAddonIsDeleted(request.getAddonIsDeleted());
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
		return model;
	}

	public static SrHospitalLines updateHospitalLinesJsonToModel(SrHospitalLinesDto request, SrHospitalLines model) {

		model.setLineUuid(request.getLineUuid());
		if (request.getId() != null && request.getId() != 0)
			model.setId(request.getId());
		if (request.getLineSrId() != null && request.getLineSrId() != 0)
			model.setLineSrId(request.getLineSrId());
		if (request.getLineLocation() != null && !request.getLineLocation().isEmpty())
			model.setLineLocation(request.getLineLocation());
		if (request.getLineCity() != null && !request.getLineCity().isEmpty())
			model.setLineCity(request.getLineCity());
		if (request.getLineCheckInDate() != null)
			model.setLineCheckInDate(request.getLineCheckInDate());
		if (request.getLineCheckOutDate() != null)
			model.setLineCheckOutDate(request.getLineCheckOutDate());
		if (request.getLineNoOfNights() != null && request.getLineNoOfNights() != 0)
			model.setLineNoOfNights(request.getLineNoOfNights());
		if (request.getLineTotalDays() != null && request.getLineTotalDays() != 0)
			model.setLineTotalDays(request.getLineTotalDays());
		if (request.getLineNationality() != null && !request.getLineNationality().isEmpty())
			model.setLineNationality(request.getLineNationality());
		if (request.getLineCountry() != null && !request.getLineCountry().isEmpty())
			model.setLineCountry(request.getLineCountry());
		if (request.getLineCountryResidency() != null && !request.getLineCountryResidency().isEmpty())
			model.setLineCountryResidency(request.getLineCountryResidency());
		if (request.getLineRoomCount() != null && request.getLineRoomCount() != 0)
			model.setLineRoomCount(request.getLineRoomCount());
		if (request.getLineHospitalName() != null && !request.getLineHospitalName().isEmpty())
			model.setLineHospitalName(request.getLineHospitalName());
		if (request.getLinePropertyType() != null && !request.getLinePropertyType().isEmpty())
			model.setLinePropertyType(request.getLinePropertyType());
		if (request.getLineMealType() != null && !request.getLineMealType().isEmpty())
			model.setLineMealType(request.getLineMealType());
		if (request.getLineRatings() != null && request.getLineRatings() != 0)
			model.setLineRatings(request.getLineRatings());

		/*
		 * count no need to check zero handled in ui
		 */
		if (request.getLineAdultCount() != null)
			model.setLineAdultCount(request.getLineAdultCount());

		if (request.getLineChildCount() != null)
			model.setLineChildCount(request.getLineChildCount());

		if (request.getLineInfantCount() != null)
			model.setLineInfantCount(request.getLineInfantCount());

		if (request.getLineSearchType() != null && !request.getLineSearchType().isEmpty())
			model.setLineSearchType(request.getLineSearchType());
		if (request.getLineLatitude() != null && !request.getLineLatitude().isEmpty())
			model.setLineLatitude(request.getLineLatitude());
		if (request.getLineLongitude() != null && !request.getLineLongitude().isEmpty())
			model.setLineLongitude(request.getLineLongitude());
		if (request.getLineRadius() != null && !request.getLineRadius().isEmpty())
			model.setLineRadius(request.getLineRadius());
		if (request.getLineMarkUpType() != null)
			model.setLineMarkUpType(request.getLineMarkUpType());
		if (request.getLineMarkupPercentage() != null && !request.getLineMarkupPercentage().isEmpty())
			model.setLineMarkupPercentage(request.getLineMarkupPercentage());
		if (request.getLineMarkupAmount() != null && !request.getLineMarkupAmount().isEmpty())
			model.setLineMarkupAmount(request.getLineMarkupAmount());
		if (request.getLineAddonsRequired() != null && request.getLineAddonsRequired() != 0)
			model.setLineAddonsRequired(request.getLineAddonsRequired());
		if (request.getLineApis() != null && !request.getLineApis().isEmpty())
			model.setLineApis(request.getLineApis());
		if (request.getLineCreatedBy() != null && request.getLineCreatedBy() != 0)
			model.setLineCreatedBy(request.getLineCreatedBy());
		if (request.getLineCreatedDate() != null)
			model.setLineCreatedDate(request.getLineCreatedDate());
		if (request.getLineCreatedDevice() != null && !request.getLineCreatedDevice().isEmpty())
			model.setLineCreatedDevice(request.getLineCreatedDevice());
		if (request.getLineCreatedIp() != null && !request.getLineCreatedIp().isEmpty())
			model.setLineCreatedIp(request.getLineCreatedIp());
		if (request.getLineUpdatedBy() != null && request.getLineUpdatedBy() != 0)
			model.setLineUpdatedBy(request.getLineUpdatedBy());
		if (request.getLineUpdatedDate() != null)
			model.setLineUpdatedDate(request.getLineUpdatedDate());
		if (request.getLineUpdatedDevice() != null && !request.getLineUpdatedDevice().isEmpty())
			model.setLineUpdatedDevice(request.getLineUpdatedDevice());
		if (request.getLineUpdatedBy() != null && request.getLineUpdatedBy() != 0)
			model.setLineUpdatedIp(request.getLineUpdatedIp());
		if (request.getLineAttr1() != null && !request.getLineAttr1().isEmpty())
			model.setLineAttr1(request.getLineAttr1());
		if (request.getLineAttr2() != null && !request.getLineAttr2().isEmpty())
			model.setLineAttr2(request.getLineAttr2());
		if (request.getLineAttr3() != null && !request.getLineAttr3().isEmpty())
			model.setLineAttr3(request.getLineAttr3());
		if (request.getLineAttr4() != null && !request.getLineAttr4().isEmpty())
			model.setLineAttr4(request.getLineAttr4());
		if (request.getLineAttr5() != null && !request.getLineAttr5().isEmpty())
			model.setLineAttr5(request.getLineAttr5());
		if (request.getLineAttr6() != null && !request.getLineAttr6().isEmpty())
			model.setLineAttr6(request.getLineAttr6());
		if (request.getLineAttr7() != null && !request.getLineAttr7().isEmpty())
			model.setLineAttr7(request.getLineAttr7());
		if (request.getLineAttr8() != null && !request.getLineAttr8().isEmpty())
			model.setLineAttr8(request.getLineAttr8());
		if (request.getLineAttr9() != null && !request.getLineAttr9().isEmpty())
			model.setLineAttr9(request.getLineAttr9());
		if (request.getLineAttr10() != null && !request.getLineAttr10().isEmpty())
			model.setLineAttr10(request.getLineAttr10());
		if (request.getLineAttr11() != null && !request.getLineAttr11().isEmpty())
			model.setLineAttr11(request.getLineAttr11());
		if (request.getLineAttr12() != null && !request.getLineAttr12().isEmpty())
			model.setLineAttr12(request.getLineAttr12());
		if (request.getLineAttr13() != null && !request.getLineAttr13().isEmpty())
			model.setLineAttr13(request.getLineAttr13());
		if (request.getLineAttr14() != null && !request.getLineAttr14().isEmpty())
			model.setLineAttr14(request.getLineAttr14());
		if (request.getLineAttr15() != null && !request.getLineAttr15().isEmpty())
			model.setLineAttr15(request.getLineAttr15());
		if (request.getLineAttr17() != null && !request.getLineAttr17().isEmpty())
			model.setLineAttr17(request.getLineAttr17());
		if (request.getLineAttr18() != null && !request.getLineAttr18().isEmpty())
			model.setLineAttr18(request.getLineAttr18());
		if (request.getLineAttr19() != null && !request.getLineAttr19().isEmpty())
			model.setLineAttr19(request.getLineAttr19());
		if (request.getLineAttr20() != null && !request.getLineAttr20().isEmpty())
			model.setLineAttr20(request.getLineAttr20());
		if (request.getLineAttr16() != null && !request.getLineAttr16().isEmpty())
			model.setLineAttr16(request.getLineAttr16());
		if (request.getLpoAmount() != null && !request.getLpoAmount().isBlank())
			model.setLpoAmount(request.getLpoAmount());
		if (request.getLpoDate() != null)
			model.setLpoDate(request.getLpoDate());
		if (request.getLpoNumber() != null && !request.getLpoNumber().isBlank())
			model.setLpoNumber(request.getLpoNumber());

		// if (request.getLineStatusId() != null && request.getLineStatusId() != 0)
		// model.setLineStatusId(request.getLineStatusId());

		return model;
	}

	public static SrHospitalRooms updateRoomsJsonToModel(SrHospitalRoomsDto request, SrHospitalRooms model) {
		if (request.getId() != null && request.getId() != 0)
			model.setId(request.getId());
		if (request.getRoomSrId() != null && request.getRoomSrId() != 0)
			model.setRoomSrId(request.getRoomSrId());
		if (request.getRoomLineId() != null && request.getRoomLineId() != 0)
			model.setRoomLineId(request.getRoomLineId());
		if (request.getRoomNumber() != null && request.getRoomNumber() != 0)
			model.setRoomNumber(request.getRoomNumber());

		/*
		 * no need to check count zero hnadled in ui
		 */

		if (request.getRoomAdultCount() != null)
			model.setRoomAdultCount(request.getRoomAdultCount());

		if (request.getRoomChildCount() != null)
			model.setRoomChildCount(request.getRoomChildCount());

		if (request.getRoomInfantCount() != null)
			model.setRoomInfantCount(request.getRoomInfantCount());

		if (request.getRoomChildAges() != null && !request.getRoomChildAges().isBlank())
			model.setRoomChildAges(request.getRoomChildAges());
		if (request.getRoomInfantAges() != null && !request.getRoomInfantAges().isEmpty())
			model.setRoomInfantAges(request.getRoomInfantAges());
		if (request.getRoomStatus() != null && request.getRoomStatus() != 0)
			model.setRoomStatus(request.getRoomStatus());
		if (request.getRoomAddonsRequired() != null)
			model.setRoomAddonsRequired(request.getRoomAddonsRequired());
		if (request.getRoomCreatedBy() != null)
			model.setRoomCreatedBy(request.getRoomCreatedBy());
		if (request.getRoomCreatedDate() != null)
			model.setRoomCreatedDate(request.getRoomCreatedDate());
		if (request.getRoomCreatedDevice() != null)
			model.setRoomCreatedDevice(request.getRoomCreatedDevice());
		if (request.getRoomCreatedIp() != null)
			model.setRoomCreatedIp(request.getRoomCreatedIp());
		if (request.getRoomUpdatedBy() != null)
			model.setRoomUpdatedBy(request.getRoomUpdatedBy());
		if (request.getRoomUpdatedDate() != null)
			model.setRoomUpdatedDate(request.getRoomUpdatedDate());
		if (request.getRoomUpdatedDevice() != null)
			model.setRoomUpdatedDevice(request.getRoomUpdatedDevice());
		if (request.getRoomUpdatedIp() != null)
			model.setRoomUpdatedIp(request.getRoomUpdatedIp());
		if (request.getRoomAttr1() != null)
			model.setRoomAttr1(request.getRoomAttr1());
		if (request.getRoomAttr2() != null)
			model.setRoomAttr2(request.getRoomAttr2());
		if (request.getRoomAttr3() != null)
			model.setRoomAttr3(request.getRoomAttr3());
		if (request.getRoomAttr4() != null)
			model.setRoomAttr4(request.getRoomAttr4());
		if (request.getRoomAttr5() != null)
			model.setRoomAttr5(request.getRoomAttr5());
		if (request.getRoomAttr6() != null)
			model.setRoomAttr6(request.getRoomAttr6());
		if (request.getRoomAttr7() != null)
			model.setRoomAttr7(request.getRoomAttr7());
		if (request.getRoomAttr8() != null)
			model.setRoomAttr8(request.getRoomAttr8());
		if (request.getRoomAttr9() != null)
			model.setRoomAttr9(request.getRoomAttr9());
		if (request.getRoomAttr10() != null)
			model.setRoomAttr10(request.getRoomAttr10());
		if (request.getRoomIsDeleted() != null)
			model.setRoomIsDeleted(request.getRoomIsDeleted());

		return model;
	}

	public static SrHospitalPassengers updatePassengersJsonToModel(SrHospitalPassengersDto request,
			SrHospitalPassengers model) {
		if (request.getId() != null && request.getId() != 0)
			model.setId(request.getId());
		if (request.getPassengerSrId() != null)
			model.setPassengerSrId(request.getPassengerSrId());
		if (request.getPassengerLineId() != null)
			model.setPassengerLineId(request.getPassengerLineId());
		if (request.getPassengerPaxId() != null)
			model.setPassengerPaxId(request.getPassengerPaxId());
		if (request.getPassengerRoomId() != null)
			model.setPassengerRoomId(request.getPassengerRoomId());
		if (request.getPassengerStatus() != null)
			model.setPassengerStatus(request.getPassengerStatus());
		if (request.getPassengerType() != null)
			model.setPassengerType(request.getPassengerType());
		if (request.getPassengerTitle() != null)
			model.setPassengerTitle(request.getPassengerTitle());
		if (request.getPassengerFirstName() != null)
			model.setPassengerFirstName(request.getPassengerFirstName());
		if (request.getPassengerLastName() != null)
			model.setPassengerLastName(request.getPassengerLastName());
		if (request.getPassengerMiddleName() != null)
			model.setPassengerMiddleName(request.getPassengerMiddleName());
		if (request.getPassengerEmail() != null)
			model.setPassengerEmail(request.getPassengerEmail());
		if (request.getPassengerPhone() != null)
			model.setPassengerPhone(request.getPassengerPhone());
		if (request.getPassengerCoutry() != null)
			model.setPassengerCoutry(request.getPassengerCoutry());
		if (request.getPassengerCountryResidency() != null)
			model.setPassengerCountryResidency(request.getPassengerCountryResidency());
		if (request.getPassengerNationality() != null)
			model.setPassengerNationality(request.getPassengerNationality());
		if (request.getPassengerAddonsRequired() != null)
			model.setPassengerAddonsRequired(request.getPassengerAddonsRequired());
		if (request.getPassengerCreatedBy() != null)
			model.setPassengerCreatedBy(request.getPassengerCreatedBy());
		if (request.getPassengerCreatedDate() != null)
			model.setPassengerCreatedDate(request.getPassengerCreatedDate());
		if (request.getPassengerCreatedDate() != null)
			model.setPassengerCreatedDevice(request.getPassengerCreatedDevice());
		if (request.getPassengerCreatedIp() != null)
			model.setPassengerCreatedIp(request.getPassengerCreatedIp());
		if (request.getPassengerUpdatedBy() != null)
			model.setPassengerUpdatedBy(request.getPassengerUpdatedBy());
		if (request.getPassengerUpdatedBy() != null)
			model.setPassengerUpdatedDate(request.getPassengerUpdatedDate());
		if (request.getPassengerUpdatedDevice() != null)
			model.setPassengerUpdatedDevice(request.getPassengerUpdatedDevice());
		if (request.getPassengerUpdatedIp() != null)
			model.setPassengerUpdatedIp(request.getPassengerUpdatedIp());
		if (request.getPassengerAttr1() != null)
			model.setPassengerAttr1(request.getPassengerAttr1());
		if (request.getPassengerAttr2() != null)
			model.setPassengerAttr2(request.getPassengerAttr2());
		if (request.getPassengerAttr3() != null)
			model.setPassengerAttr3(request.getPassengerAttr3());
		if (request.getPassengerAttr4() != null)
			model.setPassengerAttr4(request.getPassengerAttr4());
		if (request.getPassengerAttr5() != null)
			model.setPassengerAttr5(request.getPassengerAttr5());
		if (request.getPassengerAttr6() != null)
			model.setPassengerAttr6(request.getPassengerAttr6());
		if (request.getPassengerAttr7() != null)
			model.setPassengerAttr7(request.getPassengerAttr7());
		if (request.getPassengerAttr8() != null)
			model.setPassengerAttr8(request.getPassengerAttr8());
		if (request.getPassengerAttr9() != null)
			model.setPassengerAttr9(request.getPassengerAttr9());
		if (request.getPassengerAttr10() != null)
			model.setPassengerAttr10(request.getPassengerAttr10());
		if (request.getPassengerIsDeleted() != null)
			model.setPassengerIsDeleted(request.getPassengerIsDeleted());
		if (request.getPassengerDob() != null)
			model.setPassengerDob(request.getPassengerDob());
		return model;
	}
}
