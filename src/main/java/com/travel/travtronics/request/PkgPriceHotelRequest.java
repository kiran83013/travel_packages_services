package com.travel.travtronics.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PkgPriceHotelRequest {
	
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

}
