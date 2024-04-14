package com.travel.travtronics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.MasterService;

@RestController
@RequestMapping("/master/{tableName}")
public class MasterController {

	private final MasterService service;

	public MasterController(MasterService service) {
		super();
		this.service = service;
	}

	@GetMapping("/all")
	public APIResponse getAll(@PathVariable String tableName) {
		return service.getAll(tableName);
	}

}
