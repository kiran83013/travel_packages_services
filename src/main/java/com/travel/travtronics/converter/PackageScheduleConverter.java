package com.travel.travtronics.converter;

import com.travel.travtronics.request.PackageScheduleRequest;
import com.travel.travtronics.srm.model.TravelPackageSchedule;

public class PackageScheduleConverter {
	public static TravelPackageSchedule convertPackageScheduleRequestToPackageScheduleModel(
			PackageScheduleRequest request) {
		TravelPackageSchedule model = new TravelPackageSchedule();
		model.setId(request.getId());
		model.setPackageConfigurationId(request.getPackageConfigurationId());
		model.setScheduleName(request.getScheduleName());
		model.setStartDate(request.getStartDate());
		model.setCountMin(request.getCountMin());
		model.setCountMax(request.getCountMax());
		model.setDescription(request.getDescription());
		model.setStartCity(request.getStartCity());
		model.setEndCity(request.getEndCity());
		model.setStatus(request.getStatus());
		model.setTeam(request.getTeam());
		model.setOwner(request.getOwner());
		model.setTimeZone(request.getTimeZone());
		model.setBookingStartDate(request.getBookingStartDate());
		model.setBookingEndDate(request.getBookingEndDate());
		model.setMarketingStartDate(request.getMarketingStartDate());
		model.setMarketingEndDate(request.getMarketingEndDate());
		model.setMarketingBudgetMin(request.getMarketingBudgetMin());
		model.setMarketingBudgetMax(request.getMarketingBudgetMax());
		model.setSalesStartDate(request.getSalesStartDate());
		model.setCampaignCode(request.getCampaignCode());
		model.setCouponTemplate(request.getCouponTemplate());
		model.setMetaSlug(request.getMetaSlug());
		model.setMetaTitle(request.getMetaTitle());
		model.setMetaDescription(request.getMetaDescription());
		model.setMetaKeywords(request.getMetaKeywords());
		model.setCreatedBy(request.getCreatedBy());
		model.setUpdatedBy(request.getUpdatedBy());
		model.setRecordStatus(request.getRecordStatus());
		model.setPackageScheduleTextEditor(request.getPackageScheduleTextEditor());
		model.setSrTypeLink(request.getSrTypeLink());
		model.setAdvanceAmountMax(request.getAdvanceAmountMax());
		model.setAdvanceAmountMin(request.getAdvanceAmountMin());
		model.setCommissionAmount(request.getCommissionAmount());
		model.setCommissionPercentage(request.getCommissionPercentage());
		model.setPriceWithFlights(request.getPriceWithFlights());
		model.setPriceWithOutFlights(request.getPriceWithOutFlights());
		return model;
	}
}
