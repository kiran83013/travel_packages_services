package com.travel.travtronics.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PkgSubHotelRequest {
	
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
	
	private List<PkgPriceHotelRequest> supbkgprcoptionprice;

}
