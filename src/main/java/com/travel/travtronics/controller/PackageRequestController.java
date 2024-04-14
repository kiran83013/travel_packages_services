package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.request.HolidayRequest;
import com.travel.travtronics.service.PackageRequestService;

import jakarta.validation.Valid;

@RestController
public class PackageRequestController {

	@Autowired
	PackageRequestService holidayRequestService;

	@PostMapping(value = "/create-package-request")
	public ResponseEntity<?> createPackageRequest(@RequestBody @Valid HolidayRequest model) {
		return holidayRequestService.createPackageRequest(model);
	}

	@GetMapping(value = "/get-package-request")
	public ResponseEntity<?> getPackageRequest(@RequestParam Long requestId) throws Exception {
		return holidayRequestService.getPackageRequest(requestId);
	}

	/*
	 * before package confirmation
	 */
	@GetMapping(value = "/package-itenary-info")
	public ResponseEntity<?> getPackageItenaryInfo(@RequestParam Long requestId) throws Exception {
		return holidayRequestService.getPackageItenaryInfo(requestId);
	}

	@PostMapping(value = "/save-package-request-data")
	public ResponseEntity<?> savePackageRequestData(@RequestParam Long srId, @RequestParam Long loggedInUserId,
			@RequestHeader(value = "Authorization") String authorizationHeader) throws Exception {
		return holidayRequestService.savePackageRequestData(srId, loggedInUserId, authorizationHeader);
	}

}
