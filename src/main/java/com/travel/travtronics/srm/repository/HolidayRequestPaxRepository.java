package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.srm.model.HolidayRequestPax;

public interface HolidayRequestPaxRepository extends JpaRepository<HolidayRequestPax, Long> {

	List<HolidayRequestPax> findByRequestIdAndRequestLineIdAndPaxIsDeletedIsNull(Long requestId, Long requestLineId);

	List<HolidayRequestPax> findByRequestId(Long requestId);

	List<HolidayRequestPax> findByRequestIdAndPaxIsDeletedIsNull(Long requestId);

}
