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
import com.travel.travtronics.request.PricingRequestModel;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.service.PricingService;

@RestController
public class PricingController {
	@Autowired
	PricingService salaryService;
	
	@PostMapping(value = "create-pricing")
	public MessageStatusResponse createPrice(@RequestBody PricingRequestModel model) {
		return salaryService.createPrice(model);
	}

	@PutMapping(value = "modify-pricing/{id}")
	public MessageStatusResponse modifyPrice(@RequestBody PricingRequestModel model, @PathVariable Long id) {
		return salaryService.modifyPrice(model, id);
	}
	
	@GetMapping(value = "get-pricing-headers-by-organizationid")
	public APIResponse getPriceHeaderListByOrganization(@RequestParam Long organization) {
		return salaryService.getPriceHeaderListByOrganization(organization);
	}
	
	@GetMapping(value = "get-pricing-header/{id}")
	public APIResponse getPriceHeader(@PathVariable Long id) {
		return salaryService.getPriceHeader(id);
	}

	@GetMapping(value = "get-pricing-lines-by-header/{headerId}")
	public APIResponse getPriceLinesByHeader(@PathVariable Long headerId) {
		return salaryService.getPriceLinesByHeader(headerId);
	}
	
	@GetMapping(value = "chargable-price-lines/{srHeaderId}")
	public APIResponse getChargablePriceLines(@PathVariable Long srHeaderId) {
		return salaryService.getChargablePriceLinesBySrTypeId(srHeaderId);
	}
	
	@GetMapping(value = "/get-pagenation")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organization,
			@RequestParam(required = false) String name, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return salaryService.getPagenationByOrganization(organization, name, pageNo, pageSize, sortBy, sortType);
	}
	
	@GetMapping(value = "/get-items-by-pricing")
	public APIResponse getItemsByPricingLinesHeader(@RequestParam Long headerId, @RequestParam Long organizationId) {
		return salaryService.getItemsByPricingLinesByHeaderId(headerId,organizationId);
	}
}
