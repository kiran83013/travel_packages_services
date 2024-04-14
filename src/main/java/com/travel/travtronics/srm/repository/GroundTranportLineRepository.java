package com.travel.travtronics.srm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.srm.model.GTLine;

public interface GroundTranportLineRepository extends JpaRepository<GTLine, String> {

	GTLine findByRequestIdAndRequestLineId(Integer requestId, Integer requestLineId);

	GTLine findByRequestLineUuid(String uuid);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update GTLine gt set gt.lineStatusId =:lineStatusId where gt.requestId =:requestId and gt.requestLineId=:requestLineId")
	int updateLineStatus(@Param("requestId") Integer requestId, @Param("requestLineId") Integer requestLineId,
			@Param("lineStatusId") Integer lineStatusId);

}
