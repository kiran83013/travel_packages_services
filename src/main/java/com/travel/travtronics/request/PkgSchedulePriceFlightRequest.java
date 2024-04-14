package com.travel.travtronics.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PkgSchedulePriceFlightRequest {
	
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

}
