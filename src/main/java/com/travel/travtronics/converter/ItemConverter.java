package com.travel.travtronics.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.travel.travtronics.bpf.model.Item;
import com.travel.travtronics.request.ItemRequest;
import com.travel.travtronics.response.ItemResponse;

public class ItemConverter {
	public static Item convertJsonToModel(ItemRequest request) {

		Item model = new Item();
		model.setOrganizationId(request.getOrganizationId());
		model.setName(request.getName());
		model.setCode(request.getCode());
		model.setFc(request.getFc());
		model.setType(request.getCategory());
		model.setCategory(request.getCategory());
		model.setSubCategory(request.getSubCategory());
		model.setTaxCategory(request.getTaxCategory());
		model.setTax(request.getTax());
		model.setSlab(request.getSlab());
		model.setAuthority(request.getAuthority());
		model.setDescription(request.getDescription());
		model.setCountry(request.getCountry());
		model.setState(request.getState());
		model.setCity(request.getCity());
		model.setZipCode(request.getZipCode());
		model.setStartDate(request.getStartDate());
		model.setEndDate(request.getEndDate());
		model.setCreatedBy(request.getCreatedBy());
		model.setCreatedDate(request.getCreatedDate());
		model.setStatus(request.getStatus());
		model.setUnitofmeasurement(request.getUnitofmeasurement());

		return model;
	}

	public static Item updateJsonToModel(ItemRequest request, Long id) {
		Item model = new Item();

		if (id != null)
			model.setId(id);
		model.setOrganizationId(request.getOrganizationId());
		model.setName(request.getName());
		model.setCode(request.getCode());
		model.setFc(request.getFc());
		model.setType(request.getCategory());
		model.setCategory(request.getCategory());
		model.setSubCategory(request.getSubCategory());
		model.setTaxCategory(request.getTaxCategory());
		model.setTax(request.getTax());
		model.setSlab(request.getSlab());
		model.setAuthority(request.getAuthority());
		model.setDescription(request.getDescription());
		model.setCountry(request.getCountry());
		model.setState(request.getState());
		model.setCity(request.getCity());
		model.setZipCode(request.getZipCode());
		model.setStartDate(request.getStartDate());
		model.setEndDate(request.getEndDate());
		model.setStatus(request.getStatus());

		model.setUpdatedBy(request.getUpdatedBy());
		model.setUpdatedDate(request.getUpdatedDate());
		model.setUnitofmeasurement(request.getUnitofmeasurement());
		return model;
	}

	public static ItemResponse convertModeltoResponse(Item model) {

		ItemResponse response = new ItemResponse();
		response.setId(model.getId());
		response.setOrganizationId(model.getOrganizationId());
		response.setName(model.getName());
		response.setCode(model.getCode());
		response.setFc(model.getFc());
		response.setType(model.getCategory());
		response.setCategory(model.getCategory());
		response.setSubCategory(model.getSubCategory());
		response.setTaxCategory(model.getTaxCategory());
		response.setTax(model.getTax());
		response.setSlab(model.getSlab());
		response.setAuthority(model.getAuthority());
		response.setDescription(model.getDescription());
		response.setCountry(model.getCountry());
		response.setState(model.getState());
		response.setCity(model.getCity());
		response.setZipCode(model.getZipCode());
		response.setStartDate(model.getStartDate());
		response.setEndDate(model.getEndDate());
		response.setStatus(model.getStatus());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setUnitofmeasurement(model.getUnitofmeasurement());
		return response;

	}

	public static List<ItemResponse> convertListModelToListResponse(List<Item> models) {
		return models.stream().map(model -> convertModeltoResponse(model)).collect(Collectors.toList());
	}
}
