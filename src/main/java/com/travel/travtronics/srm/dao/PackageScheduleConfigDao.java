package com.travel.travtronics.srm.dao;

import java.util.List;

import com.travel.travtronics.request.PackageScheduleRequest;
import com.travel.travtronics.response.dto.PackageScheduleDto;

public interface PackageScheduleConfigDao {

	PackageScheduleDto createPackageSchedule(PackageScheduleRequest request);

	PackageScheduleDto updatePackageSchedule(PackageScheduleRequest request) throws Exception;

	PackageScheduleDto packageSchedule(Long id) throws Exception;

	List<PackageScheduleDto> allPackageSchedule(Long id) throws Exception;

}
