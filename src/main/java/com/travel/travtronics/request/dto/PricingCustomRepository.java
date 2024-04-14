package com.travel.travtronics.request.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.travel.travtronics.bpf.model.Item;
import com.travel.travtronics.bpf.model.PricingHeader;
import com.travel.travtronics.bpf.model.TaxSlabHeader;
import com.travel.travtronics.enums.SortType;

public interface PricingCustomRepository {



	Page<PricingHeader> fetchPricingPagination(String name, Long organization, Pageable pageable, String sortBy,
			SortType sortType);

	Page<Item> fetchItemPagination(String name, Long organizationId, Pageable pageable, String sortBy,
			SortType sortType);

	Page<TaxSlabHeader> fetchTaxPagination(Long taxCategory, Long organizationId, Pageable pageable, String sortBy,
		SortType sortType);


}
