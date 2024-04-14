package com.travel.travtronics.packages.converter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.travel.travtronics.pkgbookings.model.BookingPackageScheduleModel;
import com.travel.travtronics.pkgbookings.model.BookingPackageSchedulePriceModel;
import com.travel.travtronics.pkgbookings.model.BookingPassengersModel;
import com.travel.travtronics.pkgbookings.model.BookingsModel;
import com.travel.travtronics.pkgbookings.request.AddBookingRequest;
import com.travel.travtronics.pkgbookings.request.PkgBookingPaxRequest;
import com.travel.travtronics.pkgbookings.request.PkgSchedulePriceRequest;
import com.travel.travtronics.pkgbookings.request.PkgSchedulesRequest;

public class PkgBookingsConverter {
	
	ZonedDateTime instance = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	String currentDateTime = formatter.format(instance); // 15-02-2022 12:43

	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String currentDate = formatter2.format(instance); // 15-02-2022
	
	public static BookingsModel convertAddBookingRequestToBookingModel(AddBookingRequest request) {
		
		BookingsModel bookingModel = new BookingsModel();
		if(request.getBookingId() != null && request.getBookingId() > 0) {
			bookingModel.setBookingId(request.getBookingId());
	    }
	    bookingModel.setProductId(request.getProductId());
	    bookingModel.setBookingChannel(request.getBookingChannel());
	    bookingModel.setBookingReferenceNumber(request.getBookingReferenceNumber());
	    bookingModel.setBookingStatusId(request.getBookingStatusId());
	    bookingModel.setBookingStatusName(request.getBookingStatusName());
	    bookingModel.setBookingServiceRequestId(request.getBookingServiceRequestId());
	    bookingModel.setBookingServiceRequestLineId(request.getBookingServiceRequestLineId());
	    bookingModel.setQuoteId(request.getQuoteId());
	    bookingModel.setSupplierId(request.getSupplierId());
	    bookingModel.setSupplierReferenceId(request.getSupplierReferenceId());
	    bookingModel.setSupplierReferenceDate(request.getSupplierReferenceDate());
	    bookingModel.setStartDate(request.getStartDate());
	    bookingModel.setEndDate(request.getEndDate());
	    bookingModel.setCustomerId(request.getCustomerId());
	    bookingModel.setCustomerContactId(request.getCustomerContactId());
	    bookingModel.setCustomerContactName(request.getCustomerContactName());
	    bookingModel.setCustomerContactPhone(request.getCustomerContactPhone());
	    bookingModel.setCustomerContactEmail(request.getCustomerContactEmail());
	    bookingModel.setUserId(request.getUserId());
	    bookingModel.setLiabilityOwnerId(request.getLiabilityOwnerId());
	    bookingModel.setBaseCurrency(request.getBaseCurrency());
	    bookingModel.setCurrency(request.getCurrency());
	    bookingModel.setCurrencyConversionRate(request.getCurrencyConversionRate());
	    bookingModel.setBase(request.getBase());
	    bookingModel.setTax(request.getTax());
	    bookingModel.setTaxData(request.getTaxData());
	    bookingModel.setCommissionAmount(request.getCommissionAmount());
	    bookingModel.setCommissionPercentage(request.getCommissionPercentage());
	    bookingModel.setCommissionPercentageToAmount(request.getCommissionPercentageToAmount());
	    bookingModel.setExtraCommissionAmount(request.getExtraCommissionAmount());
	    bookingModel.setExtraCommissionPercentage(request.getExtraCommissionPercentage());
	    bookingModel.setExtraCommissionPercentageToAmount(request.getExtraCommissionPercentageToAmount());
	    bookingModel.setSupplierTotal(request.getSupplierTotal());
	    bookingModel.setM1(request.getM1());
	    bookingModel.setM1TemplateId(request.getM1TemplateId());
	    bookingModel.setM1TemplateData(request.getM1TemplateData());
	    bookingModel.setM2(request.getM2());
	    bookingModel.setM2ReasonData(request.getM2ReasonData());
	    bookingModel.setD1(request.getD1());
	    bookingModel.setD1TemplateId(request.getD1TemplateId());
	    bookingModel.setD1TemplateData(request.getD1TemplateData());
	    bookingModel.setD2(request.getD2());
	    bookingModel.setD2ReasonData(request.getD2ReasonData());
	    bookingModel.setGrandTotal(request.getGrandTotal());
	    bookingModel.setAddedBy(request.getAddedBy());
	    bookingModel.setAddedDate(new PkgBookingsConverter().currentDateTime);
	    bookingModel.setAddedDevice(request.getAddedDevice());
	    bookingModel.setAddedIp(request.getAddedIp());
	    
	    return bookingModel;
	}
	
	public static BookingPackageScheduleModel convertPkgSchedulesRequestToBookingPackageScheduleModel(PkgSchedulesRequest request) {
		
		BookingPackageScheduleModel model = new BookingPackageScheduleModel();
		if(request.getBookingPackageScheduleId() != null && request.getBookingPackageScheduleId() > 0) {
			model.setBookingPackageScheduleId(request.getBookingPackageScheduleId());
	    }
	    model.setBookingPackageId(request.getBookingPackageId());
	    model.setBookingScheduleId(request.getBookingScheduleId());
	    model.setBookingPackageScheduleStatus(request.getBookingPackageScheduleStatus());
	    model.setPackageName(request.getPackageName());
	    model.setScheduleName(request.getScheduleName());
	    model.setScheduleStartDate(request.getScheduleStartDate());
	    model.setScheduleEndDate(request.getScheduleEndDate());
	    model.setScheduleDetails(request.getScheduleDetails());
	    model.setScheduleEditorData(request.getScheduleEditorData());
	    model.setScheduleServiceTypeId(request.getScheduleServiceTypeId());
	    
	    return model;
	}
	
	public static BookingPackageSchedulePriceModel convertPkgSchedulePriceRequestToBookingPackageSchedulePriceModel(PkgSchedulePriceRequest request) {
		
		BookingPackageSchedulePriceModel model = new BookingPackageSchedulePriceModel();
	    
		if(request.getBookingPackageSchedulePriceId() != null && request.getBookingPackageSchedulePriceId() > 0) {
			model.setBookingPackageSchedulePriceId(request.getBookingPackageSchedulePriceId());
	    }
	    model.setBookingPackageId(request.getBookingPackageId());
	    model.setBookingScheduleId(request.getBookingScheduleId());
	    model.setScheduleServiceTypeId(request.getScheduleServiceTypeId());
	    model.setPriceItemHeaderId(request.getPriceItemHeaderId());
	    model.setPriceItemId(request.getPriceItemId());
	    model.setPriceItemName(request.getPriceItemName());
	    model.setPriceItemCode(request.getPriceItemCode());
	    model.setPriceItemDescription(request.getPriceItemDescription());
	    model.setBookingPackageSchedulePriceStatus(request.getBookingPackageSchedulePriceStatus());
	    model.setBaseCurrency(request.getBaseCurrency());
	    model.setCurrency(request.getCurrency());
	    model.setCurrencyConversionRate(request.getCurrencyConversionRate());
	    model.setBase(request.getBase());
	    model.setTax(request.getTax());
	    model.setTaxData(request.getTaxData());
	    model.setCommissionAmount(request.getCommissionAmount());
	    model.setCommissionPercentage(request.getCommissionPercentage());
	    model.setCommissionPercentageToAmount(request.getCommissionPercentageToAmount());
	    model.setExtraCommissionAmount(request.getExtraCommissionAmount());
	    model.setExtraCommissionPercentage(request.getExtraCommissionPercentage());
	    model.setExtraCommissionPercentageToAmount(request.getExtraCommissionPercentageToAmount());
	    model.setSupplierTotal(request.getSupplierTotal());
	    model.setM1(request.getM1());
	    model.setM1TemplateId(request.getM1TemplateId());
	    model.setM1TemplateData(request.getM1TemplateData());
	    model.setM2(request.getM2());
	    model.setM2ReasonData(request.getM2ReasonData());
	    model.setD1(request.getD1());
	    model.setD1TemplateId(request.getD1TemplateId());
	    model.setD1TemplateData(request.getD1TemplateData());
	    model.setD2(request.getD2());
	    model.setD2ReasonData(request.getD2ReasonData());
	    model.setGrandTotal(request.getGrandTotal());
	    
	    return model;
	}
	
	public static BookingPassengersModel convertPkgBookingPaxRequestToBookingPassengersModel(PkgBookingPaxRequest request) {
		
		BookingPassengersModel model = new BookingPassengersModel();
		
		if(request.getPassengerId() != null && request.getPassengerId() > 0) {
			model.setPassengerId(request.getPassengerId());
	    }
	    model.setBookingReferenceNumber(request.getBookingReferenceNumber());
	    model.setCustomerTravelUserId(request.getCustomerTravelUserId());
	    model.setPaxType(request.getPaxType());
	    model.setTitle(request.getTitle());
	    model.setFirstName(request.getFirstName());
	    model.setMiddleName(request.getMiddleName());
	    model.setLastSurName(request.getLastSurName());
	    model.setDob(request.getDob());
	    model.setAge(request.getAge());
	    model.setGender(request.getGender());
	    model.setMobile(request.getMobile());
	    model.setEmail(request.getEmail());
	    model.setPassengerLead(request.getPassengerLead());
	    model.setPassengerStatus(request.getPassengerStatus());
	    
	    return model;
	}





}
