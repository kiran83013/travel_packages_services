package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.request.AddPkgQuoteRequest;
import com.travel.travtronics.request.QuoteFulfillmentRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.PackageQuotesService;

@RestController
public class PackageQuotesController {
	
	@Autowired
	PackageQuotesService packageQuotesService;
	
	
	@PostMapping(value = "/add-package-quote")
	public APIResponse addPackageQuote(@RequestBody AddPkgQuoteRequest requestData) {
	
		return packageQuotesService.addPackageQuote(requestData);
	}
	
	@PostMapping(value = "/quote-fullfillment")
	public APIResponse quoteFullfillment(@RequestBody QuoteFulfillmentRequest requestData) {
		
		return packageQuotesService.quoteFullfillment(requestData);
	}
	
	
	

}
