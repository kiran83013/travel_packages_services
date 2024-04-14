package com.travel.travtronics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.request.PackageSchedulePriceItemLinkRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.PackageSchedulePriceItemLinkService;

import jakarta.validation.Valid;

@RestController
public class PackageSchedulePriceItemController {

	@Autowired
	PackageSchedulePriceItemLinkService packageSchedulePriceItemLinkService;

//	@PostMapping(value = "/create-package-schedule-price-item-link")
//	public APIResponse savePriceItemLink(@Valid @RequestBody PackageSchedulePriceItemLinkRequest requestList) {
//		return packageSchedulePriceItemLinkService.save(requestList);
//	}

//	@PutMapping(value = "/update-package-schedule-price-item-link/{id}", consumes = "application/json", produces = "application/json")
//	public APIResponse updatePriceItemLink(@PathVariable Long id,
//			@Valid @RequestBody PackageSchedulePriceItemLinkRequest request) throws NotFoundException {
//		return packageSchedulePriceItemLinkService.updatePriceItemLink(id, request);
//	}

	@GetMapping(value = "/get-package-schedule-price-item-link/{id}")
	public APIResponse getPackageSchedulePriceItemLink(@PathVariable Long id) throws Exception {
		return packageSchedulePriceItemLinkService.getPackageSchedulePriceItemLink(id);
	}

	@GetMapping(value = "/get-all-package-schedule-price-item-link", produces = "application/json")
	public APIResponse getAllSchedulePriceItemLink(@RequestParam(required = false) Integer serviceRequestId,
			@RequestParam(required = false) Integer serviceRequestLineId,
			@RequestParam(required = false) Integer serviceRequestTypeId,
			@RequestParam(required = false) Integer scheduleId, @RequestParam(required = false) Integer linkStatus) {
		return packageSchedulePriceItemLinkService.getAllSchedulePriceItemLink(serviceRequestId, serviceRequestLineId,
				serviceRequestTypeId, scheduleId, linkStatus);
	}

	@PostMapping(value = "/create-and-update-package-schedule-price-item-link")
	public APIResponse saveAndUpdatePriceItemLink(
			@Valid @RequestBody List<PackageSchedulePriceItemLinkRequest> requestList) {
		return packageSchedulePriceItemLinkService.saveAndUpdatePriceItemLink(requestList);
	}
}
