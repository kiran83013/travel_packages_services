package com.travel.travtronics.converter;

import com.travel.travtronics.bpf.model.InputTypeConfig;
import com.travel.travtronics.response.ConfigResponse;
import com.travel.travtronics.response.PricingLineConfigResponse;

public class InputTypeConfigConverter {

//	public static InputTypeConfig convertDtoToModel(InputTypeConfigRequest dto) {
//
//		InputTypeConfig config = new InputTypeConfig();
//		config.setOrganizationId(dto.getOrganizationId());
//		config.setName(dto.getName());
//		config.setType(dto.getType());
//		config.setSource(dto.getSource());
//		config.setUi(dto.getUi());
//		config.setCreatedBy(dto.getCreatedBy());
//		config.setCreatedDate(LocalDateTime.now().toString());
//		config.setStatus(dto.getStatus());
//		config.setCategory(dto.getCategory());
//		config.setValidators(dto.getValidators());
//		config.setMultiselect(dto.getMultiselect());
//		config.setDefaultType(dto.getDefaultType());
//		config.setDafaultValue(dto.getDafaultValue());
//		config.setServiceId(dto.getServiceId());
//		config.setOce(dto.getOce());
//		config.setDependentField(dto.getDependentField());
//		config.setOptions(dto.getOptions());
//		return config;
//	}

//	public static InputTypeConfig updateDtoToModel(InputTypeConfigRequest dto, Integer configId) {
//
//		InputTypeConfig config = new InputTypeConfig();
//		if (configId != null && configId != 0)
//			config.setConfigId(configId);
//		config.setOrganizationId(dto.getOrganizationId());
//		config.setName(dto.getName());
//		config.setType(dto.getType());
//		config.setSource(dto.getSource());
//		config.setUi(dto.getUi());
//		config.setStatus(dto.getStatus());
//		config.setUpdatedBy(dto.getUpdatedBy());
//		config.setUpdatedDate(LocalDateTime.now().toString());
//		config.setCategory(dto.getCategory());
//		config.setValidators(dto.getValidators());
//		config.setMultiselect(dto.getMultiselect());
//		config.setDefaultType(dto.getDefaultType());
//		config.setDafaultValue(dto.getDafaultValue());
//		config.setServiceId(dto.getServiceId());
//		config.setOce(dto.getOce());
//		config.setDependentField(dto.getDependentField());
//		config.setOptions(dto.getOptions());
//		return config;
//	}

	public static ConfigResponse convertModelToResponse(InputTypeConfig model) {
		ConfigResponse response = new ConfigResponse();

		response.setOrganizationId(model.getOrganizationId());
		response.setConfigId(model.getConfigId());
		response.setName(model.getName());
		response.setType(model.getType());
		response.setSource(model.getSource());
		response.setUi(model.getUi());
		response.setStatus(model.getStatus());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setCategory(model.getCategory());
		response.setValidators(model.getValidators());
		response.setMultiselect(model.getMultiselect());
		response.setDefaultType(model.getDefaultType());
		response.setDafaultValue(model.getDafaultValue());
		response.setServiceId(model.getServiceId());
		response.setOce(model.getOce());
		response.setDependentField(model.getDependentField());
		response.setOptions(model.getOptions());
		return response;
	}

	public static PricingLineConfigResponse convertModelToPricingLinesResponse(InputTypeConfig model) {
		PricingLineConfigResponse response = new PricingLineConfigResponse();

		response.setConfigId(model.getConfigId());
		response.setOrganizationId(model.getOrganizationId());
		response.setName(model.getName());
		response.setType(model.getType());
		response.setSource(model.getSource());
		response.setUi(model.getUi());
		response.setStatus(model.getStatus());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setCategory(model.getCategory());
		response.setValidators(model.getValidators());
		response.setMultiselect(model.getMultiselect());
		response.setDefaultType(model.getDefaultType());
		response.setDafaultValue(model.getDafaultValue());
		response.setServiceId(model.getServiceId());
		response.setOce(model.getOce());
		response.setDependentField(model.getDependentField());
		response.setOptions(model.getOptions());
		return response;
	}

//	public static InputTypeConfigResponse convertInputTypeConfigModelToRequest(InputTypeConfig model) {
//		InputTypeConfigResponse request = new InputTypeConfigResponse();
//		request.setConfigId(model.getConfigId());
//		request.setCategory(model.getCategory());
//		request.setCreatedBy(model.getCreatedBy());
//		request.setCreatedDate(model.getCreatedDate());
//		request.setDafaultValue(model.getDafaultValue());
//		request.setDefaultType(model.getDefaultType());
//		request.setDependentField(model.getDependentField());
//		request.setMultiselect(model.getMultiselect());
//		request.setName(model.getName());
//		request.setOce(model.getOce());
//		request.setOptions(model.getOptions());
//		request.setOrganizationId(model.getOrganizationId());
//		request.setServiceId(model.getServiceId());
//		request.setSource(model.getSource());
//		request.setStatus(model.getStatus());
//		request.setType(model.getType());
//		request.setUi(model.getUi());
//		request.setUpdatedBy(model.getUpdatedBy());
//		request.setUpdatedDate(model.getUpdatedDate());
//		request.setValidators(model.getValidators());
//		return request;
//	}
}
