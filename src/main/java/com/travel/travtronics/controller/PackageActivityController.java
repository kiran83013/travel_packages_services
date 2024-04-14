package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.service.PackageTagsService;

@RestController
@RequestMapping("/PackageActivity")
public class PackageActivityController {

	@Autowired
	PackageTagsService paService;

	@GetMapping(value = "/get-data-serach")
	public ResponseEntity<?> getSearchData(@RequestParam(required = false) String activityName,
			@RequestParam(required = false) String location, @RequestParam(required = false) String city,
			@RequestParam(required = false) String country) {
		return paService.getSearchData(activityName, location, city, country);
	}
}
