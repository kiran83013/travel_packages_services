package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.srm.model.PackageScheduleSegmentFlightModel;

public interface PackageScheduleSegmentFlightRepository extends JpaRepository<PackageScheduleSegmentFlightModel, Long> {

	List<PackageScheduleSegmentFlightModel> findBySupplierFlightId(Long id);

}
