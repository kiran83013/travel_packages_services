package com.travel.travtronics.bpf.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.travel.travtronics.bpf.model.PriceType;

public interface PricingTypeRepository extends CrudRepository<PriceType, Long> {

	List<PriceType> findAllByStatusTrue();

}
