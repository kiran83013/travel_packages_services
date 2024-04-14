package com.travel.travtronics.converter;


import java.util.List;
import java.util.stream.Collectors;

import com.travel.travtronics.bpf.model.TaxSlabHeader;
import com.travel.travtronics.bpf.model.TaxSlabLines;
import com.travel.travtronics.request.TaxHeaderRequest;
import com.travel.travtronics.request.TaxLinesRequest;
import com.travel.travtronics.response.TaxHeaderResponse;
import com.travel.travtronics.response.TaxLinesResponse;

public class TaxConverter {
	public static TaxSlabHeader convertTaxJsonToModel(TaxHeaderRequest request) {
		TaxSlabHeader model = new TaxSlabHeader();

		model.setOrganizationId(request.getOrganizationId());
		model.setTaxCategory(request.getTaxCategory());
		model.setDescription(request.getDescription());
		model.setStartDate(request.getStartDate());
		model.setEndDate(request.getEndDate());
		model.setCreatedBy(request.getCreatedBy());
		model.setCreatedDate(request.getCreatedDate());
		model.setStatus(request.getStatus());

		return model;
	}

	public static TaxSlabHeader updateTaxJsonToModel(TaxHeaderRequest request, Long id) {
		TaxSlabHeader model = new TaxSlabHeader();

		if (id != null && id != 0)
			model.setId(id);
		model.setOrganizationId(request.getOrganizationId());
		model.setTaxCategory(request.getTaxCategory());
		model.setDescription(request.getDescription());
		model.setStartDate(request.getStartDate());
		model.setEndDate(request.getEndDate());
		model.setUpdatedBy(request.getUpdatedBy());
		model.setUpdatedDate(request.getUpdatedDate());
		model.setStatus(request.getStatus());

		return model;
	}

	public static TaxHeaderResponse convertTaxModelToResponse(TaxSlabHeader model) {
		TaxHeaderResponse response = new TaxHeaderResponse();
		response.setId(model.getId());
		response.setOrganizationId(model.getOrganizationId());
		response.setTaxCategory(model.getTaxCategory());
		response.setDescription(model.getDescription());
		response.setStartDate(model.getStartDate());
		response.setEndDate(model.getEndDate());
		response.setCreatedBy(model.getCreatedBy());
		response.setCreatedDate(model.getCreatedDate());
		response.setStatus(model.getStatus());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());

		return response;
	}

	public static List<TaxHeaderResponse> convertTaxJsonHeaderToModels(List<TaxSlabHeader> requests) {
		return requests.stream().map(model -> convertTaxModelToResponse(model)).collect(Collectors.toList());
	}

	public static TaxSlabLines convertTaxLineJsonToModel(TaxLinesRequest request) {

		TaxSlabLines line = new TaxSlabLines();
		if (request.getId() != null && request.getId() != 0)
			line.setId(request.getId());

		if (request.getTaxSlabHeaderId() != null && request.getTaxSlabHeaderId() != 0)
			line.setTaxSlabHeaderId(request.getTaxSlabHeaderId());

		line.setOrganizationId(request.getOrganizationId());
		line.setTaxType(request.getTaxType());
		line.setDescription(request.getDescription());
		line.setFrom(request.getFrom());
		line.setTo(request.getTo());
		line.setSlabAmount(request.getSlabAmount());
		line.setSlabPercentage(request.getSlabPercentage());
		line.setStartDate(request.getStartDate());
		line.setEndDate(request.getEndDate());
		line.setAttr1(request.getAttr1());
		line.setAttr2(request.getAttr2());
		line.setAttr3(request.getAttr3());
		line.setCreatedBy(request.getCreatedBy());
		line.setUpdatedBy(request.getUpdatedBy());
		line.setCreatedDate(request.getCreatedDate());
		line.setUpdatedDate(request.getUpdatedDate());
		line.setStatus(request.getStatus());

		return line;

	}

	public static List<TaxSlabLines> convertTaxJsonLinesToModels(List<TaxLinesRequest> requests) {
		return requests.stream().map(model -> convertTaxLineJsonToModel(model)).collect(Collectors.toList());
	}

	public static TaxLinesResponse convertLineToResponse(TaxSlabLines model) {
		TaxLinesResponse response = new TaxLinesResponse();

		response.setId(model.getId());

		if (model.getTaxSlabHeaderId() != null && model.getTaxSlabHeaderId() != 0)
			response.setTaxSlabHeaderId(model.getTaxSlabHeaderId());
		response.setOrganizationId(model.getOrganizationId());
		response.setTaxType(model.getTaxType());
		response.setDescription(model.getDescription());
		response.setFrom(model.getFrom());
		response.setTo(model.getTo());
		response.setSlabAmount(model.getSlabAmount());
		response.setSlabPercentage(model.getSlabPercentage());
		response.setStartDate(model.getStartDate());
		response.setEndDate(model.getEndDate());
		response.setAttr1(model.getAttr1());
		response.setAttr2(model.getAttr2());
		response.setAttr3(model.getAttr3());
		response.setCreatedDate(model.getCreatedDate());
		response.setCreatedBy(model.getCreatedBy());
		response.setUpdatedBy(model.getUpdatedBy());
		response.setUpdatedDate(model.getUpdatedDate());
		response.setStatus(model.getStatus());

		return response;

	}

	public static List<TaxLinesResponse> convertTaxLineModelsToResponse(List<TaxSlabLines> requests) {
		return requests.stream().map(model -> convertLineToResponse(model)).collect(Collectors.toList());
	}
}
