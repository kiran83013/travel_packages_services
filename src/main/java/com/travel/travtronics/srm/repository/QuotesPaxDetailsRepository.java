package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.srm.model.QuotesPaxDetailsModel;

public interface QuotesPaxDetailsRepository extends JpaRepository<QuotesPaxDetailsModel, Integer> {

	@Query(value="SELECT * FROM quotes_pax_details WHERE header_id = ?1 AND record_status = 1", nativeQuery = true)
	List<QuotesPaxDetailsModel> findByHeaderId(Integer id);
	
	@Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="UPDATE quotes_pax_details SET record_status = 0 WHERE header_id = ?1", nativeQuery = true)
    Integer revokeQuotedPaxData(Integer headerId);
	

}
