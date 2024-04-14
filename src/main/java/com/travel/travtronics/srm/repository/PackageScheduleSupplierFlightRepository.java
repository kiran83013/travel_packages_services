package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.srm.model.PackageScheduleSupplierFlightModel;

public interface PackageScheduleSupplierFlightRepository
		extends JpaRepository<PackageScheduleSupplierFlightModel, Long> {

	@Query(value = "SELECT * FROM srm.package_schedule_supplier_flight WHERE service_request_id = ?1 "
			+ "AND service_request_line_id = ?2 AND schedule_id = ?3 ORDER BY id ASC", nativeQuery = true)
	List<PackageScheduleSupplierFlightModel> getSplrPriceOptionsList(Integer srId, Integer srLineId, Long scheduleId);

	@Query(value = "SELECT * FROM srm.package_schedule_supplier_flight WHERE schedule_id = ?1 GROUP BY service_request_id, service_request_line_id", nativeQuery = true)
	List<PackageScheduleSupplierFlightModel> getSplrPriceOptionsListBySchdId(Long scheduleId);

	@Query(value = "SELECT * FROM srm.package_schedule_supplier_flight WHERE schedule_id = ?1 AND service_request_id = ?2 "
			+ "AND service_request_line_id = ?3 GROUP BY service_request_id, service_request_line_id", nativeQuery = true)
	List<PackageScheduleSupplierFlightModel> getSplrPriceOptionsListBySchdIdSrSrLine(Long scheduleId, Long srId,
			Long srLineId);

}
