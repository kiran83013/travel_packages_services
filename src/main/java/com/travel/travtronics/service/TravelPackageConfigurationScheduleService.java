package com.travel.travtronics.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.request.PackageScheduleRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.dto.PackageScheduleDto;
import com.travel.travtronics.srm.dao.PackageScheduleConfigDao;

@Service
public class TravelPackageConfigurationScheduleService {

	@Autowired
	PackageScheduleConfigDao packageScheduleConfigDao;

	public APIResponse createPackageSchedule(PackageScheduleRequest request) {
		PackageScheduleDto pckageSchedule = packageScheduleConfigDao.createPackageSchedule(request);
		return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(),
				Collections.singletonList(pckageSchedule));
	}

	public APIResponse updatePackageSchedule(PackageScheduleRequest request) throws Exception {
		PackageScheduleDto packageSchedule = packageScheduleConfigDao.updatePackageSchedule(request);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Collections.singletonList(packageSchedule));
	}

	public APIResponse findPackageSchedule(Long id) throws Exception {
		PackageScheduleDto packageScheduleDto = packageScheduleConfigDao.packageSchedule(id);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),
				Collections.singletonList(packageScheduleDto));
	}

	public APIResponse getAllPackageSchedule(Long id) throws Exception {
		List<PackageScheduleDto> allPackageSchedule = packageScheduleConfigDao.allPackageSchedule(id);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), allPackageSchedule);
	}

}
