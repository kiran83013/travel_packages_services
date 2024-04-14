package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.AdditionalFieldsService;

@RestController
@RequestMapping("/AdditionalFields")
public class AdditionalFieldsController {

	@Autowired
	AdditionalFieldsService additionalFieldsService;



	@GetMapping(path = "/additional-fields/{headerId}")
	public APIResponse getadditionalFieldsByHeaderId(@PathVariable Long headerId,
			@RequestParam(name = "isPricing", required = false) Long isPricing) {
		return additionalFieldsService.getadditionalFieldsByHeaderId(headerId, isPricing);

	}

}
