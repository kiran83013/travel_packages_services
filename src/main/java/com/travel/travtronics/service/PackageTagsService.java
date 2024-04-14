package com.travel.travtronics.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travel.travtronics.request.PackageTagsRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.srm.dao.PackageConfigDao;
import com.travel.travtronics.srm.model.PackageTags;

@Service
public class PackageTagsService {

	@Autowired
	PackageConfigDao packageConfigDao;

	public APIResponse savePackageTags(PackageTagsRequest request) {
		PackageTags packageTags = packageConfigDao.packageTags(request);
		return new APIResponse(HttpStatus.CREATED.value(), "PackageTags saved successfully", List.of(packageTags));
	}

	public APIResponse updatePackageTags(Long id, PackageTagsRequest request) {
		PackageTags packageTags = packageConfigDao.packageTags(id, request);
		return new APIResponse(HttpStatus.OK.value(), "PackageTags updated successfully", List.of(packageTags));
	}

	public APIResponse findById(Long id) {
		PackageTags packageTags = packageConfigDao.packageTags(id);
		return new APIResponse(HttpStatus.OK.value(), "PackageTags found", List.of(packageTags));
	}

	public APIResponse findAll() {
		List<PackageTags> packageTags = packageConfigDao.packageTags();
		return new APIResponse(HttpStatus.OK.value(), "PackageTags found", packageTags);
	}

	public APIResponse saveMultipulePackageTags(List<PackageTagsRequest> request) {
		List<PackageTags> packageTags = packageConfigDao.packageTags(request);
		return new APIResponse(HttpStatus.CREATED.value(), "PackageTags saved successfully", packageTags);
	}

	public ResponseEntity<?> getSearchData(String activityName, String location, String city, String country) {
		List<Map<String, String>> activitySearchData = packageConfigDao.activitySearchData(activityName, location, city,
				country);
		return new ResponseEntity<>(activitySearchData, HttpStatus.OK);
	}

}
