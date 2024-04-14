package com.travel.travtronics.converter;

import com.travel.travtronics.request.PackageScheduleDatesRequest;
import com.travel.travtronics.srm.model.TravelPackageScheduleDates;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PackageScheduleDatesConverter {

	public static List<TravelPackageScheduleDates> convertPackageScheduleDatesRequest(
			List<PackageScheduleDatesRequest> requests, Long scheduleId) {

		return requests.stream().map(model -> {

			TravelPackageScheduleDates travelPackageScheduleDatesModel = new TravelPackageScheduleDates();
			if (Objects.nonNull(model.getId()) && model.getId() != 0) {
				travelPackageScheduleDatesModel.setId(model.getId());
			}
			travelPackageScheduleDatesModel.setScheduleId(scheduleId);
			travelPackageScheduleDatesModel.setStartDate(model.getStartDate());
			travelPackageScheduleDatesModel.setEndDate(model.getEndDate());
			travelPackageScheduleDatesModel.setCreatedBy(model.getCreatedBy());
			travelPackageScheduleDatesModel.setUpdatedBy(model.getUpdatedBy());
			travelPackageScheduleDatesModel.setRecordStatus(model.getRecordStatus());
		//	travelPackageScheduleDatesModel.setHtmlEditor(model.getHtmlEditor());
			return travelPackageScheduleDatesModel;
		}).collect(Collectors.toList());
	}

}
