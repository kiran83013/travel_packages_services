package com.travel.travtronics.srm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.srm.model.CustomerContactInfoModel;

public interface CustomerContactInfoRepository extends JpaRepository<CustomerContactInfoModel, Integer>{

	@Query(value = "SELECT * FROM customer_contact WHERE CustomerId = ?1 AND ID = ?2", nativeQuery = true)
	Optional<CustomerContactInfoModel> getCustomerContactInfoByCustIdContactId(String customerId, String contactId);

	Optional<CustomerContactInfoModel> findByPrimaryEmailOrPrimaryPhoneNumber(String mail, String phoneNo);

	Optional<CustomerContactInfoModel> findByPrimaryEmail(String mail);
	
	Optional<CustomerContactInfoModel> findByPrimaryPhoneNumber(String phoneNo);

}
