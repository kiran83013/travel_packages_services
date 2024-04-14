package com.travel.travtronics.srm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.srm.model.SrHotelLines;

public interface SrHotelLinesRepository extends JpaRepository<SrHotelLines, Integer> {

	List<SrHotelLines> findAllByLineSrId(Integer srId);

	

	Optional<SrHotelLines> findByIdAndLineSrId(Integer id, Integer sr);

	Optional<SrHotelLines> findByIdAndLineSrIdAndLineUuid(Integer id, Integer lineSrId, String lineUuid);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update SrHotelLines hotel set hotel.lineStatusId =:lineStatusId where hotel.lineSrId =:requestId and hotel.id=:requestLineId")
	int updateLineStatus(@Param("requestId") Integer requestId, @Param("requestLineId") Integer requestLineId,
			@Param("lineStatusId") Integer lineStatusId);
	
	@Query(value = "SELECT * FROM sr_hotel_lines WHERE line_sr_id = ?1 AND line_status_id != 42", nativeQuery = true)
	List<SrHotelLines> getAllUnFulfilledCount(long srId);

}
