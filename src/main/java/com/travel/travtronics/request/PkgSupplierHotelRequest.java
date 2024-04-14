package com.travel.travtronics.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PkgSupplierHotelRequest {
	
	private Long id;
	
	private Integer scheduleId;
	
	private Integer serviceRequestId;
	
	private Integer serviceRequestLineId;
	
	private Integer checkInDay;
	
	private Integer checkOutDay;
	
	private Integer daysCount;
	
	private Integer nightCount;
	
	private Integer supplierId;
	
	private String hotelCode;
	
	private String hotelName;
	
	private String hotelAddress;
	
	private Integer supplierHotelStatus;
	
	private Integer addedBy;
	
	private String addedDevice;
	
	private String addedIp;
	
	private List<PkgSubHotelRequest> supbkgprcoption;
	

}
