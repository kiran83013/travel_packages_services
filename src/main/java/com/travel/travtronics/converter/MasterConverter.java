package com.travel.travtronics.converter;

import com.travel.travtronics.bpf.model.MasterModel;
import com.travel.travtronics.response.MasterResponse;

public class MasterConverter {

	public static MasterResponse convertModelToJson(MasterModel model) {
		MasterResponse response = new MasterResponse();
		response.setId(model.getId());
		response.setName(model.getName());
		response.setCode(model.getCode());
		response.setDescription(model.getDescription());
		response.setStatus(model.getStatus());
		response.setCreatedBy(model.getCreatedBy());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		response.setCreatedDate(model.getCreatedDate());
		return response;
	}

}
