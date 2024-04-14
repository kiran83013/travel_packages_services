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

import com.travel.travtronics.request.MedInfoRequest;
import com.travel.travtronics.request.dto.MedInfoSearchParametersDTO;
import com.travel.travtronics.request.dto.PackageImageConfigDto;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.TravelPackageConfigurationService;

@RestController
@RequestMapping("/travelPackageConfiguration")
public class TravelPackageConfigurationController {

	@Autowired
	TravelPackageConfigurationService medInfoService;

	@PostMapping(value = "create", produces = "application/json", consumes = "application/json")
	public APIResponse medInfo(@RequestBody MedInfoRequest request) throws Exception {
		return medInfoService.medInfo(request);
	}

	@PutMapping(value = "update/{id}", produces = "application/json", consumes = "application/json")
	public APIResponse updateMedInfo(@PathVariable Long id, @RequestBody MedInfoRequest request) throws Exception {
		return medInfoService.updateMedInfo(id, request);
	}

	@GetMapping(value = "findById")
	public APIResponse findById(@RequestParam Long id) throws Exception {
		return medInfoService.findById(id);
	}

	@PostMapping(value = "package-images", produces = "application/json", consumes = "application/json")
	public APIResponse packageImages(@RequestBody List<PackageImageConfigDto> request) {
		return medInfoService.packageImages(request);
	}

	@GetMapping(value = "package-images")
	public APIResponse getPackageImages(@RequestParam Long packageId) {
		return medInfoService.getPackageImages(packageId);
	}

	@PostMapping("/search-package")
	public APIResponse searchPackageData(@RequestBody MedInfoSearchParametersDTO searchParameters) {
		return medInfoService.searchPackageData(searchParameters);
	}

}
