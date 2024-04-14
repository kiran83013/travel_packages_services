package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.request.TaxRequestModel;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.service.TaxService;

@RestController
public class TaxSlabController {
	@Autowired
	TaxService taxService;

	@PostMapping(value = "create-tax-slab")
	public MessageStatusResponse createTaxSlab(@RequestBody TaxRequestModel model) {
		return taxService.createTaxSlab(model);
	}

	@GetMapping(value = "get-tax-slab-header/{id}")
	public APIResponse getTaxHeader(@PathVariable Long id) {
		return taxService.getTaxHeader(id);
	}

	@GetMapping(value = "get-tax-slab-headers-by-organization")
	public APIResponse getTaxHeaderListByOrganization(@RequestParam Long organizationId) {
		return taxService.getTaxHeaderListByOrganization(organizationId);
	}

	@PutMapping(value = "modify-tax-slab/{id}")
	public MessageStatusResponse modifyTaxSlab(@RequestBody TaxRequestModel model, @PathVariable Long id) {
		return taxService.modifyTaxSlab(model, id);
	}

	@GetMapping(value = "get-taxLines-by-header/{id}")
	public APIResponse getTaxLinesByHeader(@PathVariable Long id) {
		return taxService.getTaxLinesByHeader(id);
	}

	@GetMapping(value = "get-taxLines-by-item/{id}")
	public APIResponse getTaxDataByComponent(@PathVariable Long id) {
		return taxService.getTaxDataByComponent(id);
	}

	@GetMapping(value = "/get-pagening-by-organization")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,
			@RequestParam(required = false) Long taxCategory, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return taxService.getPagenationByOrganization(organizationId, taxCategory, pageNo, pageSize, sortBy, sortType);
	}
}
