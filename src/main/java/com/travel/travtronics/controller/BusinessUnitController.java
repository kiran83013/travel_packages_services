package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.BusinessUnitService;

@RestController
@RequestMapping("/businessUnit")
public class BusinessUnitController {

	@Autowired
	BusinessUnitService bunitService;



	@GetMapping(value = "/list", produces = "application/json")
	public APIResponse getAll(@RequestParam Long organizationId) {
		return bunitService.getAll(organizationId);
	}



}
