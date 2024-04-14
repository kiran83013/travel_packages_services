package com.travel.travtronics.srm.dao;

import java.util.List;
import java.util.Map;

import com.travel.travtronics.request.MedInfoRequest;
import com.travel.travtronics.request.PackageTagsRequest;
import com.travel.travtronics.request.dto.MedInfoSearchParametersDTO;
import com.travel.travtronics.request.dto.PackageImageConfigDto;
import com.travel.travtronics.response.MedInfoResponse;
import com.travel.travtronics.srm.model.MedInfo;
import com.travel.travtronics.srm.model.PackageConfigurationImages;
import com.travel.travtronics.srm.model.PackageTags;

public interface PackageConfigDao {

	MedInfo packageConfig(MedInfoRequest request) throws Exception;

	MedInfo medInfo(Long id, MedInfoRequest request) throws Exception;

	MedInfoResponse medInfo(Long id) throws Exception;

	List<PackageConfigurationImages> packageImages(List<PackageImageConfigDto> request);

	List<PackageImageConfigDto> packageImages(Long id);

	List<MedInfoResponse> packageData(MedInfoSearchParametersDTO searchParameters);

	PackageTags packageTags(PackageTagsRequest request);

	PackageTags packageTags(Long id, PackageTagsRequest request);

	PackageTags packageTags(Long id);

	List<PackageTags> packageTags();

	List<PackageTags> packageTags(List<PackageTagsRequest> request);

	List<Map<String, String>> activitySearchData(String activityName, String location, String city, String country);

}
