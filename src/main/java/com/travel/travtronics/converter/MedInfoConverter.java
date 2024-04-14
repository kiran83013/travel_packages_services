package com.travel.travtronics.converter;

import com.travel.travtronics.request.MedInfoRequest;
import com.travel.travtronics.srm.model.MedInfo;

public class MedInfoConverter {

	public static MedInfo convertRequestToEntity(MedInfoRequest request) {
		MedInfo medInfo = new MedInfo();

		medInfo.setMedName(request.getMedName());
		medInfo.setMedCode(request.getMedCode());
		medInfo.setMedDescription(request.getMedDescription());
		medInfo.setMedDays(request.getMedDays());
		medInfo.setMedCategory(request.getMedCategory());
		medInfo.setMedType(request.getMedType());
		medInfo.setRecordStatus(request.getRecordStatus());
		medInfo.setValidFrom(request.getValidFrom());
		medInfo.setValidTo(request.getValidTo());
		medInfo.setCreatedBy(request.getCreatedBy());
		medInfo.setUpdatedBy(request.getUpdatedBy());
		medInfo.setSrId(request.getSrId());

		return medInfo;
	}
}