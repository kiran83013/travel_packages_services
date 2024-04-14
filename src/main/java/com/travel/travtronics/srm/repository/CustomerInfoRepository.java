package com.travel.travtronics.srm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.travel.travtronics.srm.model.CustomerInfoModel;

public interface CustomerInfoRepository extends CrudRepository<CustomerInfoModel, Long> {

	CustomerInfoModel findByCustomerId(Long customerId);

	@Query(value = "SELECT name AS rating FROM masters_srm.master_rating WHERE id =?1", nativeQuery = true)
	String fetchRatingName(Long id);
	
	@Query(value = "SELECT CONCAT(contact.FirstName,\" \",contact.LastName) FROM customer_contact contact WHERE contact.ID=?1", nativeQuery = true)
	String fetchContactName(Long id);

}
