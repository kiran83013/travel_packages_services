package com.travel.travtronics.request;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddPkgQuoteRequest {
	
	private String contactName;
	
	private String contactMailId;
	
	private String contactMobileNo;
	
	private Long priceHeaderId;
	
	private Long leadId;
	
	private Long scheduleId;
	
	private Integer adultCount;
	
	private Integer childrenCount;
	
	private Integer infantCount;
	
	private List<String> childrenAge;
	
	private Integer scheduledDatesId;
	
	private  String requiredDate;
	
	private Map<String, Object> priceSelected;
	
	private Integer addedBy;
	
	private String deviceInfo;
	
	private String ipAddress;

}
