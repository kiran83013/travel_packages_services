package com.travel.travtronics.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PkgScheduleSubHotelResponse {
	
	private Long id;
	
	private Long optionId;
	
	private Integer roomNameId;
	
	private String roomName;
	
	private Integer roomTypeId;
	
	private String roomType;
	
	private Integer maxAdt;
	
	private Integer maxChd;
	
	private Integer maxInf;
	
	private Integer subStatus;
	
	private Integer addedBy;
	
	private String addedDate;
	
	private String addedDevice;
	
	private String addedIp;
	
	private Integer updatedBy;
	
	private String updatedDate;
	
	private String updatedDevice;
	
	private String updatedIp;
	
	private List<PkgSchedulePriceHotelResponse> supbkgprcoptionprice;

}
