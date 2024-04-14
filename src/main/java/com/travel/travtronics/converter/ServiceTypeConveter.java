package com.travel.travtronics.converter;

import java.time.LocalDateTime;

import com.travel.travtronics.bpf.model.AdditionalFields;
import com.travel.travtronics.bpf.model.ServiceTypeLines;
import com.travel.travtronics.request.AdditionalFieldsRequest;
import com.travel.travtronics.request.dto.ServiceTypeLinesFetchModel;
import com.travel.travtronics.response.AdditionalFieldsResponse;

public class ServiceTypeConveter {

//	public static ServiceTypesHeader convertJsonToModel(ServiceTypeHeaderRequest request) {
//
//		ServiceTypesHeader model = new ServiceTypesHeader();
//
//		model.setCreatedBy(request.getCreatedBy());
//		model.setCreatedDate(LocalDateTime.now().toString());
////		model.setCreatedDate(LocalDateTime.now());
//		model.setDepartmentId(request.getDepartmentId());
//		model.setOrganizationId(request.getOrganizationId());
//		model.setServiceTypeGroup(request.getServiceTypeGroup());
//		model.setDesktopEnabled(request.getDesktopEnabled());
//		model.setMobileEnabled(request.getMobileEnabled());
//		model.setIsDynamicForm(request.getIsDynamicForm());
//		model.setFormUrl(request.getFormUrl());
//		model.setName(request.getName());
//		model.setDescription(request.getDescription());
//		model.setPreValidations(request.getPreValidations());
//		model.setInstructions(request.getInstructions());
//		model.setStatus(request.getStatus());
//		model.setIsParent(request.getIsParent());
//		model.setIsProcess(request.getIsProcess());
//		model.setServiceClass(request.getServiceClass());
//		model.setServiceCategory(request.getServiceCategory());
//		model.setServiceType(request.getServiceType());
//		model.setStages(request.getStages());
//		model.setIsStage(request.getIsStage());
//		model.setServiceTypeIconClass(request.getServiceTypeIconClass());
//		model.setIsPricing(request.getIsPricing());
//		model.setReceiptConfirmTemplate(request.getReceiptConfirmTemplate());
//		return model;
//
//	}
//
//	public static ServiceTypesHeader modifyJsonToModel(ServiceTypeHeaderRequest request, Long id) {
//		ServiceTypesHeader model = new ServiceTypesHeader();
//
//		if (id != null && id != 0)
//			model.setId(id);
//		model.setDepartmentId(request.getDepartmentId());
//		model.setOrganizationId(request.getOrganizationId());
//		model.setServiceTypeGroup(request.getServiceTypeGroup());
//		model.setName(request.getName());
//		model.setDesktopEnabled(request.getDesktopEnabled());
//		model.setMobileEnabled(request.getMobileEnabled());
//		model.setIsDynamicForm(request.getIsDynamicForm());
//		model.setFormUrl(request.getFormUrl());
//		model.setDescription(request.getDescription());
//		model.setPreValidations(request.getPreValidations());
//		model.setInstructions(request.getInstructions());
//		model.setStatus(request.getStatus());
//		model.setUpdatedBy(request.getUpdatedBy());
//		model.setUpdatedDate(LocalDateTime.now().toString());
//		model.setIsParent(request.getIsParent());
//		model.setIsProcess(request.getIsProcess());
//		model.setServiceClass(request.getServiceClass());
//		model.setServiceCategory(request.getServiceCategory());
//		model.setServiceType(request.getServiceType());
//		model.setStages(request.getStages());
//		model.setIsStage(request.getIsStage());
//		model.setServiceTypeIconClass(request.getServiceTypeIconClass());
//		model.setIsPricing(request.getIsPricing());
//		model.setReceiptConfirmTemplate(request.getReceiptConfirmTemplate());
////		model.setUpdatedDate(LocalDateTime.now());
//		return model;
//
//	}
//
	public static ServiceTypeLinesFetchModel convertServiceTypeModelToJson(ServiceTypeLines model) {

		ServiceTypeLinesFetchModel dto = new ServiceTypeLinesFetchModel();

		dto.setId(model.getId());
		dto.setOrganizationId(model.getOrganizationId());
		dto.setHeaderId(model.getHeaderId());
		dto.setField(model.getField());
		dto.setDescription(model.getDescription());
		dto.setRequired(model.getRequired());
		dto.setInfo(model.getInfo());
		dto.setHint(model.getHint());
		dto.setDesktopColumn(model.getDesktopColumn());
		dto.setFormData(model.getFormData());
		dto.setDesktopOffset(model.getDesktopOffset());
		dto.setMobileColumn(model.getMobileColumn());
		dto.setMobileOffset(model.getMobileOffset());
		dto.setMin(model.getMin());
		dto.setMax(model.getMax());
		dto.setMinLength(model.getMinLength());
		dto.setMaxLength(model.getMaxLength());
		dto.setMinDate(model.getMinDate());
		dto.setMaxDate(model.getMaxDate());
		dto.setNewRow(model.getNewRow());
		dto.setIsSpecialCharacters(model.getIsSpecialCharacters());
		dto.setIsPricing(model.getIsPricing());
		dto.setStatus(model.getStatus());
		dto.setUpdatedBy(model.getUpdatedBy());
		dto.setUpdatedDate(model.getUpdatedDate());
		dto.setCreatedBy(model.getCreatedBy());
		dto.setCreatedDate(model.getCreatedDate());
		dto.setBackendColumn(model.getBackendColumn());
		dto.setBackendOffset(model.getBackendOffset());
		dto.setFieldOrder(model.getFieldOrder());
		dto.setEndStage(model.getEndStage());
		dto.setStageNumber(model.getStageNumber());
		return dto;
	}

//
//	public static ServiceTypeLines convertLineRequestToModel(ServiceLineRequest request) {
//		ServiceTypeLines line = new ServiceTypeLines();
//		if (request.getId() != null && request.getId() != 0) {
//			line.setId(request.getId());
//			line.setUpdatedDate(LocalDateTime.now().toString());
//		} else {
//			line.setCreatedDate(LocalDateTime.now().toString());
//		}
//
//		if (request.getHeaderId() != null && request.getHeaderId() != 0)
//			line.setHeaderId(request.getHeaderId());
//		if (request.getField() != null)
//			line.setField(request.getField());
//		if (request.getDescription() != null && !request.getDescription().isEmpty())
//			line.setDescription(request.getDescription());
//		if (request.getInfo() != null && !request.getInfo().isEmpty())
//			line.setInfo(request.getInfo());
//		if (request.getRequired() != null)
//			line.setRequired(request.getRequired());
//		if (request.getHint() != null && !request.getHint().isEmpty())
//			line.setHint(request.getHint());
//		if (request.getDesktopColumn() != null)
//			line.setDesktopColumn(request.getDesktopColumn());
//		if (request.getDesktopOffset() != null)
//			line.setDesktopOffset(request.getDesktopOffset());
//		if (request.getFormData() != null && !request.getFormData().isEmpty())
//			line.setFormData(request.getFormData());
//		if (request.getMobileColumn() != null)
//			line.setMobileColumn(request.getMobileColumn());
//		if (request.getMobileOffset() != null)
//			line.setMobileOffset(request.getMobileOffset());
//		if (request.getMin() != null)
//			line.setMin(request.getMin());
//		if (request.getMax() != null)
//			line.setMax(request.getMax());
//		if (request.getMinLength() != null)
//			line.setMinLength(request.getMinLength());
//		if (request.getMaxLength() != null)
//			line.setMaxLength(request.getMaxLength());
//		if (request.getMinDate() != null)
//			line.setMinDate(request.getMinDate());
//		if (request.getMaxDate() != null)
//			line.setMaxDate(request.getMaxDate());
//		if (request.getNewRow() != null)
//			line.setNewRow(request.getNewRow());
//		if (request.getIsSpecialCharacters() != null)
//			line.setIsSpecialCharacters(request.getIsSpecialCharacters());
//		if (request.getIsPricing() != null)
//			line.setIsPricing(request.getIsPricing());
//		if (request.getCreatedBy() != null)
//			line.setCreatedBy(request.getCreatedBy());
//		if (request.getUpdatedBy() != null)
//			line.setUpdatedBy(request.getUpdatedBy());
//		if (request.getStatus() != null)
//			line.setStatus(request.getStatus());
//		if (request.getBackendColumn() != null)
//			line.setBackendColumn(request.getBackendColumn());
//		if (request.getBackendOffset() != null)
//			line.setBackendOffset(request.getBackendOffset());
//		if (request.getFieldOrder() != null)
//			line.setFieldOrder(request.getFieldOrder());
//		if (request.getEndStage() != null)
//			line.setEndStage(request.getEndStage());
//		if (request.getStageNumber() != null)
//			line.setStageNumber(request.getStageNumber());
//		if (request.getOrganizationId() != null)
//			line.setOrganizationId(request.getOrganizationId());
//		return line;
//	}
//
	public static AdditionalFieldsResponse convertAdditionalFieldModelToJson(AdditionalFields model) {

		AdditionalFieldsResponse dto = new AdditionalFieldsResponse();

		dto.setId(model.getId());
		dto.setOrganizationId(model.getOrganizationId());
		dto.setHeaderId(model.getHeaderId());
		dto.setField(model.getField());
		dto.setDescription(model.getDescription());
		dto.setRequired(model.getRequired());
		dto.setInfo(model.getInfo());
		dto.setHint(model.getHint());
		dto.setDesktopColumn(model.getDesktopColumn());
		dto.setFormData(model.getFormData());
		dto.setDesktopOffset(model.getDesktopOffset());
		dto.setMobileColumn(model.getMobileColumn());
		dto.setMobileOffset(model.getMobileOffset());
		dto.setMin(model.getMin());
		dto.setMax(model.getMax());
		dto.setMinLength(model.getMinLength());
		dto.setMaxLength(model.getMaxLength());
		dto.setMinDate(model.getMinDate());
		dto.setMaxDate(model.getMaxDate());
		dto.setNewRow(model.getNewRow());
		dto.setIsSpecialCharacters(model.getIsSpecialCharacters());
		dto.setIsPricing(model.getIsPricing());
		dto.setStatus(model.getStatus());
		dto.setUpdatedBy(model.getUpdatedBy());
		dto.setUpdatedDate(model.getUpdatedDate());
		dto.setCreatedBy(model.getCreatedBy());
		dto.setCreatedDate(model.getCreatedDate());
		dto.setBackendColumn(model.getBackendColumn());
		dto.setBackendOffset(model.getBackendOffset());
		dto.setFieldOrder(model.getFieldOrder());
		dto.setEndStage(model.getEndStage());
		dto.setStageNumber(model.getStageNumber());
		dto.setTransitionId(model.getTransitionId());
		return dto;
	}

	public static AdditionalFields convertLineRequestAdditionalFieldToModel(AdditionalFieldsRequest request) {
		AdditionalFields line = new AdditionalFields();
		if (request.getId() != null && request.getId() != 0) {
			line.setId(request.getId());
			line.setUpdatedDate(LocalDateTime.now().toString());
		} else {
			line.setCreatedDate(LocalDateTime.now().toString());
		}

		if (request.getHeaderId() != null && request.getHeaderId() != 0)
			line.setHeaderId(request.getHeaderId());
		if (request.getField() != null)
			line.setField(request.getField());
		if (request.getDescription() != null && !request.getDescription().isEmpty())
			line.setDescription(request.getDescription());
		if (request.getInfo() != null && !request.getInfo().isEmpty())
			line.setInfo(request.getInfo());
		if (request.getRequired() != null)
			line.setRequired(request.getRequired());
		if (request.getHint() != null && !request.getHint().isEmpty())
			line.setHint(request.getHint());
		if (request.getDesktopColumn() != null)
			line.setDesktopColumn(request.getDesktopColumn());
		if (request.getDesktopOffset() != null)
			line.setDesktopOffset(request.getDesktopOffset());
		if (request.getFormData() != null && !request.getFormData().isEmpty())
			line.setFormData(request.getFormData());
		if (request.getMobileColumn() != null)
			line.setMobileColumn(request.getMobileColumn());
		if (request.getMobileOffset() != null)
			line.setMobileOffset(request.getMobileOffset());
		if (request.getMin() != null)
			line.setMin(request.getMin());
		if (request.getMax() != null)
			line.setMax(request.getMax());
		if (request.getMinLength() != null)
			line.setMinLength(request.getMinLength());
		if (request.getMaxLength() != null)
			line.setMaxLength(request.getMaxLength());
		if (request.getMinDate() != null)
			line.setMinDate(request.getMinDate());
		if (request.getMaxDate() != null)
			line.setMaxDate(request.getMaxDate());
		if (request.getNewRow() != null)
			line.setNewRow(request.getNewRow());
		if (request.getIsSpecialCharacters() != null)
			line.setIsSpecialCharacters(request.getIsSpecialCharacters());
		if (request.getIsPricing() != null)
			line.setIsPricing(request.getIsPricing());
		if (request.getCreatedBy() != null)
			line.setCreatedBy(request.getCreatedBy());
		if (request.getUpdatedBy() != null)
			line.setUpdatedBy(request.getUpdatedBy());
		if (request.getStatus() != null)
			line.setStatus(request.getStatus());
		if (request.getBackendColumn() != null)
			line.setBackendColumn(request.getBackendColumn());
		if (request.getBackendOffset() != null)
			line.setBackendOffset(request.getBackendOffset());
		if (request.getFieldOrder() != null)
			line.setFieldOrder(request.getFieldOrder());
		if (request.getEndStage() != null)
			line.setEndStage(request.getEndStage());
		if (request.getStageNumber() != null)
			line.setStageNumber(request.getStageNumber());
		if (request.getOrganizationId() != null)
			line.setOrganizationId(request.getOrganizationId());
		if (request.getTransitionId() != null) {
			line.setTransitionId(request.getTransitionId());
		}
		return line;
	}
//
//	public static MenuOfServiceResponse convertServiceMenuEntityToDto(ServiceTypeMenuEntity entity) {
//
//		MenuOfServiceResponse response = new MenuOfServiceResponse();
//
//		response.setServiceTypeId(entity.getServiceTypeId());
//		response.setServiceTypeName(entity.getServiceTypeName());
//		response.setServiceTypeUrl(entity.getServiceTypeUrl());
//		response.setIsServiceDynamic(entity.getIsServiceDynamic());
//		response.setServiceTypeClass(entity.getServiceTypeClass());
//		response.setIsParent(entity.getIsParent());
//		response.setIsProcess(entity.getIsProcess());
//		return response;
//	}
//
//	public static List<MenuItemsResponse> convertGroupDeptSrTypesToJson(
//			Map<String, List<MenuOfServiceResponse>> groupSrInfo) {
//
//		List<MenuItemsResponse> responseInfo = new ArrayList<>();
//
//		groupSrInfo.forEach((k, v) -> {
//
//			MenuItemsResponse response = new MenuItemsResponse();
//
//			String[] deptInfo = k.split("-");
//
//			response.setDepartmentId(Long.valueOf(deptInfo[0]));
//			response.setDepartmentName(deptInfo[1]);
//			response.setServices(v);
//			responseInfo.add(response);
//
//		});
//
//		return responseInfo;
//
//	}
//
//	public static ServiceTypesHeaderResponse convertModelTOResponse(ServiceTypesHeader model) {
//
//		ServiceTypesHeaderResponse response = new ServiceTypesHeaderResponse();
//
//		response.setCreatedBy(model.getCreatedBy());
//		response.setCreatedDate(model.getCreatedDate());
//		response.setDepartmentId(model.getDepartmentId());
//		response.setDescription(model.getDescription());
//		response.setDesktopEnabled(model.getDesktopEnabled());
//		response.setFormUrl(model.getFormUrl());
//		response.setId(model.getId());
//		response.setInstructions(model.getInstructions());
//		response.setIsDynamicForm(model.getIsDynamicForm());
//		response.setIsParent(model.getIsParent());
//		response.setIsPricing(model.getIsPricing());
//		response.setIsProcess(model.getIsProcess());
//		response.setIsStage(model.getIsStage());
//		response.setMobileEnabled(model.getMobileEnabled());
//		response.setName(model.getName());
//		response.setOrganizationId(model.getOrganizationId());
//		response.setPreValidations(model.getPreValidations());
//		response.setReceiptConfirmTemplate(model.getReceiptConfirmTemplate());
//		response.setServiceCategory(model.getServiceCategory());
//		response.setServiceClass(model.getServiceClass());
//		response.setServiceType(model.getServiceType());
//		response.setServiceTypeGroup(model.getServiceTypeGroup());
//		response.setServiceTypeIconClass(model.getServiceTypeIconClass());
//		response.setStages(model.getStages());
//		response.setStatus(model.getStatus());
//		response.setUpdatedBy(model.getUpdatedBy());
//		response.setUpdatedDate(model.getUpdatedDate());
//		return response;
//	}

}
