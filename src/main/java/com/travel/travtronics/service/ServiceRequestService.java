package com.travel.travtronics.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.travtronics.bpf.model.ServiceRequestDataModel;
import com.travel.travtronics.bpf.model.SrEntity;
import com.travel.travtronics.bpf.repository.ServiceRequestDataRepository;
import com.travel.travtronics.bpf.repository.SrRepository;
import com.travel.travtronics.converter.ServiceRequestConverter;
import com.travel.travtronics.request.ServiceRequestData;
import com.travel.travtronics.request.UpdateServiceRequestData;
import com.travel.travtronics.request.dto.ServiceRequestDto;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.CreateServiceResponse;
import com.travel.travtronics.response.SREntityResponse;
import com.travel.travtronics.response.SrInfoResponse;
import com.travel.travtronics.srm.dao.CommonAsyncDao;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

@Service
public class ServiceRequestService {

	@Autowired
	SrRepository serviceRequestRepository;

	@Autowired
	ServiceRequestDataRepository serviceRequestDataRepository;

	@Autowired
	CommonAsyncDao commonAsyncDao;

	private final ObjectMapper objectMapper;

	public ServiceRequestService(ObjectMapper objectMapper) {
		super();
		this.objectMapper = objectMapper;
	}

	public APIResponse create(ServiceRequestData requestData, Integer statusId) {
		CreateServiceResponse responseData = new CreateServiceResponse();
		SrEntity savedData = serviceRequestRepository
				.save(ServiceRequestConverter.convertRequestDataToModelObject(requestData));

		if (savedData.getId() != null && savedData.getId() > 0) {
			ServiceRequestDataModel dataJsonObj = ServiceRequestConverter
					.convertRequestDataToRequestJsonDataModelObject(savedData.getId(), requestData);

			ServiceRequestDataModel dataJson = serviceRequestDataRepository.save(dataJsonObj);

			if (dataJson.getId() != null && dataJson.getId() > 0) {
				responseData = ServiceRequestConverter.covertSrDataModelToResponse(savedData, dataJson);
			}
		}
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Collections.singletonList(responseData));
	}

	public APIResponse list() {
		try {
			List<SrEntity> list = serviceRequestRepository.findAll();
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
		}
	}

	public APIResponse getRequestDataById(Long id) {
		Map<String, Object> response = new HashMap<>();
		Optional<SrEntity> headerData = serviceRequestRepository.findById(id);
		if (headerData.isPresent()) {
			SrInfoResponse srInfo = ServiceRequestConverter.convertSrModelToSrInfoResponse(headerData.get());
			response.put("request", srInfo);

			Optional<ServiceRequestDataModel> dataJsonInfo = serviceRequestDataRepository
					.findBySrId(headerData.get().getId());
			if (dataJsonInfo.isPresent()) {
				response.put("requestData", dataJsonInfo.get());
			} else {
				response.put("requestData", Collections.emptyList());
			}

			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Collections.singletonList(response));
		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), Collections.emptyList());
		}
	}

	public APIResponse update(UpdateServiceRequestData updateData) {
		if (Objects.isNull(updateData.getUpdatedBy()) || Objects.isNull(updateData.getUpdatedChannel()))
			return new APIResponse(HttpStatus.BAD_REQUEST.value(),
					"invalid request..updatedby and updatedChannel must not be null", null);
		CreateServiceResponse responseData = new CreateServiceResponse();

		Optional<SrEntity> opt = serviceRequestRepository.findById(updateData.getId());

		if (opt.isPresent()) {
			Integer srStatus = opt.get().getSrStatusId().intValue();

			System.out.println("checking :-" + srStatus);

			updateData.setCreatedBy(opt.get().getCreatedBy());
			updateData.setCreatedDate(opt.get().getCreatedDate());

			/************* SR DATA AUDIT ****************/
			commonAsyncDao.createSrDataAudit(opt.get());
			SrEntity savedData = serviceRequestRepository
					.save(ServiceRequestConverter.convertUpdateRequestDataToModelObject(updateData, opt.get()));

			if (savedData.getId() != null && savedData.getId() > 0) {

				Optional<ServiceRequestDataModel> dataJsonInfo = serviceRequestDataRepository
						.findBySrId(savedData.getId());
				if (dataJsonInfo.isPresent()) {

					ServiceRequestDataModel dataJsonObj = ServiceRequestConverter
							.convertUpdateRequestDataToRequestJsonDataModelObject(dataJsonInfo.get().getId(),
									savedData.getId(), updateData, dataJsonInfo.get());

					ServiceRequestDataModel dataJson = serviceRequestDataRepository.save(dataJsonObj);

					if (dataJson.getId() != null && dataJson.getId() > 0) {

						responseData = ServiceRequestConverter.covertSrDataModelToResponse(savedData, dataJson);

					}
				}
			}
		}
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Collections.singletonList(responseData));
	}

//	public APIResponsePaging searchNeedMyRequest(ServiceRequestDto searchDto, int pageNo, int pageSize) {
//
//		
//		Page<SrEntity> bookingReportBySuplierReference = serviceRequestRepository
//				.findAll(new Specification<SrEntity>() {
//					
//					private static final long serialVersionUID = 6596536068947061747L;
//					Predicate[] predicates = new Predicate[12];
//
//					@Override
//					public Predicate toPredicate(Root<SrEntity> root, CriteriaQuery<?> query,
//							CriteriaBuilder criteriaBuilder) {
//						// TODO Auto-generated method stub
//						return null;
//					}	
//					
//				}
//		return null;
//	}

	public Page<SrEntity> searchNeedMyRequest(ServiceRequestDto searchDto, Pageable pageable) {
		Long srTypeId = searchDto.getSrTypeId();
		Specification<SrEntity> spec = (root, query, criteriaBuilder) -> {
			Predicate predicate = criteriaBuilder.conjunction();
			if (srTypeId != null) {
				predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("srTypeId"), srTypeId));
			}
			return predicate;
		};
		return serviceRequestRepository.findAll(spec, pageable);
	}

	public APIResponsePaging searchNeedMyRequests(ServiceRequestDto searchDto, Pageable pageable) {
		Long srTypeId = searchDto.getSrTypeId();
		  Specification<SrEntity> spec = (root, query, criteriaBuilder) -> {
		        Predicate predicate = criteriaBuilder.conjunction();
		        if (srTypeId != null && srTypeId > 0) {
		            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("srTypeId"), srTypeId));
		        }
		        return predicate;
		    };
		Page<SrEntity> findAll = serviceRequestRepository.findAll(spec, pageable);

		Page<SREntityResponse> srPage = findAll.map(model -> {
			SREntityResponse response = ServiceRequestConverter.convertResponseToEntity(model);

			Optional<String> srtypeName = serviceRequestRepository.getSrTypeName(response.getSrTypeId());
			Optional<String> statusName = serviceRequestRepository.getStatusName(response.getSrStatusId());
			response.setSrTypeName(srtypeName.orElse(null));
			response.setStatusName(statusName.orElse(null));
			   Optional<ServiceRequestDataModel> requestDataOptional = serviceRequestDataRepository.findBySrId(response.getId());
	            requestDataOptional.ifPresent(requestData -> {
	                try {
	                    // Convert JSON string to Map
					Map<String, Object> leadData = objectMapper.readValue(requestData.getData(), new TypeReference<Map<String, Object>>() {});
	                    response.setLeadData(leadData);
	                } catch (IOException e) {
	                    // Handle JSON parsing exception
	                    e.printStackTrace();
	                }
	            });

	            return response;
	        });
		
		List<SREntityResponse> data = srPage.getContent();
		long totalElements = srPage.getTotalElements();
		int totalPages = srPage.getTotalPages();
		Integer pageNo = pageable.getPageNumber();
		return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), data, new ArrayList<>(), pageNo,
				totalElements, totalPages);
	}
}
