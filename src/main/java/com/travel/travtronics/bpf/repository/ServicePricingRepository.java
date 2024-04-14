package com.travel.travtronics.bpf.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.bpf.model.ServicePricing;
import com.travel.travtronics.util.Status;

public interface ServicePricingRepository extends JpaRepository<ServicePricing, Long> {

	List<ServicePricing> findByHeaderIdAndStatusAndServicePriceType(Long headerId, Status active, Integer priceType);

	List<ServicePricing> findByHeaderIdAndServicePriceTypeAndStatus(Long headerId, Integer pricingType, Status active);

	List<ServicePricing> findByHeaderIdAndStatus(Long srHeaderId, Status active);

	@Query(value = "SELECT tax.name FROM master_tax_category tax WHERE tax.ID=?1", nativeQuery = true)
	Optional<String> getTaxCategoryName(Integer id);

}
