package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.PackagesService;

@RestController
public class HotelsPkgPriceController {
	
	@Autowired
	PackagesService packagesService;
	
	@PostMapping(value = "/get-hotel-price-combinations")
	public APIResponse getHotelPriceCombinations(@RequestParam int adtCount, @RequestParam int chdCount) {
	
		return packagesService.getHotelPriceCombinations(adtCount, chdCount);
	}

}
