package com.travel.travtronics.srm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.srm.model.SrHospitalLines;

public interface SrHospitalLinesRepository extends JpaRepository<SrHospitalLines, Integer> {

	List<SrHospitalLines> findAllByLineSrId(Integer srId);


	Optional<SrHospitalLines> findByIdAndLineSrId(Integer id, Integer sr);

	Optional<SrHospitalLines> findByIdAndLineSrIdAndLineUuid(Integer id, Integer lineSrId, String lineUuid);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update SrHospitalLines hospital set hospital.lineStatusId =:lineStatusId where hospital.lineSrId =:requestId and hospital.id=:requestLineId")
	int updateLineStatus(@Param("requestId") Long requestId, @Param("requestLineId") Long requestLineId, @Param("lineStatusId") Integer lineStatusId);
	
	@Query(value = "SELECT * FROM sr_hospital_lines WHERE line_sr_id = ?1 AND line_status_id != 42", nativeQuery = true)
	List<SrHospitalLines> getAllUnFulfilledCount(long srId);

}
