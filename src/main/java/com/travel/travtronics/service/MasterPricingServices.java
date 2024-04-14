package com.travel.travtronics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.travtronics.bpf.model.PriceTypeItems;
import com.travel.travtronics.bpf.repository.PriceTypeItemsRepository;
import com.travel.travtronics.bpf.repository.PricingTypeRepository;
import com.travel.travtronics.response.APIResponse;

@Service
public class MasterPricingServices {

	@Autowired
	PricingTypeRepository pricingTypeRepository;

	@Autowired
	PriceTypeItemsRepository priceTypeItemsRepository;

	public APIResponse getPricingTypes() {

		return new APIResponse(200, "OK", pricingTypeRepository.findAllByStatusTrue());
	}

	public APIResponse getPricingTypeItems(Long id) throws Exception {
		PriceTypeItems Data = priceTypeItemsRepository.findById(id).orElseThrow(() -> new Exception("Data Not Found"));

		return new APIResponse(200, "OK", List.of(Data));
	}

	public APIResponse getPricingTypeItems() {
		return new APIResponse(200, "OK", priceTypeItemsRepository.findAllByStatusTrue());
	}
}
