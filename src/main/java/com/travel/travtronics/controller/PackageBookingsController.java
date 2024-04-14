package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.pkgbookings.request.AddBookingRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.PackageBookingsService;

@RestController
public class PackageBookingsController {
	
	@Autowired
	PackageBookingsService packageBookingsService;
	
	@PostMapping(value = "/create-pakcage-booking")
	public APIResponse createPackageBooking(@RequestBody AddBookingRequest requestData) {
	
		return packageBookingsService.createPackageBooking(requestData);
	}
	
	@GetMapping(value = "/get-pakcage-booking/{bookingId}")
	public APIResponse getPackageBookingInfo(@PathVariable Long bookingId) {
	
		return packageBookingsService.getPackageBookingInfo(bookingId);
	}

}
