package com.travel.travtronics.bpf.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.travel.travtronics.bpf.model.PriceTypeItems;

public interface PriceTypeItemsRepository extends CrudRepository<PriceTypeItems, Long> {

	List<PriceTypeItems> findAllByStatusTrue();

}
