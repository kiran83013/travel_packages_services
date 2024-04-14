package com.travel.travtronics.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.travel.travtronics.bpf.model.Item;
import com.travel.travtronics.bpf.model.PricingHeader;
import com.travel.travtronics.bpf.model.PricingLines;
import com.travel.travtronics.bpf.model.ServicePricing;
import com.travel.travtronics.request.ServicePricingRequest;
import com.travel.travtronics.request.dto.TaxTemplateDto;
import com.travel.travtronics.response.EservicePricingResponse;
import com.travel.travtronics.response.PriceHeaderLineResponse;
import com.travel.travtronics.response.PriceHearderTemplate;
import com.travel.travtronics.response.PriceLineResponseModel;
import com.travel.travtronics.response.ServicePriceFinalResponse;
import com.travel.travtronics.response.TaxTemplate;

@Component
public class ServicePricingConverter {
	
	public static PriceHeaderLineResponse convertHeaderLineModelToDto(PricingHeader model,
			List<PriceLineResponseModel> pricingLines) {
		PriceHeaderLineResponse response = new PriceHeaderLineResponse();
		response.setPricingHeader(model);
		response.setPricingLines(pricingLines);
		return response;
	}
	
	public static ServicePriceFinalResponse convertModelToServicePriceFinalResponse(ServicePricing request,
			PriceHeaderLineResponse response) {

		ServicePriceFinalResponse service = new ServicePriceFinalResponse();
		service.setId(request.getId());

		service.setPriceList(request.getPriceList());

		service.setHeaderId(request.getHeaderId());

		service.setStartDate(request.getStartDate());

		service.setEndDate(request.getEndDate());

		service.setCreatedBy(request.getCreatedBy());

		service.setUpdatedBy(request.getUpdatedBy());

		service.setCreatedDate(request.getCreatedDate());

		service.setUpdatedDate(request.getUpdatedDate());

		service.setStatus(request.getStatus());

		service.setPriceListModel(response);

		return service;
	}

	public static PriceLineResponseModel convertPriceLineModelToResponse(PricingLines model, Item item) {
		PriceLineResponseModel response = new PriceLineResponseModel();
		response.setId(model.getId());
		response.setHeaderId(model.getHeaderId());
		response.setItem(model.getItem());
		response.setTaxSlab(model.getTaxSlab());
		response.setPercentage(model.getPercentage());
		response.setPortalVisible(model.getPortalVisible());
		response.setQualifier(model.getQualifier());
		response.setField(model.getField());
		response.setFieldDependent(model.getFieldDependent());
		response.setStatutory(model.getStatutory());
		response.setOperator(model.getOperator());
		response.setValue(model.getValue());
		response.setCharge(model.getCharge());
		response.setApi(model.getApi());
		response.setCreatedBy(model.getCreatedBy());
		response.setVariableFrequency(model.getVariableFrequency());

		response.setStartDate(model.getStartDate());
		response.setEndDate(model.getEndDate());
		response.setAttr1(model.getAttr1());
		response.setAttr2(model.getAttr2());
		response.setAttr3(model.getAttr3());
		response.setCreatedDate(model.getCreatedDate());
		response.setUpdatedDate(model.getUpdatedDate());
		response.setStatus(model.getStatus());
		response.setItemConfig(item);
		return response;
	}

	public static EservicePricingResponse convertPropertiesListDataToPropertiesResponse(String inputFieldName,
			PriceHearderTemplate lineInfo, List<TaxTemplateDto> taxLines, Double uomValue, Boolean isApi) {
		List<TaxTemplate> taxes = new ArrayList<>();

		double itemPrice = 0.0;
		if (isApi == false) {
			itemPrice = lineInfo.getItemPrice() * uomValue;
		} else {
			itemPrice = uomValue;
		}

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

				double percentage = Double.valueOf(tax.getSlabPercentage());
				double amount = Double.valueOf(tax.getSlabAmount());

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

		EservicePricingResponse responseData = new EservicePricingResponse();
		responseData.setPriceLineId(lineInfo.getPriceLineId());
		responseData.setItemId(lineInfo.getItemId());
		responseData.setItemName(lineInfo.getItemName());
		responseData.setItemCode(inputFieldName);
		responseData.setInputValue(uomValue.intValue());
		responseData.setDescription(lineInfo.getItemDesc());
		responseData.setOrganizationId(lineInfo.getOrganizationId());
		responseData.setOrganizationName(lineInfo.getOrganizationName());
		responseData.setTaxPrice(calCulatedTaxAmount);
		responseData.setDiscount(discountAmount);
		responseData.setItemPrice(itemPrice);
		responseData.setTotalPrice(totalPrice);
		responseData.setTaxBreakup(taxes);

		return responseData;
	}

	public static EservicePricingResponse convertPriceListDataToPriceResponse(String inputFieldName,
			PriceHearderTemplate lineInfo, List<TaxTemplateDto> taxLines) {

		System.out.println("price :" + lineInfo.getItemPrice());
		List<TaxTemplate> taxes = new ArrayList<>();

		double itemPrice = lineInfo.getItemPrice();
		double calCulatedTaxAmount = 0;
		double discountAmount = 0;
		double totalPrice = 0;

		if (taxLines.size() > 0) {

			for (TaxTemplateDto tax : taxLines) {

				TaxTemplate taxData = new TaxTemplate();
				taxData.setTaxId(tax.getTaxId());
				// System.out.println(tax.getTaxId());
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

				double percentage = Double.valueOf(tax.getSlabPercentage());
				double amount = Double.valueOf(tax.getSlabAmount());

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

		EservicePricingResponse responseData = new EservicePricingResponse();
		responseData.setPriceLineId(lineInfo.getPriceLineId());
		responseData.setItemId(lineInfo.getItemId());
		responseData.setItemName(lineInfo.getItemName());
		responseData.setInputValue(1);
		responseData.setDescription(lineInfo.getItemDesc());
		responseData.setOrganizationId(lineInfo.getOrganizationId());
		responseData.setOrganizationName(lineInfo.getOrganizationName());
		responseData.setTaxPrice(calCulatedTaxAmount);
		responseData.setDiscount(discountAmount);
		responseData.setItemPrice(itemPrice);
		responseData.setTotalPrice(totalPrice);
		responseData.setTaxBreakup(taxes);

		System.out.println("responseData" + responseData);
		return responseData;
	}

	public static ServicePricing convertPricingToModel(ServicePricingRequest request) {

		ServicePricing service = new ServicePricing();

		if (request.getId() != null && request.getId() != 0)
			service.setId(request.getId());

		if (request.getOrganizationId() != null && request.getOrganizationId() != 0)
			service.setOrganizationId(request.getOrganizationId());

		if (request.getPriceList() != null && request.getPriceList() != 0)
			service.setPriceList(request.getPriceList());

		if (request.getHeaderId() != null && request.getHeaderId() != 0)
			service.setHeaderId(request.getHeaderId());

		if (request.getServicePriceType() != null && request.getServicePriceType() != 0)
			service.setServicePriceType(request.getServicePriceType());

		if (request.getStartDate() != null)
			service.setStartDate(request.getStartDate());

		if (request.getEndDate() != null)
			service.setEndDate(request.getEndDate());

		if (request.getCreatedBy() != null)
			service.setCreatedBy(request.getCreatedBy());

		if (request.getUpdatedBy() != null)
			service.setUpdatedBy(request.getUpdatedBy());

		if (request.getCreatedDate() != null)
			service.setCreatedDate(request.getCreatedDate());

		if (request.getUpdatedDate() != null)
			service.setUpdatedDate(request.getUpdatedDate());

		if (request.getStatus() != null)
			service.setStatus(request.getStatus());

		return service;
	}

	public static EservicePricingResponse convertPropertiesListDataToPropertiesResponse(PriceHearderTemplate lineInfo,
			List<TaxTemplateDto> taxLines, Double uomValue, Boolean isApi) {
		List<TaxTemplate> taxes = new ArrayList<>();

		double itemPrice = 0.0;
		if (isApi == false)
			itemPrice = lineInfo.getItemPrice() * uomValue;
		else
			/*
			 * uomValue is a vaible which is a result charge accuired from api
			 */
			itemPrice = uomValue;

		double calCulatedTaxAmount = 0;
		double discountAmount = 0;
		double totalPrice = 0;

		if (taxLines.size() > 0) {

			for (TaxTemplateDto tax : taxLines) {

				TaxTemplate taxData = new TaxTemplate();
				taxData.setTaxId(tax.getTaxId());
				// System.out.println(tax.getTaxId());
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

				// System.out.println(tax.getSlabPercentage());
				// System.out.println(tax.getSlabAmount());

				double percentage = Double.valueOf(tax.getSlabPercentage());
				double amount = Double.valueOf(tax.getSlabAmount());

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

		EservicePricingResponse responseData = new EservicePricingResponse();
		responseData.setPriceLineId(lineInfo.getPriceLineId());
		responseData.setItemId(lineInfo.getItemId());
		responseData.setItemName(lineInfo.getItemName());
		responseData.setDescription(lineInfo.getItemDesc());
		responseData.setOrganizationId(lineInfo.getOrganizationId());
		responseData.setOrganizationName(lineInfo.getOrganizationName());
		responseData.setTaxPrice(calCulatedTaxAmount);
		responseData.setDiscount(discountAmount);
		responseData.setItemPrice(itemPrice);
		responseData.setTotalPrice(totalPrice);
		responseData.setTaxBreakup(taxes);

		return responseData;
	}

	public static EservicePricingResponse convertPriceListDataToPriceResponse(PriceHearderTemplate lineInfo,
			List<TaxTemplateDto> taxLines) {

		System.out.println("price :" + lineInfo.getItemPrice());
		List<TaxTemplate> taxes = new ArrayList<>();

		double itemPrice = lineInfo.getItemPrice();
		double calCulatedTaxAmount = 0;
		double discountAmount = 0;
		double totalPrice = 0;

		if (taxLines.size() > 0) {

			for (TaxTemplateDto tax : taxLines) {

				TaxTemplate taxData = new TaxTemplate();
				taxData.setTaxId(tax.getTaxId());
				// System.out.println(tax.getTaxId());
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

				// System.out.println(tax.getSlabPercentage());
				// System.out.println(tax.getSlabAmount());

				double percentage = Double.valueOf(tax.getSlabPercentage());
				double amount = Double.valueOf(tax.getSlabAmount());

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

		EservicePricingResponse responseData = new EservicePricingResponse();
		responseData.setPriceLineId(lineInfo.getPriceLineId());
		responseData.setItemId(lineInfo.getItemId());
		responseData.setItemName(lineInfo.getItemName());
		responseData.setDescription(lineInfo.getItemDesc());
		responseData.setOrganizationId(lineInfo.getOrganizationId());
		responseData.setOrganizationName(lineInfo.getOrganizationName());
		responseData.setTaxPrice(calCulatedTaxAmount);
		responseData.setDiscount(discountAmount);
		responseData.setItemPrice(itemPrice);
		responseData.setTotalPrice(totalPrice);
		responseData.setTaxBreakup(taxes);

		System.out.println("responseData" + responseData);
		return responseData;
	}

}
