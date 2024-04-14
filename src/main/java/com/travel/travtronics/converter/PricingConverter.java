package com.travel.travtronics.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.travel.travtronics.bpf.model.PricingHeader;
import com.travel.travtronics.bpf.model.PricingLines;
import com.travel.travtronics.request.PricingHeaderRequest;
import com.travel.travtronics.request.PricingLineRequest;
import com.travel.travtronics.request.dto.PricingLinesFetchModel;
import com.travel.travtronics.request.dto.TaxTemplateDto;
import com.travel.travtronics.response.ChargablePricingResponse;
import com.travel.travtronics.response.PricingHeaderResponse;
import com.travel.travtronics.response.PricingLinesResponse;
import com.travel.travtronics.response.TaxTemplate;

public class PricingConverter {
	public static PricingHeader convertJsonToModel(PricingHeaderRequest request) {

		PricingHeader model = new PricingHeader();

		model.setName(request.getName());
		model.setCode(request.getCode());
		model.setOrganization(request.getOrganization());
		model.setDepartment(request.getDepartment());
		model.setBusinessUnit(request.getBusinessUnit());
		model.setDescription(request.getDescription());
		model.setStartDate(request.getStartDate());
		model.setEndDate(request.getEndDate());
		model.setType(request.getType());
		model.setCategory(request.getCategory());
		model.setWfStatus(request.getWfStatus());
		model.setCreatedBy(request.getCreatedBy());
		model.setCreatedDate(request.getCreatedDate());
		model.setStatus(request.getStatus());
		model.setServiceTypeHeaderId(request.getServiceTypeHeaderId());
		return model;
	}

	public static PricingHeader updateJsonToModel(PricingHeaderRequest request, Long id) {

		PricingHeader model = new PricingHeader();

		if (id != null && id != 0)
			model.setId(id);

		model.setName(request.getName());
		model.setCode(request.getCode());
		model.setOrganization(request.getOrganization());
		model.setDepartment(request.getDepartment());
		model.setBusinessUnit(request.getBusinessUnit());
		model.setDescription(request.getDescription());
		model.setStartDate(request.getStartDate());
		model.setEndDate(request.getEndDate());
		model.setType(request.getType());
		model.setCategory(request.getCategory());
		model.setWfStatus(request.getWfStatus());
		model.setStatus(request.getStatus());
		model.setUpdatedBy(request.getUpdatedBy());
		model.setUpdatedDate(request.getUpdatedDate());
		model.setServiceTypeHeaderId(request.getServiceTypeHeaderId());
		return model;

	}

	public static PricingHeaderResponse convertModelToJson(PricingHeader model) {

		PricingHeaderResponse response = new PricingHeaderResponse();

		response.setId(model.getId());
		response.setName(model.getName());
		response.setCode(model.getCode());
		response.setOrganization(model.getOrganization());
		response.setDepartment(model.getDepartment());
		response.setBusinessUnit(model.getBusinessUnit());
		response.setDescription(model.getDescription());
		response.setStartDate(model.getStartDate());
		response.setEndDate(model.getEndDate());
		response.setType(model.getType());
		response.setCategory(model.getCategory());
		response.setWfStatus(model.getWfStatus());
		response.setStatus(model.getStatus());
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		response.setServiceTypeHeaderId(model.getServiceTypeHeaderId());
		return response;

	}

//	public static List<PricingHeaderResponse> convertListModelToListJson(List<PricingHeader> models) {
//		return models.stream().map(model -> convertModelToJson(model)).collect(Collectors.toList());
//	}

	public static PricingLines convertLineJsonToModel(PricingLineRequest request) {

		PricingLines line = new PricingLines();

		if (request.getId() != null && request.getId() != 0)
			line.setId(request.getId());

		if (request.getHeaderId() != null && request.getHeaderId() != 0)
			line.setHeaderId(request.getHeaderId());

		line.setOrganizationId(request.getOrganizationId());
		line.setItem(request.getItem());
		line.setPercentage(request.getPercentage());
		line.setPortalVisible(request.getPortalVisible());
		line.setQualifier(request.getQualifier());
		line.setFieldDependent(request.getFieldDependent());
		line.setStatutory(request.getStatutory());
		line.setField(request.getField());
		line.setOperator(request.getOperator());
		line.setTaxSlab(request.getTaxSlab());
		line.setValue(request.getValue());
		line.setApi(request.getApi());
		line.setCharge(request.getCharge());
		line.setCreatedBy(request.getCreatedBy());
		line.setVariableFrequency(request.getVariableFrequency());
		line.setStartDate(request.getStartDate());
		line.setEndDate(request.getEndDate());
		line.setIsAdditinalCharge(request.getIsAdditinalCharge());
		line.setIsChargeOveride(request.getIsChargeOveride());
		line.setAttr1(request.getAttr1());
		line.setAttr2(request.getAttr2());
		line.setAttr3(request.getAttr3());
		line.setCreatedDate(request.getCreatedDate());
		line.setUpdatedDate(request.getUpdatedDate());
		line.setStatus(request.getStatus());
		line.setPbouom(request.getPbouom());
		line.setUmoValue(request.getUmoValue());
		line.setToOperator(request.getToOperator());
		line.setToValue(request.getToValue());
		line.setIsApi(request.getIsApi());
		line.setIsRange(request.getIsRange());
		line.setPriceTemplateType(request.getPriceTemplateType());
		return line;
	}

	public static List<PricingLines> convertListLineJsonToListModels(List<PricingLineRequest> requests) {
		return requests.stream().map(model -> convertLineJsonToModel(model)).collect(Collectors.toList());
	}

//	public static PricingLineResponse convertLineModelToJson(PricingLines model) {
//
//		PricingLineResponse response = new PricingLineResponse();
//
//		response.setId(model.getId());
//		response.setHeaderId(model.getHeaderId());
//		response.setItem(model.getItem());
//		response.setTaxSlab(model.getTaxSlab());
//		response.setPercentage(model.getPercentage());
//		response.setPortalVisible(model.getPortalVisible());
//		response.setQualifier(model.getQualifier());
//		response.setField(model.getField());
//		response.setFieldDependent(model.getFieldDependent());
//		response.setStatutory(model.getStatutory());
//		response.setOperator(model.getOperator());
//		response.setValue(model.getValue());
//		response.setCharge(model.getCharge());
//		response.setApi(model.getApi());
//		response.setCreatedBy(model.getCreatedBy());
//		response.setVariableFrequency(model.getVariableFrequency());
//		response.setIsAdditinalCharge(model.getIsAdditinalCharge());
//		response.setIsChargeOveride(model.getIsChargeOveride());
//		response.setStartDate(model.getStartDate());
//		response.setEndDate(model.getEndDate());
//		response.setAttr1(model.getAttr1());
//		response.setAttr2(model.getAttr2());
//		response.setAttr3(model.getAttr3());
//		response.setCreatedDate(model.getCreatedDate());
//		response.setUpdatedDate(model.getUpdatedDate());
//		response.setStatus(model.getStatus());
//		response.setPbouom(model.getPbouom());
//		response.setUmoValue(model.getUmoValue());
//		response.setIsApi(model.getIsApi());
//		response.setIsRange(model.getIsRange());
//		response.setPriceTemplateType(model.getPriceTemplateType());
//		return response;
//	}

//	public static List<PricingLineResponse> convertLineModelsToLineJsons(List<PricingLines> models) {
//		return models.stream().map(model -> convertLineModelToJson(model)).collect(Collectors.toList());
//	}

	public static List<PricingLinesFetchModel> convertPricingLinesTypeModelToJson(List<PricingLines> models) {

		return models.stream().map(model -> {

			PricingLinesFetchModel dto = new PricingLinesFetchModel();

			dto.setId(model.getId());
			dto.setHeaderId(model.getHeaderId());
			dto.setItem(model.getItem());
			dto.setOrganizationId(model.getOrganizationId());
			dto.setTaxSlab(model.getTaxSlab());
			dto.setApi(model.getApi());
			dto.setCharge(model.getCharge());
			dto.setQualifier(model.getQualifier());
			dto.setFieldDependent(model.getFieldDependent());
			dto.setPercentage(model.getPercentage());
			dto.setField(model.getField());
			dto.setVariableFrequency(model.getVariableFrequency());
			dto.setOperator(model.getOperator());
			dto.setValue(model.getValue());
			dto.setPortalVisible(model.getPortalVisible());
			dto.setStatutory(model.getStatutory());
			dto.setIsAdditinalCharge(model.getIsAdditinalCharge());
			dto.setIsChargeOveride(model.getIsChargeOveride());
			dto.setStartDate(model.getStartDate());
			dto.setEndDate(model.getEndDate());
			dto.setAttr1(model.getAttr1());
			dto.setAttr2(model.getAttr2());
			dto.setAttr3(model.getAttr3());
			dto.setCreatedBy(model.getCreatedBy());
			dto.setUpdatedBy(model.getUpdatedBy());
			dto.setCreatedDate(model.getCreatedDate());
			dto.setUpdatedDate(model.getUpdatedDate());
			dto.setStatus(model.getStatus());
			dto.setPbouom(model.getPbouom());
			dto.setUmoValue(model.getUmoValue());
			dto.setToOperator(model.getToOperator());
			dto.setToValue(model.getToValue());
			dto.setIsApi(model.getIsApi());
			dto.setIsRange(model.getIsRange());
			dto.setPriceTemplateType(model.getPriceTemplateType());
			return dto;
		}).collect(Collectors.toList());
	}

	public static List<PricingHeaderResponse> convertListModelToListJson(List<PricingHeader> models) {
		return models.stream().map(model -> convertModelToJson(model)).collect(Collectors.toList());
	}

	public static List<PricingLinesResponse> convertChargablePricingLinesTypeModelToJson(List<PricingLines> models) {

		return models.stream().map(model -> {

			PricingLinesResponse dto = new PricingLinesResponse();

			dto.setId(model.getId());
			dto.setOrganizationId(model.getOrganizationId());
			dto.setHeaderId(model.getHeaderId());
			dto.setItem(model.getItem());
			dto.setTaxSlab(model.getTaxSlab());
			dto.setApi(model.getApi());
			dto.setCharge(model.getCharge());
			dto.setQualifier(model.getQualifier());
			dto.setFieldDependent(model.getFieldDependent());
			dto.setPercentage(model.getPercentage());
			dto.setField(model.getField());
			dto.setVariableFrequency(model.getVariableFrequency());
			dto.setOperator(model.getOperator());
			dto.setValue(model.getValue());
			dto.setPortalVisible(model.getPortalVisible());
			dto.setStatutory(model.getStatutory());
			dto.setIsAdditinalCharge(model.getIsAdditinalCharge());
			dto.setIsChargeOveride(model.getIsChargeOveride());
			dto.setStartDate(model.getStartDate());
			dto.setEndDate(model.getEndDate());
			dto.setAttr1(model.getAttr1());
			dto.setAttr2(model.getAttr2());
			dto.setAttr3(model.getAttr3());
			dto.setCreatedBy(model.getCreatedBy());
			dto.setUpdatedBy(model.getUpdatedBy());
			dto.setCreatedDate(model.getCreatedDate());
			dto.setUpdatedDate(model.getUpdatedDate());
			dto.setStatus(model.getStatus());
			dto.setPbouom(model.getPbouom());
			dto.setUmoValue(model.getUmoValue());
			return dto;
		}).collect(Collectors.toList());
	}

	public static ChargablePricingResponse convertPriceListDataToChargablePriceResponse(Long priceLineId,
			double itemChargableAmount, List<TaxTemplateDto> taxLines) {

		List<TaxTemplate> taxes = new ArrayList<>();
		
		double itemPrice = itemChargableAmount;
		double calCulatedTaxAmount = 0;
		double discountAmount = 0;
		double totalPrice = 0;

		if (taxLines.size() > 0) {

			for (TaxTemplateDto tax : taxLines) {

				TaxTemplate taxData = new TaxTemplate();
				taxData.setTaxId(tax.getTaxId());
				taxData.setTaxCategoryId(tax.getTaxCategoryId());
				taxData.setTaxName(tax.getTaxName());
				taxData.setTaxCode(tax.getTaxCode());
				taxData.setTaxDescription(tax.getTaxDescription());

				Long budgetFrom = (long) 0;
				if (tax.getBudgetFrom() != null) {
					budgetFrom = tax.getBudgetFrom();
				}
				Long budgetTo = (long) 0;
				if (tax.getBudgetTo() != null) {
					budgetTo = tax.getBudgetTo();
				}

				double percentage = tax.getSlabPercentage();
				double amount = tax.getSlabAmount();

				double thisCalAmount = 0;

				/********* checking tax slabs ******************/
				if (budgetFrom >= 0 && budgetTo >= 0) {
					if (budgetFrom <= itemPrice && budgetTo >= itemPrice) {
						thisCalAmount = thisCalAmount + (itemPrice * percentage / 100);
						thisCalAmount = thisCalAmount + amount;
					}
				} else {
					thisCalAmount = thisCalAmount + (itemPrice * percentage / 100);
					thisCalAmount = thisCalAmount + amount;
				}

				calCulatedTaxAmount = calCulatedTaxAmount + thisCalAmount;

				taxData.setBudgetFrom(budgetFrom);
				taxData.setBudgetTo(budgetTo);
				taxData.setSlabAmount(amount);
				taxData.setSlabPercentage(percentage);
				taxData.setCalculatedAmount(thisCalAmount);

				taxes.add(taxData);

			}
		}

		totalPrice = itemPrice + calCulatedTaxAmount;

		ChargablePricingResponse responseData = new ChargablePricingResponse();

		responseData.setPriceLineId(priceLineId);
		responseData.setTaxPrice(calCulatedTaxAmount);
		responseData.setDiscount(discountAmount);
		responseData.setItemPrice(itemPrice);
		responseData.setTotalPrice(totalPrice);
		responseData.setTaxBreakup(taxes);

		return responseData;
	}

}
