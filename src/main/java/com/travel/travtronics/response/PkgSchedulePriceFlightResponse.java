package com.travel.travtronics.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PkgSchedulePriceFlightResponse {
	
	private Long id;
	
	private Long supplierFlightId;
	
	private Integer priceStatus;
	
	private String paxType;
	
	private Double base;
	
	private Double tax;
	
	private String taxBreakUp;
	
	private Double m1;
	
	private Double d1;
	
	private Double m2;
	
	private Double d2;
	
	private Double total;
	
	private String currencyCode;
	
	private Integer addedBy;
	
	private String addedDate;
	
	private String addedDevice;
	
	private String addedIp;
	
	private Integer updatedBy;
	
	private String updatedDate;
	
	private String updatedDevice;
	
	private String updatedIp;

}
