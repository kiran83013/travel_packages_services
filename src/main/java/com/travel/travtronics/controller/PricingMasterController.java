package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.MasterPricingServices;

@RestController
@RequestMapping("/price-type-master")
public class PricingMasterController {

	@Autowired
	MasterPricingServices pricingService;



	@GetMapping("/price-types")
	public APIResponse getPricingTypes() {
		return pricingService.getPricingTypes();
	}


	@GetMapping("/price-type-item")
	public APIResponse getPricingTypeItems(@RequestParam Long id) throws Exception {
		return pricingService.getPricingTypeItems(id);
	}
	
	@GetMapping("/price-type-items-info")
	public APIResponse getPricingTypeItems() {
		return pricingService.getPricingTypeItems();
	}


}
