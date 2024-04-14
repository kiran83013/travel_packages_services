package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.GenerateSetupMasterService;

@RestController
@RequestMapping("/master_general_setup")
public class GenerateSetupMasterController {

	@Autowired
	GenerateSetupMasterService generateSetupMasterService;


	@GetMapping(value = "/list-by-orgId-category", produces = "application/json")
	public APIResponse getAllByOrgAndCategory(@RequestParam Long orgId, @RequestParam Long categoryId) {
		return generateSetupMasterService.getAllByOrgAndCategory(orgId, categoryId);
	}


}
