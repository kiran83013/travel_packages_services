	package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travel.travtronics.srm.model.PackagesModel;

@Repository
public interface PackagesRepository extends JpaRepository<PackagesModel,Integer> {
    List<PackagesModel> findByHeaderId(Integer optionId);

	//List<QuotesPricingModel> findByOptionsId(Integer optionsId);

	@Query(value = "SELECT * FROM quotes_pricing WHERE header_id = ?1", nativeQuery = true)
	List<PackagesModel> getPriceListByHeaderId(Integer id);

	
	
}
