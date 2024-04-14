package com.travel.travtronics.srm.model;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.util.QueryConst;

public interface QuotesHeaderRepository extends JpaRepository<QuotesHeaderModel, Long> {
	
	@Query(value = QueryConst.GET_CONTACT_INFO_BY_SRID_QRY, nativeQuery = true)
	Map<String, Object> getContactInfoBySrId(Integer srId);

	Optional<QuotesHeaderModel> findById(Integer quoteId);

}
