package com.travel.travtronics.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PkgScheduleSupplierHotelResponse {
	
	private Long id;
	
	private Integer scheduleId;
	
	private Integer serviceRequestId;
	
	private Integer serviceRequestLineId;
	
	private Integer checkInDay;
	
	private Integer checkOutDay;
	
	private Integer daysCount;
	
	private Integer nightCount;
	
	private Integer defaultOption;
	
	private Integer supplierId;
	
	private String hotelCode;
	
	private String hotelName;
	
	private String hotelAddress;
	
	private Integer supplierHotelStatus;
	
	private Integer addedBy;
	
	private String addedDate;
	
	private String addedDevice;
	
	private String addedIp;
	
	private Integer updatedBy;
	
	private String updatedDate;
	
	private String updatedDevice;
	
	private String updatedIp;
	
	private List<PkgScheduleSubHotelResponse> supbkgprcoption;

}
