package com.travel.travtronics.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.bpf.model.ServicePricing;
import com.travel.travtronics.request.ServicePricingRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.ServicePricingService;

@RestController
@RequestMapping("/service_pricing")
public class ServicePricingController {

	@Autowired
	ServicePricingService servicePricingService;

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public APIResponse createDocument(@RequestBody List<ServicePricing> pricing) {
		return servicePricingService.createPricing(pricing);
	}

//	@GetMapping(value = "/headerId", produces = "application/json")
//	public APIResponse GetById(@RequestParam Long headerId) {
//		return servicePricingService.GetById(headerId);
//	}

	@PostMapping(path = "/saveAndUpdate")
	public APIResponse saveAndUpdate(@RequestBody List<ServicePricingRequest> pricing) {
		return servicePricingService.saveAndUpdate(pricing);
	}

	/*
	 * @GetMapping(value = "/get-pricing-data", produces = "application/json")
	 * public APIResponse getPricing(@RequestParam Long headerId) { return
	 * servicePricingService.getPricing(headerId); }
	 */

	@PostMapping(value = "/getPricingData", produces = "application/json")
	public APIResponse getPriceData(@RequestParam Long serviceHeaderId, @RequestBody String qualifiers,
			@RequestParam Integer pricingType,
			@RequestParam(defaultValue = "false", required = false) Boolean iacFlag) {
		return servicePricingService.getPriceInfoData(serviceHeaderId, qualifiers, pricingType, iacFlag);
	}

	@GetMapping(value = "/sr-price-range-Info", produces = "application/json")
	public APIResponse getSrPriceRange(@RequestParam Long serviceHeaderId, @RequestParam Integer pricingType) {
		return servicePricingService.getSrPriceRangeInfo(serviceHeaderId, pricingType);
	}

	/*
	 * getting pricing information for service type
	 * 
	 * @RequestParam serviceHeaderId
	 */
	@GetMapping(value = "/getPricingInfo", produces = "application/json")
	public APIResponse getPriceInfo(@RequestParam Long serviceHeaderId) {
		return servicePricingService.getPriceInformation(serviceHeaderId);
	}

	@GetMapping(value = "/feild-localization-price", produces = "application/json")
	public APIResponse feildLocalizationPriceInfo(@RequestParam Long serviceHeaderId, @RequestParam String langCode,
			@RequestParam Integer pricingType) {
		return servicePricingService.feildLocalizationPriceInfo(serviceHeaderId, langCode, pricingType);
	}

	@PostMapping(value = "/feild-localization-price-data", produces = "application/json")
	public APIResponse feildLocaLizationPricingData(@RequestParam Long serviceHeaderId, @RequestBody String qualifiers,
			@RequestParam String langCode, @RequestParam Integer pricingType,
			@RequestParam(defaultValue = "false", required = false) Boolean iacFlag) {
		return servicePricingService.feildLocaLizationPricingData(serviceHeaderId, qualifiers, langCode, pricingType,
				iacFlag);
	}

	@PostMapping(value = "/get-property-price", produces = "application/json")
	public APIResponse getPropertyPrice(@RequestParam Long serviceHeaderId, @RequestBody String qualifiers,
			@RequestParam String langCode, @RequestParam Integer pricingType,
			@RequestParam(defaultValue = "false", required = false) Boolean iacFlag) {
		return servicePricingService.getPropertyPrice(serviceHeaderId, qualifiers, langCode, pricingType, iacFlag);
	}

	@PostMapping(value = "/property-price")
	public String priceCharge(@RequestParam Double charge, @RequestParam Long serviceHeaderId,
			@RequestParam Integer pricingType, @RequestBody Map<String, Object> qualifiers) {

		if (qualifiers.containsKey("UoMValueForPropertyBooking")) {

			charge = charge * Double.valueOf(qualifiers.get("UoMValueForPropertyBooking").toString());
		}
		return (charge.toString());

	}
}
