package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.srm.model.PackageScheduleHeaderModel;

public interface PackageScheduleHeaderRepository extends JpaRepository<PackageScheduleHeaderModel, Long> {

	@Query(value = "SELECT * FROM srm.package_schedule_header WHERE schedule_id = ?1 AND service_request_id = ?2 "
			+ "AND service_request_line_id = ?3 AND header_status = 1", nativeQuery = true)
	List<PackageScheduleHeaderModel> getHeaderDataBySchIdSrSrlineId(Long scheduleId, Long srId, Long srLineId);

}
