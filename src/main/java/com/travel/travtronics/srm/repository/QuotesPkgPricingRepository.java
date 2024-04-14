package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.srm.model.QuotesPkgPricing;

public interface QuotesPkgPricingRepository extends JpaRepository<QuotesPkgPricing, Integer> {

	List<QuotesPkgPricing> findByQuoteHeaderId(Integer quoteHeaderId);

	List<QuotesPkgPricing> findByQuoteHeaderId(Long quoteId);

}
