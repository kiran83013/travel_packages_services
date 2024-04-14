package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.DepartmentsService;

@RestController
@RequestMapping("/Departments/")
public class DepartmentsController {

	@Autowired
	DepartmentsService departmentsService;



	@GetMapping(value = "list", produces = "application/json")
	public APIResponse list(@RequestParam Long organizationId) {
		return departmentsService.list(organizationId);
	}


}
