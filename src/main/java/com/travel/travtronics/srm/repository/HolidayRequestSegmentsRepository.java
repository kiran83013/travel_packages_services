package com.travel.travtronics.srm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.srm.model.HolidayRequestSegments;

public interface HolidayRequestSegmentsRepository extends JpaRepository<HolidayRequestSegments, Long> {

	Optional<HolidayRequestSegments> findByRequestIdAndRequestLineId(Long requestId, Long requestLineId);

	List<HolidayRequestSegments> findByRequestId(Long requestId);

}
