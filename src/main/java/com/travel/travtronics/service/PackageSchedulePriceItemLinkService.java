package com.travel.travtronics.service;

import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.converter.PackageSchedulePriceItemLinkConverter;
import com.travel.travtronics.request.PackageSchedulePriceItemLinkRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.srm.dao.impl.SearchPackageSchedulePriceItemLinkEntity;
import com.travel.travtronics.srm.model.PackageSchedulePriceItemLink;
import com.travel.travtronics.srm.repository.PackageSchedulePriceItemLinkRepository;

import jakarta.validation.Valid;

@Service
public class PackageSchedulePriceItemLinkService {

	@Autowired
	PackageSchedulePriceItemLinkRepository pckageSchedulePriceItemLinkRepository;

//	public APIResponse save(@Valid PackageSchedulePriceItemLinkRequest requestList) {
//		PackageSchedulePriceItemLink masterActivity = PackageSchedulePriceItemLinkConverter
//				.convertSchedulePriceItemRequestTOModel(requestList);
//		PackageSchedulePriceItemLink savedMasteractivity = pckageSchedulePriceItemLinkRepository.save(masterActivity);
//		return new APIResponse(HttpStatus.OK.value(), "Successfully created",
//				Collections.singletonList(savedMasteractivity));
//	}

//	public APIResponse updatePriceItemLink(Long id, @Valid PackageSchedulePriceItemLinkRequest request) {
//		Optional<PackageSchedulePriceItemLink> existingPriceItemLink = pckageSchedulePriceItemLinkRepository
//				.findById(id);
//
//		if (existingPriceItemLink.isPresent()) {
//			PackageSchedulePriceItemLink updatedMasterActivity = pckageSchedulePriceItemLinkRepository
//					.save(PackageSchedulePriceItemLinkConverter.convertPriceItemLinkUpdateRequestToModel(id, request));
//
//			return new APIResponse(HttpStatus.OK.value(), "Successfully updated",
//					Collections.singletonList(updatedMasterActivity));
//		} else {
//			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system with the provided id",
//					Collections.emptyList());
//		}
//	}

	public APIResponse getPackageSchedulePriceItemLink(Long id) throws Exception {
		PackageSchedulePriceItemLink model = pckageSchedulePriceItemLinkRepository.findById(id)
				.orElseThrow(() -> new Exception("PackageSchedulePriceItemLink not found"));
		return new APIResponse(OK.value(), OK.name(),
				Collections.singletonList(PackageSchedulePriceItemLinkConverter.convertModelToResponse(model)));
	}

	public APIResponse getAllSchedulePriceItemLink(Integer serviceRequestId, Integer serviceRequestLineId,
			Integer serviceRequestTypeId, Integer scheduleId, Integer linkStatus) {
		if (serviceRequestId == null && serviceRequestLineId == null && serviceRequestTypeId == null
				&& scheduleId == null && linkStatus == null) {
			return new APIResponse(HttpStatus.OK.value(), "unable-to-search-provide-atleast-one-parameter",
					new ArrayList<>());
		}
		List<PackageSchedulePriceItemLink> searchedData = pckageSchedulePriceItemLinkRepository
				.findAll(SearchPackageSchedulePriceItemLinkEntity.findByPricItemLinkSpecifications(serviceRequestId,
						serviceRequestLineId, serviceRequestTypeId, scheduleId, linkStatus));
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), searchedData);
	}

	public APIResponse saveAndUpdatePriceItemLink(@Valid List<PackageSchedulePriceItemLinkRequest> requestList) {

		if (requestList != null && requestList.size() > 0) {

			List<PackageSchedulePriceItemLink> response = new ArrayList<PackageSchedulePriceItemLink>();

			for (PackageSchedulePriceItemLinkRequest item : requestList) {

				Optional<PackageSchedulePriceItemLink> priceItemData = null;
				if (item.getId() != null && item.getId() > 0) {
					Optional<PackageSchedulePriceItemLink> priceItemLink = pckageSchedulePriceItemLinkRepository
							.findById(item.getId());
					if (priceItemLink.isPresent() == false) {
						return new APIResponse(HttpStatus.NOT_FOUND.value(),
								"No data found in the system with provided id", Collections.emptyList());
					}
					priceItemData = pckageSchedulePriceItemLinkRepository.findById(item.getId());
				}
				PackageSchedulePriceItemLink priceItemLink = pckageSchedulePriceItemLinkRepository
						.save(PackageSchedulePriceItemLinkConverter.convertSchedulePriceItemRequestTOModel(item.getId(),
								item, priceItemData));
				if (priceItemLink != null && priceItemLink.getId() > 0) {
					response.add(priceItemLink);
				}
			}
			return new APIResponse(HttpStatus.CREATED.value(), "Package Schedule Price Item Link Saved", response);
		} else {
			return new APIResponse(HttpStatus.FAILED_DEPENDENCY.value(), "Invalid request data",
					Collections.emptyList());
		}

	}
}
