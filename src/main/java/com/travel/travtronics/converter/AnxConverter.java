package com.travel.travtronics.converter;

import java.util.Date;
import java.util.Objects;

import com.travel.travtronics.request.dto.AnxRequestLine;
import com.travel.travtronics.request.dto.PaxRequestModel;
import com.travel.travtronics.request.dto.SrHotelAddonsDto;
import com.travel.travtronics.srm.model.SrAnxAddons;
import com.travel.travtronics.srm.model.SrAnxLinesModel;
import com.travel.travtronics.srm.model.SrAnxPax;

public class AnxConverter {

	public static SrAnxLinesModel convertAnxRequestDataToAnxSrLineModel(String UniqueId, Long srLineId,
			AnxRequestLine request) {

		SrAnxLinesModel model = new SrAnxLinesModel();

		model.setLineUuid(UniqueId);
		model.setAnxLineId(srLineId);
		model.setAnxLineRequestId(request.getAnxLineRequestId());
		model.setAnxLineTypeId(request.getAnxLineTypeId());
		model.setAnxLineType(request.getAnxLineType());

		if (Objects.nonNull(request.getAnxLineAdtCount()) && request.getAnxLineAdtCount() > 0)
			model.setAnxLineAdtCount(request.getAnxLineAdtCount());
		else
			model.setAnxLineAdtCount(1);
		model.setAnxLineChdCount(request.getAnxLineChdCount());
		model.setAnxLineInfCount(request.getAnxLineInfCount());

		model.setAnxLineJson(request.getAnxLineJson());
		model.setAnxLineAddons(request.getAnxLineAddons());
		model.setAnxLineStatus(1);
		model.setAnxLineCreatedBy(request.getLoggedInUserId());
		model.setAnxLineCreatedIp(request.getDeviceIp());
		model.setAnxLineCreatedDevice(request.getDeviceInfo());
		model.setAnxLineLpoNumber(request.getAnxLineLpoNumber());
		model.setAnxLineLpoDate(request.getAnxLineLpoDate());
		model.setAnxLineLpoAmount(request.getAnxLineLpoAmount());
		model.setStatusId(request.getStatusId());
		model.setTeamId(request.getTeamId());
		model.setWiwId(request.getWiwId());
		model.setSubmitedBy(request.getSubmitedBy());
		model.setSubmitedDate(request.getSubmitedDate());
		model.setAnxLineAttr1(request.getAnxLineAttr1());
		model.setAnxLineAttr2(request.getAnxLineAttr2());
		model.setAnxLineAttr3(request.getAnxLineAttr3());
		model.setAnxLineAttr4(request.getAnxLineAttr4());
		model.setAnxLineAttr5(request.getAnxLineAttr5());
		model.setAnxLineAttr6(request.getAnxLineAttr6());

		// model.setAncillaryLineStatusId(request.getAncillaryLineStatusId() != null ?
		// request.getAncillaryLineStatusId() : 0);
		model.setAncillaryLineStatusId(14); // Awt Fulfillment

		return model;
	}

	public static SrAnxLinesModel convertAnxUpdateRequestDataToAnxSrLineModel(String uniqueId, Long srLineId,
			AnxRequestLine request, SrAnxLinesModel lineInfo) {

		SrAnxLinesModel model = new SrAnxLinesModel();

		model.setLineUuid(uniqueId);
		model.setAnxLineId(srLineId);
		model.setAnxLineRequestId(request.getAnxLineRequestId());
		model.setAnxLineTypeId(request.getAnxLineTypeId());
		model.setAnxLineType(request.getAnxLineType());
		model.setAnxLineAdtCount(request.getAnxLineAdtCount());
		model.setAnxLineChdCount(request.getAnxLineChdCount());
		model.setAnxLineInfCount(request.getAnxLineInfCount());
		model.setAnxLineJson(request.getAnxLineJson());
		model.setAnxLineAddons(request.getAnxLineAddons());
		model.setAnxLineStatus(request.getAnxLineStatus());
		model.setAnxLineCreatedBy(lineInfo.getAnxLineCreatedBy());
		model.setAnxLineUpdatedBy(request.getLoggedInUserId());
		model.setAnxLineUpdatedIp(request.getDeviceIp());
		model.setAnxLineUpdatedDevice(request.getDeviceInfo());
		model.setAnxLineLpoNumber(request.getAnxLineLpoNumber());
		model.setAnxLineLpoDate(request.getAnxLineLpoDate());
		model.setAnxLineLpoAmount(request.getAnxLineLpoAmount());
		model.setStatusId(request.getStatusId());
		model.setTeamId(request.getTeamId());
		model.setWiwId(request.getWiwId());
		model.setSubmitedBy(request.getSubmitedBy());
		model.setSubmitedDate(request.getSubmitedDate());
		model.setAnxLineAttr1(request.getAnxLineAttr1());
		model.setAnxLineAttr2(request.getAnxLineAttr2());
		model.setAnxLineAttr3(request.getAnxLineAttr3());
		model.setAnxLineAttr4(request.getAnxLineAttr4());
		model.setAnxLineAttr5(request.getAnxLineAttr5());
		model.setAnxLineAttr6(request.getAnxLineAttr6());

		if (request.getAncillaryLineStatusId() != null && request.getAncillaryLineStatusId() > 0) {
			model.setAncillaryLineStatusId(request.getAncillaryLineStatusId());
		} else {
			model.setAncillaryLineStatusId(lineInfo.getAncillaryLineStatusId());
		}

		return model;

	}

	public static SrAnxPax convertPaxDtoToPaxModel(PaxRequestModel paxdto) {

		SrAnxPax pax = new SrAnxPax();

		pax.setCreatedBy(paxdto.getCreatedBy());
		pax.setCreatedDate(new Date());

		pax.setRequestId(paxdto.getRequestId());
		pax.setRequestLineId(paxdto.getRequestLineId());
		pax.setPaxId(paxdto.getPaxId());
		pax.setStatusId(paxdto.getStatusId());
		pax.setCreatedDate(paxdto.getCreatedDate());

		pax.setFirstName(paxdto.getFirstName());
		pax.setLastName(paxdto.getLastName());
		pax.setEmail(paxdto.getEmail());
		pax.setPhone(paxdto.getPhone());
		pax.setNationality(paxdto.getNationality());
		pax.setDob(paxdto.getDob());
		pax.setNationality(paxdto.getNationality());
		pax.setIssuedCountry(paxdto.getIssuedCountry());
		pax.setPaxType(paxdto.getPaxType());
		pax.setPassport(paxdto.getPassport());
		pax.setPassportExpiredDate(paxdto.getPassportExpiredDate());
		pax.setPassportIssueDate(paxdto.getPassportIssueDate());
		pax.setPaxIsDeleted(paxdto.getPaxIsDeleted());

		return pax;

	}

	public static SrAnxPax updatePaxDtoToPaxModel(PaxRequestModel paxdto, SrAnxPax pax) {

		pax.setRequestId(paxdto.getRequestId());
		pax.setRequestLineId(paxdto.getRequestLineId());
		pax.setPaxId(paxdto.getPaxId());
		pax.setStatusId(paxdto.getStatusId());

		pax.setUpdatedBy(paxdto.getUpdatedBy());
		pax.setUpdatedDate(new Date());

		pax.setFirstName(paxdto.getFirstName());
		pax.setLastName(paxdto.getLastName());
		pax.setEmail(paxdto.getEmail());
		pax.setPhone(paxdto.getPhone());
		pax.setNationality(paxdto.getNationality());
		pax.setDob(paxdto.getDob());
		pax.setNationality(paxdto.getNationality());
		pax.setIssuedCountry(paxdto.getIssuedCountry());
		pax.setPaxType(paxdto.getPaxType());
		pax.setPassport(paxdto.getPassport());
		pax.setPassportExpiredDate(paxdto.getPassportExpiredDate());
		pax.setPassportIssueDate(paxdto.getPassportIssueDate());
		pax.setPaxIsDeleted(paxdto.getPaxIsDeleted());
		pax.setRequestLinePaxId(pax.getRequestLinePaxId());
		return pax;

	}

	public static SrAnxAddons convertAddonsDtoToModel(SrHotelAddonsDto request) {
		SrAnxAddons model = new SrAnxAddons();
		model.setAddonIsDeleted(request.getAddonIsDeleted());
		model.setAddonSrId(request.getAddonSrId());
		model.setAddonLineId(request.getAddonLineId());
		model.setAddonPassengerId(request.getAddonPassengerId());
		model.setAddonRoomId(request.getAddonRoomId());
		model.setAddonStatus(request.getAddonStatus());
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

	public static SrAnxAddons updateAddonsDtoToModel(SrHotelAddonsDto request, SrAnxAddons model) {

		model.setId(request.getId());

		if (request.getAddonIsDeleted() != null)
			model.setAddonIsDeleted(request.getAddonIsDeleted());
		model.setAddonSrId(request.getAddonSrId());
		model.setAddonLineId(request.getAddonLineId());
		model.setAddonPassengerId(request.getAddonPassengerId());
		model.setAddonRoomId(request.getAddonRoomId());
		model.setAddonStatus(request.getAddonStatus());
		model.setAddonType(request.getAddonType());
		model.setAddonTypeId(request.getAddonTypeId());
		model.setAddonSubType(request.getAddonSubType());
		model.setAddonSubTypeId(request.getAddonSubTypeId());
		model.setAddonTitle(request.getAddonTitle());
		model.setAddonDescription(request.getAddonDescription());
		model.setAddonExtraCost(request.getAddonExtraCost());

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

}
