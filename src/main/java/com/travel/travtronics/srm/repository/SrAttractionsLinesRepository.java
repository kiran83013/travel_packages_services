package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.srm.model.SrAttractionsLinesModel;

import jakarta.validation.Valid;

public interface SrAttractionsLinesRepository extends JpaRepository<SrAttractionsLinesModel, Long> {

	List<SrAttractionsLinesModel> findByAttractionHeaderIdAndAttractionLineStatus(@Valid Long srAttractionId,
			Integer status);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update SrAttractionsLinesModel attr set attr.attractionStatusId =:lineStatusId where attr.attractionRequestId =:requestId and attr.attractionHeaderId=:requestLineId")
	int updateLineStatus(@Param("requestId") Long requestId, @Param("requestLineId") Long requestLineId,
			@Param("lineStatusId") Integer lineStatusId);
	
	@Query(value = "SELECT * FROM sr_attraction_lines WHERE attraction_request_id = ?1 AND attraction_line_status_id != 42", nativeQuery = true)
	List<SrAttractionsLinesModel> getAllUnFulfilledCount(long srId);

}
