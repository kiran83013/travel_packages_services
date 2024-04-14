package com.travel.travtronics.converter;

import java.util.ArrayList;
import java.util.List;

import com.travel.travtronics.request.PackageTagsRequest;
import com.travel.travtronics.srm.model.PackageTags;

public class PackageTagsConverter {

	public static PackageTags convertRequestToEntity(PackageTagsRequest request) {
		PackageTags packageTags = new PackageTags();
		// Map fields from MedInfoRequest to MedInfo entity
		packageTags.setName(request.getName());
		packageTags.setCode(request.getCode());
		packageTags.setDescription(request.getDescription());
		packageTags.setCategory(request.getCategory());
		packageTags.setSubCategory(request.getSubCategory());
		packageTags.setType(request.getType());
		packageTags.setStatus(request.getStatus());
		packageTags.setCreatedBy(request.getCreatedBy());
		packageTags.setUpdatedBy(request.getUpdatedBy());
		return packageTags;
	}

	public static void updateEntityFromRequest(PackageTags existingPackageTags, PackageTagsRequest request) {
		existingPackageTags.setName(request.getName());
		existingPackageTags.setCode(request.getCode());
		existingPackageTags.setDescription(request.getDescription());
		existingPackageTags.setCategory(request.getCategory());
		existingPackageTags.setSubCategory(request.getSubCategory());
		existingPackageTags.setType(request.getType());
		existingPackageTags.setStatus(request.getStatus());
		existingPackageTags.setCreatedBy(request.getCreatedBy());
		existingPackageTags.setUpdatedBy(request.getUpdatedBy());
	}

	public static List<PackageTags> convertRequestToMultipuleEntity(List<PackageTagsRequest> requestList) {
		List<PackageTags> packageTagsList = new ArrayList<>();

		// Iterate through the list of PackageTagsRequest objects
		for (PackageTagsRequest request : requestList) {
			PackageTags packageTags = new PackageTags();

			// Map fields from PackageTagsRequest to PackageTags entity
			packageTags.setName(request.getName());
			packageTags.setCode(request.getCode());
			packageTags.setDescription(request.getDescription());
			packageTags.setCategory(request.getCategory());
			packageTags.setSubCategory(request.getSubCategory());
			packageTags.setType(request.getType());
			packageTags.setStatus(request.getStatus());
			packageTags.setCreatedBy(request.getCreatedBy());
			packageTags.setUpdatedBy(request.getUpdatedBy());

			// Add the created PackageTags object to the list
			packageTagsList.add(packageTags);
		}

		return packageTagsList;
	}

}
