package com.travel.travtronics.srm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.srm.model.PackageScheduleHeaderLineModel;

public interface PackageScheduleHeaderLineRepository extends JpaRepository<PackageScheduleHeaderLineModel, Long> {

	List<PackageScheduleHeaderLineModel> findByHeaderId(Long id);
	
	@Query(value = "SELECT * FROM srm.package_schedule_header_line WHERE header_id = ?1 AND line_status = 1", nativeQuery = true)
	List<PackageScheduleHeaderLineModel> getLinesDataByHeaderId(Long id);
}
