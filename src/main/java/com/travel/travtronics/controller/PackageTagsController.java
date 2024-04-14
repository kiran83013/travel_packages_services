package com.travel.travtronics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.request.PackageTagsRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.PackageTagsService;

@RestController
@RequestMapping("/tags")
public class PackageTagsController {

	@Autowired
	PackageTagsService packageTagsService;

	@PostMapping(value = "create", produces = "application/json", consumes = "application/json")
	public APIResponse savePackageTags(@RequestBody PackageTagsRequest request) {
		return packageTagsService.savePackageTags(request);
	}

	@PutMapping(value = "update/{id}", produces = "application/json", consumes = "application/json")
	public APIResponse updatePackageTags(@PathVariable Long id, @RequestBody PackageTagsRequest request) {
		return packageTagsService.updatePackageTags(id, request);
	}

	@GetMapping(value = "findById")
	public APIResponse findById(@RequestParam Long id) {
		return packageTagsService.findById(id);
	}

	@GetMapping(value = "findAll")
	public APIResponse findAll() {
		return packageTagsService.findAll();
	}

	@PostMapping(value = "createMultipule", produces = "application/json", consumes = "application/json")
	public APIResponse saveMultipulePackageTags(@RequestBody List<PackageTagsRequest> request) {
		return packageTagsService.saveMultipulePackageTags(request);
	}
}
