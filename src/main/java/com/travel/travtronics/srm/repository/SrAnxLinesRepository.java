package com.travel.travtronics.srm.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.srm.model.SrAnxLinesModel;
import com.travel.travtronics.util.QueryConst;

public interface SrAnxLinesRepository extends JpaRepository<SrAnxLinesModel, Long> {

	@Query(value = "SELECT * FROM sr_ancillary_line WHERE ancillary_line_request_id = ?1 AND ancillary_line_id = ?2 AND ancillary_line_status = '1'", nativeQuery = true)
	Optional<SrAnxLinesModel> getSrAnxLineInfoByLineId(Integer srId, Long lineId);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update SrAnxLinesModel anx set anx.ancillaryLineStatusId =:lineStatusId where anx.anxLineRequestId =:requestId and anx.anxLineId=:requestLineId")
	int updateLineStatus(@Param("requestId") Integer requestId, @Param("requestLineId") Long requestLineId,
			@Param("lineStatusId") Integer lineStatusId);

	@Query(value = "SELECT * FROM sr_ancillary_line WHERE ancillary_line_request_id = ?1 AND ancillary_line_wf_status_id != 42", nativeQuery = true)
	List<SrAnxLinesModel> getAllUnFulfilledCount(long srId);


}
