package com.travel.travtronics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.request.PackagePriceRequest;
import com.travel.travtronics.request.PkgHotelSupplierInfoRequest;
import com.travel.travtronics.request.PkgPriceHeaderMappingRequest;
import com.travel.travtronics.request.PkgSubHotelRequest;
import com.travel.travtronics.request.PkgSupplierFlightRequest;
import com.travel.travtronics.request.PkgSupplierHotelRequest;
import com.travel.travtronics.request.UpdatePackagePriceRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.PackagePriceConfigServcie;
import com.travel.travtronics.service.PackagesService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/travel-packages")
public class PackagesController {

	@Autowired
	PackagesService packagesService;
	
	@Autowired
	PackagePriceConfigServcie packagePriceConfigServcie;
	
	@GetMapping(value = "/get-pkg-all-price-options/{scheduleId}")
	public APIResponse getPkgAllPriceOptions(@PathVariable Long scheduleId) {
	
		return packagePriceConfigServcie.getPkgAllPriceOptions(scheduleId); 
	}
	
	@GetMapping(value = "/get-pkg-all-price-options-by-sr-srline/{scheduleId}/{srId}/{srLineId}")
	public APIResponse getPkgAllPriceOptionsBySrSrLine(@PathVariable Long scheduleId, @PathVariable Long srId, @PathVariable Long srLineId) {
	
		return packagePriceConfigServcie.getPkgAllPriceOptionsBySrSrLine(scheduleId, srId, srLineId); 
	}
	
	@PostMapping(value = "/save-hotel-pkg-price")
	@Operation(summary = "Save Hotel Package Price Data", description = "Save hotel price with supplier, hotel and pricing options")
	public APIResponse saveHotelPackagePriceData(@RequestBody List<PkgSupplierHotelRequest> requestData) {
	
		return packagePriceConfigServcie.saveHotelPackagePriceData(requestData);
	}
	
	@GetMapping(value = "/get-hotel-pkg-price-by-id/{id}")
	@Operation(summary = "Get specific hotel pkg price data by headerId", description = "Get specific hotel price with supplier, hotel and pricing options by splrId")
	public APIResponse getHotelPackagePriceDataByHeaderId(@PathVariable Long id) {
	
		return packagePriceConfigServcie.getHotelPackagePriceDataByHeaderId(id);
	}
	
	/************************* Individual Hotel Supplier and hotel pricing line saving and get services *********************/
	
	@PostMapping(value = "/save-pkg-hotel-supplier-data")
	@Operation(summary = "Save Hotel Package supplier Data", description = "Save hotel supplier data")
	public APIResponse savePkgHotelSupplierData(@RequestBody List<PkgHotelSupplierInfoRequest> requestData) {
	
		return packagePriceConfigServcie.savePkgHotelSupplierData(requestData);
	}
	
	@GetMapping(value = "/get-pkg-hotel-supplier-data/{id}")
	@Operation(summary = "Get Hotel Package supplier Data", description = "Get hotel supplier data")
	public APIResponse getPkgHotelSupplierData(@PathVariable Long id) {	
		return packagePriceConfigServcie.getPkgHotelSupplierData(id);
	}
	
	@PostMapping(value = "/save-pkg-hotel-supplier-price-data/{supplierHotelId}")
	@Operation(summary = "Save Hotel Package supplier pricing Data", description = "Save hotel supplier pricing data")
	public APIResponse savePkgHotelSupplierPricingData(@PathVariable Long supplierHotelId, @RequestBody List<PkgSubHotelRequest> requestData) {
	
		return packagePriceConfigServcie.savePkgHotelSupplierPricingData(supplierHotelId, requestData);
	}
	
	@GetMapping(value = "/get-pkg-hotel-supplier-price-lines-data/{id}")
	@Operation(summary = "Get Hotel Package supplier pricing lines Data", description = "Get hotel supplier pricing data by supplierSubHotelId")
	public APIResponse getPkgHotelSupplierSubHotelPricingData(@PathVariable Long id) {
	
		return packagePriceConfigServcie.getPkgHotelSupplierSubHotelPricingData(id);
	}
	
	/************************* Individual Hotel Supplier and hotel pricing line saving and get services *********************/
	
	@PostMapping(value = "/pkg-price-header-mapping")
	@Operation(summary = "Package Price and header mapping", description = "Save Package Price and header mapping")
	public APIResponse PackagePriceHeaderMapping(@RequestBody List<PkgPriceHeaderMappingRequest> requestData) {
	
		return packagePriceConfigServcie.packagePriceHeaderMapping(requestData);
	}
	
	@GetMapping(value = "/get-pkg-price-header-mapping-data/{headerId}")
	@Operation(summary = "Get Package Price and header mapping data", description = "Get hotel Package Price and header mapping data by id")
	public APIResponse getPackagePriceHeaderMappingData(@PathVariable Long headerId) {
	
		return packagePriceConfigServcie.getPackagePriceHeaderMappingData(headerId);
	}
	
	@GetMapping(value = "/get-pkg-price-headers-data/{scheduleId}/{srId}/{srLineId}")
	public APIResponse getPackagePriceHeadersData(@PathVariable Long scheduleId, @PathVariable Long srId, @PathVariable Long srLineId) {
	
		return packagePriceConfigServcie.getPackagePriceHeadersData(scheduleId, srId, srLineId);
	}
	
	@PostMapping(value = "/save-flight-pkg-price")
	public APIResponse saveFlightPackagePriceData(@RequestBody List<PkgSupplierFlightRequest> requestData) {
	
		return packagePriceConfigServcie.saveFlightPackagePriceData(requestData);
	}
	
	@GetMapping(value = "/get-flight-pkg-price-by-id/{id}")
	public APIResponse getFlightPackagePriceDataByHeaderId(@PathVariable Long id) {
	
		return packagePriceConfigServcie.getFlightPackagePriceDataByHeaderId(id);
	}	
	
	
	/**********************Old B2C PKGs services ************************/	
	@GetMapping(value = "/itinerary-list")
	public APIResponse getItineraryList(@RequestParam(value = "itinaryName", defaultValue = "", required = false) String itinaryName) {
		return packagesService.getItineraryList(itinaryName);
	}
	
	@GetMapping(value = "/itinerary-info/{id}")
	public APIResponse getItineraryInfo(@PathVariable Integer id) {
		return packagesService.getItineraryDetailsById(id);
	}
	
	@GetMapping(value = "/items-list")
	public APIResponse getPackagesItemsList(@RequestParam(value = "itemName", defaultValue = "", required = false) String itemName) {
		return packagesService.getPackagesItemsList(itemName);
	}
	
	@PostMapping(value = "/create-package-price")
	public APIResponse createPackagePricing(@Valid @RequestBody PackagePriceRequest requestData) {
		return packagesService.savePackagePricingData(requestData);
	}
	
	@PutMapping(value = "/update-package-price/{headerId}")
	public APIResponse updatePackagePricing(@Valid @RequestBody UpdatePackagePriceRequest requestData, @PathVariable Integer headerId) {
		return packagesService.updatePackagePricingData(requestData, headerId);
	}
	
	@GetMapping(value = "/package-price-list")
	public APIResponse getPackagePricingList() {
		return packagesService.getPackagePricingList();
	}
	
	@GetMapping(value = "/package-price-info/{headerId}")
	public APIResponse getPackagePriceInfo(@PathVariable Integer headerId) {
		return packagesService.getPackagePriceInfo(headerId);
	}
	
	/*
	@RequestMapping(value="/quotes-list/{quoteType}", method = RequestMethod.GET)
	public APIResponsePaging getQuotesLines(@PathVariable String quoteType, 
			@RequestParam(value = "customerId", defaultValue = "0", required = false) Integer customerId,
			@RequestParam(defaultValue = "0") int pageNo, 
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		
		return quotationService.getAllQuotesList(quoteType, customerId, pageNo, pageSize, sortBy, sortType);
		
	}
	*/

}
