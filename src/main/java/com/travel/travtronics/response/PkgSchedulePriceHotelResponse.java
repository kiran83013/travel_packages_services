package com.travel.travtronics.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PkgSchedulePriceHotelResponse {
	
	private Long id;
	
	private Long subOptionId;
	
	private Integer priceStatus;
	
	private Integer adtCount;
	
	private Integer chdCount;
	
	private Integer infCount;
	
	private Double base;
	
	private Double tax;
	
	private String taxBreakUp;
	
	private Double m1;
	
	private Double d1;
	
	private Double m2;
	
	private Double d2;
	
	private Double outputVat;
	
	private Double inputVat;
	
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
