package com.travel.travtronics.converter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.travel.travtronics.packages.model.PackageItineraryHeaderModel;
import com.travel.travtronics.packages.model.PackageItineraryModel;
import com.travel.travtronics.packages.model.PackageItineraryPriceHeaderModel;
import com.travel.travtronics.packages.model.PackageItineraryPriceLinesModel;
import com.travel.travtronics.request.ItineraryPriceLinesRequest;
import com.travel.travtronics.request.UpdateItineraryPriceLinesRequest;
import com.travel.travtronics.response.PackagesPriceInfoResponse;

public class PackagesConverter {

	ZonedDateTime instance = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	String currentDateTime = formatter.format(instance); // 15-02-2022 12:43

	public static PackageItineraryPriceHeaderModel convertPriceHeaderRequestDataToModel(Integer itineraryId,
			String validFromDate, String validToDate, Integer createdBy, String ipAddress, String name,
			String description, Integer buId, Integer ccId, Integer locId, Integer custType) {

		PackageItineraryPriceHeaderModel modelData = new PackageItineraryPriceHeaderModel();

		modelData.setItineraryId(itineraryId);
		modelData.setValidFrom(validFromDate);
		modelData.setValidTo(validToDate);
		modelData.setCreatedBy(createdBy);
		modelData.setCreatedDate(new PackagesConverter().currentDateTime);
		modelData.setIpAddress(ipAddress);
		modelData.setBuId(buId);
		modelData.setCcId(ccId);
		modelData.setLocationId(locId);
		modelData.setName(name);
		modelData.setDescription(description);
		modelData.setCustomerType(custType);
		return modelData;
	}

	public static PackageItineraryPriceLinesModel convertPriceLinesRequestDataToModel(
			ItineraryPriceLinesRequest lineData, Integer headerId, PackageItineraryModel itineraryLineInfo) {

		PackageItineraryPriceLinesModel modelData = new PackageItineraryPriceLinesModel();

		modelData.setHeaderId(headerId);
		modelData.setItineraryLineId(lineData.getItineraryLineId());
		modelData.setItemId(lineData.getItemId());
		modelData.setDynamicPrice(lineData.getDynamicPrice());
		modelData.setStrandedPrice(lineData.getStrandedPrice());
		modelData.setSetOption(lineData.getSetOption());
		modelData.setSetName(lineData.getSetName());
		modelData.setSetPrimary(lineData.getSetPrimary());
		modelData.setItineraryDay(itineraryLineInfo.getItineraryDay());
		modelData.setCategory(itineraryLineInfo.getCategory());
		modelData.setBreakup(lineData.getBreakup());
		modelData.setAdtPrice(lineData.getAdtPrice());
		modelData.setChdPrice(lineData.getChdPrice());
		modelData.setInfPrice(lineData.getInfPrice());
		modelData.setCreatedBy(lineData.getCreatedBy());
		modelData.setCreatedDate(new PackagesConverter().currentDateTime);
		modelData.setIpAddress(lineData.getIpAddress());

		return modelData;

	}

	public static PackagesPriceInfoResponse convertPriceLinesModelDataToResponse(
			PackageItineraryPriceHeaderModel headerData, List<PackageItineraryPriceLinesModel> itineraryPriceLines,
			PackageItineraryHeaderModel itineraryInfo) {

		PackagesPriceInfoResponse responseInfo = new PackagesPriceInfoResponse();

		responseInfo.setId(headerData.getId());
		responseInfo.setItineraryId(headerData.getItineraryId());
		responseInfo.setItineraryName(itineraryInfo.getName());
		responseInfo.setValidFrom(headerData.getValidFrom());
		responseInfo.setValidTo(headerData.getValidTo());
		responseInfo.setCreatedBy(headerData.getCreatedBy());
		responseInfo.setCreatedDate(headerData.getCreatedDate());
		responseInfo.setUpdatedBy(headerData.getUpdatedBy());
		responseInfo.setUpdatedDate(headerData.getUpdatedDate());
		responseInfo.setIpAddress(headerData.getIpAddress());
		responseInfo.setItineraryPriceLines(itineraryPriceLines);
		responseInfo.setBuId(headerData.getBuId());
		responseInfo.setCcId(headerData.getCcId());
		responseInfo.setLocationId(headerData.getLocationId());
		responseInfo.setName(headerData.getName());
		responseInfo.setDescription(headerData.getDescription());
		responseInfo.setCustomerType(headerData.getCustomerType());
		return responseInfo;
	}

	public static PackageItineraryPriceHeaderModel convertUpdatePriceHeaderRequestDataToModel(
			PackageItineraryPriceHeaderModel headerInfo, Integer itineraryId, String validFromDate, String validToDate,
			Integer updatedBy, String ipAddress, String name, String description, Integer buId, Integer ccId,
			Integer locId, Integer custType) {

		headerInfo.setId(headerInfo.getId());
		headerInfo.setItineraryId(itineraryId);
		headerInfo.setValidFrom(validFromDate);
		headerInfo.setValidTo(validToDate);
		headerInfo.setUpdatedBy(updatedBy);
		headerInfo.setUpdatedDate(new PackagesConverter().currentDateTime);
		headerInfo.setIpAddress(ipAddress);
		headerInfo.setBuId(buId);
		headerInfo.setCcId(ccId);
		headerInfo.setLocationId(locId);
		headerInfo.setName(name);
		headerInfo.setDescription(description);
		headerInfo.setCustomerType(custType);
		return headerInfo;
	}

	public static PackageItineraryPriceLinesModel convertUpdatePriceLinesRequestDataToModel(
			PackageItineraryPriceLinesModel updateData, UpdateItineraryPriceLinesRequest requestData, Integer headerId,
			PackageItineraryModel itineraryLineInfo) {

		updateData.setLineId(requestData.getLineId());
		updateData.setHeaderId(headerId);
		updateData.setItineraryLineId(requestData.getItineraryLineId());
		updateData.setItemId(requestData.getItemId());
		updateData.setDynamicPrice(requestData.getDynamicPrice());
		updateData.setStrandedPrice(requestData.getStrandedPrice());
		updateData.setSetOption(requestData.getSetOption());
		updateData.setSetName(requestData.getSetName());
		updateData.setSetPrimary(requestData.getSetPrimary());
		updateData.setBreakup(requestData.getBreakup());
		updateData.setItineraryDay(itineraryLineInfo.getItineraryDay());
		updateData.setCategory(itineraryLineInfo.getCategory());
		updateData.setAdtPrice(requestData.getAdtPrice());
		updateData.setChdPrice(requestData.getChdPrice());
		updateData.setInfPrice(requestData.getInfPrice());
		updateData.setUpdatedBy(requestData.getUpdatedBy());
		updateData.setUpdatedDate(new PackagesConverter().currentDateTime);
		updateData.setIpAddress(requestData.getIpAddress());

		return updateData;

	}

	/*
	 * public static List<?> convertReceiptModelsToResponse(List<ReceiptHeaderModel>
	 * models) {
	 * 
	 * return models.stream().map(model -> { ReceiptReportResponse response = new
	 * ReceiptReportResponse();
	 * 
	 * response.setReceiptNumber(model.getReceiptNumber());
	 * response.setReceiptDate(model.getReceiptDate());
	 * response.setReceiptCurrenyCode(model.getReciptCurrencyCode());
	 * response.setReceiptAmount(model.getReceiptAmount());
	 * response.setReceiptContactName(model.getReceiptContactName());
	 * response.setReceiptContactMobile(model.getReceiptContactMobile());
	 * response.setReceiptContactEmail(model.getReceiptContactMobile());
	 * response.setStatusId(model.getReceiptStatusId()); return response;
	 * }).collect(Collectors.toList());
	 * 
	 * }
	 */

}
