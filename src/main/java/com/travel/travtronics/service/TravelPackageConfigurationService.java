package com.travel.travtronics.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.request.MedInfoRequest;
import com.travel.travtronics.request.dto.MedInfoSearchParametersDTO;
import com.travel.travtronics.request.dto.PackageImageConfigDto;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.MedInfoResponse;
import com.travel.travtronics.srm.dao.PackageConfigDao;
import com.travel.travtronics.srm.model.MedInfo;
import com.travel.travtronics.srm.model.PackageConfigurationImages;

@Service
public class TravelPackageConfigurationService {

	@Autowired
	PackageConfigDao packageConfigDao;

	public APIResponse medInfo(MedInfoRequest request) throws Exception {

		MedInfo packageConfig = packageConfigDao.packageConfig(request);

		return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(),
				Collections.singletonList(packageConfig));
	}

	public APIResponse updateMedInfo(Long id, MedInfoRequest request) throws Exception {
		MedInfo medInfo = packageConfigDao.medInfo(id, request);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Collections.singletonList(medInfo));
	}

	public APIResponse findById(Long id) throws Exception {
		MedInfoResponse medInfo = packageConfigDao.medInfo(id);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), Collections.singletonList(medInfo));
	}

	public APIResponse packageImages(List<PackageImageConfigDto> request) {

		List<PackageConfigurationImages> packageImages = packageConfigDao.packageImages(request);
		return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), packageImages);
	}

	public APIResponse getPackageImages(Long packageId) {
		List<PackageImageConfigDto> packageImages = packageConfigDao.packageImages(packageId);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), packageImages);
	}

	public APIResponse searchPackageData(MedInfoSearchParametersDTO searchParameters) {
		List<MedInfoResponse> packageData = packageConfigDao.packageData(searchParameters);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), packageData);
	}

}
