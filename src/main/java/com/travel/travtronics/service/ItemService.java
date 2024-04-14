package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.bpf.model.Item;
import com.travel.travtronics.bpf.repository.ItemRepository;
import com.travel.travtronics.converter.ItemConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.request.ItemRequest;
import com.travel.travtronics.request.dto.PricingCustomRepository;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.ItemResponse;
import com.travel.travtronics.response.MessageStatusResponse;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	PricingCustomRepository pricingCustomRepository;

	public MessageStatusResponse createComponet(ItemRequest model) {
		Item convertJsonToModel = ItemConverter.convertJsonToModel(model);
		itemRepository.save(convertJsonToModel);
		return new MessageStatusResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name());
	}

	public MessageStatusResponse modifyComponent(ItemRequest model, Long id) {
		Optional<Item> findById = itemRepository.findById(id);
		if (!findById.isPresent())
			return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
		else {
			Item updateJsonToModel = ItemConverter.updateJsonToModel(model, id);
			itemRepository.save(updateJsonToModel);
			return new MessageStatusResponse(HttpStatus.OK.value(), HttpStatus.OK.name());
		}

	}

	public APIResponse getComponent(Long id) {
		Optional<Item> findById = itemRepository.findById(id);
		if (!findById.isPresent())
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), Collections.emptyList());
		else {
			ItemResponse convertModeltoResponse = ItemConverter.convertModeltoResponse(findById.get());
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
					Collections.singletonList(convertModeltoResponse));
		}
	}

	public APIResponse getComponents(Long organizationId) {
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
				ItemConverter.convertListModelToListResponse(itemRepository.findAllByOrganizationId(organizationId)));
	}

	public APIResponsePaging getPagenationByOrganization(Long organizationId, String name, int pageNo, int pageSize,
			String sortBy, SortType sortType) {
//		Pageable pageable = PageRequest.of(pageNo, pageSize,
//				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
//		if(organizationId != null) {
//			Page<Item> item = itemRepository.findByOrganizationId(organizationId, pageable);
//			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(), new ArrayList<>(), item.getNumber(),item.getTotalElements(),item.getTotalPages());
//		}else{
//			Page<Item> itemPage = itemRepository.findAll(pageable);
//			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), itemPage.getContent(),
//					new ArrayList<>(), itemPage.getNumber(), itemPage.getTotalElements(),
//					itemPage.getTotalPages());
//		}

		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Item> serviceMenuTypeData = pricingCustomRepository.fetchItemPagination(name, organizationId, pageable, sortBy,
				sortType);
		return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), serviceMenuTypeData.getContent(),
				new ArrayList<>(), serviceMenuTypeData.getNumber(), serviceMenuTypeData.getTotalElements(),
				serviceMenuTypeData.getTotalPages());
	}
}
