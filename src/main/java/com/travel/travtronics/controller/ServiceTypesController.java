package com.travel.travtronics.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.ServiceTypePricingService;
import com.travel.travtronics.service.ServiceTypesService;

@RestController
public class ServiceTypesController {

	@Autowired
	ServiceTypesService serviceTypeService;

	@Autowired
	ServiceTypePricingService servicePricingService;

	@GetMapping(path = "/service-type-headers")
	public APIResponse getServiceHeaderTypes(
			@RequestParam(required = false, defaultValue = "0") String organizationId) {
		return serviceTypeService.getServiceHeaderTypes(organizationId);
	}

	@GetMapping(path = "/service-type-lines/{headerId}")
	public @ResponseBody ResponseEntity<?> getServiceTypeLine(@PathVariable Long headerId,
			@RequestParam(name = "isPricing", required = false) Long isPricing) {

		return isPricing != null ? serviceTypeService.getServiceTypeLinesByIsPricing(headerId, isPricing)
				: serviceTypeService.getServiceTypeLines(headerId);
	}

	@GetMapping(path = "/service-type-header-lines-form-data/{scheduleId}")
	public APIResponse getServiceTypeHeaderAndLinesFromData(@PathVariable Long scheduleId) {
		return serviceTypeService.getServiceTypeHeaderAndLinesFromDataByHeadeId(scheduleId);
	}

	@PostMapping(value = "/get-item-prices", produces = "application/json")
	public APIResponse getItemsPrice(@RequestParam Long serviceHeaderId, @RequestBody Map<String, Object> qualifiers) {
		return servicePricingService.getItemsPrice(serviceHeaderId, qualifiers);
	}

	@GetMapping(path = "/get-price-lines-for-schedule-price-linking/{srtypeId}")
	public APIResponse priceLinesForSchedulePriceLinking(@PathVariable Long srtypeId) {
		return serviceTypeService.priceLinesForSchedulePriceLinking(srtypeId);
	}
	
	@GetMapping(value = "/headerId", produces = "application/json")
	public APIResponse GetById(@RequestParam Long headerId) {
		return servicePricingService.GetById(headerId);
	}

}
