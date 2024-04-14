package com.travel.travtronics.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.bpf.model.SrEntity;
import com.travel.travtronics.bpf.repository.SrRepository;
import com.travel.travtronics.converter.ServiceRequestConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.request.ServiceRequestData;
import com.travel.travtronics.request.UpdateServiceRequestData;
import com.travel.travtronics.request.dto.ServiceRequestDto;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.SREntityResponse;
import com.travel.travtronics.service.ServiceRequestService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/srequest")
public class ServiceRequestController {

	@Autowired
	ServiceRequestService serviceRequestService;
	
	@Autowired
	SrRepository serviceRequestRepository;

	@PostMapping(value = "create", consumes = "application/json", produces = "application/json")
	public APIResponse create(@RequestBody ServiceRequestData model, @RequestParam(required = false) Integer statusId) {
		return serviceRequestService.create(model, statusId);
	}

	@GetMapping(value = "list", produces = "application/json")
	public APIResponse list() {
		return serviceRequestService.list();
	}
	@GetMapping(value = "id", produces = "application/json")
	public APIResponse getRequestDataById(@RequestParam Long id) {
		return serviceRequestService.getRequestDataById(id);
	}

	@PutMapping(value = "update", consumes = "application/json", produces = "application/json")
	public APIResponse update(@RequestBody UpdateServiceRequestData model) {
		return serviceRequestService.update(model);
	}

//	@PostMapping("/need-my-request")
//	public APIResponsePaging searchNeedMyRequest(@RequestBody ServiceRequestDto searchDto,
//			@RequestParam(defaultValue = "0", required = false) int pageNo,
//			@RequestParam(defaultValue = "10", required = false) int pageSize,
//			@RequestParam(defaultValue = "id") String sortBy,
//			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
//		return serviceRequestService.searchNeedMyRequest(searchDto, pageNo, pageSize);
//	}
	
	@PostMapping("/need-my-request")
	public APIResponsePaging searchNeedMyRequest(@RequestBody ServiceRequestDto searchDto,
			@RequestParam(defaultValue = "0", required = false) int pageNo,
			@RequestParam(defaultValue = "10", required = false) int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		
		// Create Sort object based on sortBy and sortType
		Sort sort = sortType.equals(SortType.desc) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		// Create Pageable object for pagination
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		// Call the service method passing searchDto and pageable for pagination
		Page<SrEntity> page = serviceRequestService.searchNeedMyRequest(searchDto, pageable);
		Page<SREntityResponse> srPage = page.map(model ->{
			SREntityResponse response = ServiceRequestConverter.convertResponseToEntity(model);
			Optional<String> srtypeName = serviceRequestRepository.getSrTypeName(response.getSrTypeId());
			if (srtypeName.isPresent())
				response.setSrTypeName(srtypeName.get());
			return response;
		});
		// Extract the data from the page
		List<SREntityResponse> data = srPage.getContent();
		long totalElements = srPage.getTotalElements();
		int totalPages = srPage.getTotalPages();

		return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), data, null, pageNo, totalElements,
				totalPages);
	}
	
	
	
	@PostMapping("/need-my-requests")
	public APIResponsePaging searchNeedMyRequests(@RequestBody ServiceRequestDto searchDto,
			@RequestParam(defaultValue = "0", required = false) int pageNo,
			@RequestParam(defaultValue = "10", required = false) int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		Sort sort = sortType.equals(SortType.desc) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return  serviceRequestService.searchNeedMyRequests(searchDto, pageable);
	}
}
