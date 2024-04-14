package com.travel.travtronics.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.travel.travtronics.request.dto.PaxRequestModel;
import com.travel.travtronics.srm.model.PaxServiceRequestLine;

public class PaxConverter {

	public static PaxServiceRequestLine convertPaxDtoToPaxModel(PaxRequestModel model) {

		PaxServiceRequestLine line = new PaxServiceRequestLine();

		
		line.setCreatedBy(model.getCreatedBy());
		line.setRequestId(model.getRequestId());
		line.setRequestLineId(model.getRequestLineId());
		line.setPaxId(model.getPaxId());
		line.setStatusId(model.getStatusId());
		line.setCreatedDate(model.getCreatedDate());
		line.setUpdatedBy(model.getUpdatedBy());
		line.setUpdatedDate(model.getUpdatedDate());
		line.setFirstName(model.getFirstName());
		line.setLastName(model.getLastName());
		line.setEmail(model.getEmail());
		line.setPhone(model.getPhone());
		line.setNationality(model.getNationality());
		line.setDob(model.getDob());
		line.setNationality(model.getNationality());
		line.setIssuedCountry(model.getIssuedCountry());
		line.setPaxType(model.getPaxType());
		line.setPassport(model.getPassport());
		line.setPassportExpiredDate(model.getPassportExpiredDate());
		line.setPassportIssueDate(model.getPassportIssueDate());
		line.setPaxIsDeleted(model.getPaxIsDeleted());
		return line;
	}

	public static List<PaxServiceRequestLine> convertDtosToModels(List<PaxRequestModel> models) {
		return models.stream().map(model -> convertPaxDtoToPaxModel(model)).collect(Collectors.toList());

	}

	

//	public static SrSearchModel convertSrToSearchModel(ServiceRequest sr) {
//		SrSearchModel model = new SrSearchModel();
//		model.setRequestId(sr.getRequestId());
//		model.setCustomerId(sr.getCustomerId());
//		model.setContactId(sr.getContactId());
//		model.setRequestType(sr.getRequestType());
//		model.setPriorityId(sr.getPriorityId());
//		model.setSeverityId(sr.getSeverityId());
//		model.setChannelId(sr.getChannelId());
//		model.setCreatedBy(sr.getCreatedBy());
//		model.setCreatedDate(sr.getCreatedDate());
//		model.setUpdatedBy(sr.getUpdatedBy());
//		model.setUpdatedDate(sr.getUpdatedDate());
//		model.setClosedBy(sr.getClosedBy());
//		model.setClosedDate(sr.getClosedDate());
//		model.setRequestStatus(sr.getRequestStatus());
//		model.setCopy(sr.getCopy());
//		model.setCopyFrom(sr.getCopyFrom());
//		model.setCopyTo(sr.getCopyTo());
//		model.setApproverAssignedBy(sr.getApproverAssignedBy());
//		model.setApproverId(sr.getApproverId());
//		model.setApproverRemarks(sr.getApproverRemarks());
//		model.setApproverStatus(sr.getApproverStatus());
//		model.setApproverCreatedBy(sr.getApproverCreatedBy());
//		model.setApproverCreatedDate(sr.getApproverCreatedDate());
//		model.setApproverModifiedBy(sr.getApproverModifiedBy());
//		model.setApproverModifiedDate(sr.getApproverModifiedDate());
//		return model;
//	}

	public static PaxServiceRequestLine modifyPaxDtoToPaxModel(PaxRequestModel model) {

		PaxServiceRequestLine line = new PaxServiceRequestLine();

		if (model.getRequestLinePaxId() != null && model.getRequestLinePaxId() != 0)
			line.setRequestLinePaxId(model.getRequestLinePaxId());
		

		line.setCreatedBy(model.getCreatedBy());
		line.setRequestId(model.getRequestId());
		line.setRequestLineId(model.getRequestLineId());
		line.setPaxId(model.getPaxId());
		line.setStatusId(model.getStatusId());
		line.setCreatedDate(model.getCreatedDate());
		line.setUpdatedBy(model.getUpdatedBy());
		line.setUpdatedDate(model.getUpdatedDate());
		line.setFirstName(model.getFirstName());
		line.setLastName(model.getLastName());
		line.setEmail(model.getEmail());
		line.setPhone(model.getPhone());
		line.setNationality(model.getNationality());
		line.setDob(model.getDob());
		line.setNationality(model.getNationality());
		line.setIssuedCountry(model.getIssuedCountry());
		line.setPaxType(model.getPaxType());
		line.setPassport(model.getPassport());
		line.setPassportExpiredDate(model.getPassportExpiredDate());
		line.setPassportIssueDate(model.getPassportIssueDate());
		line.setPaxIsDeleted(model.getPaxIsDeleted());
		return line;
	}
	
}
