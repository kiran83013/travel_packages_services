package com.travel.travtronics.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PkgScheduleSegmentFlightRequest {

	private Long id;
	
	private Long supplierFlightId;
	
	private String deptCode;
	
	private Integer deptDay;
	
	private String deptTime;
	
	private String arrCode;
	
	private Integer arrDay;
	
	private String arrTime;
	
	private String marketingCarrier;
	
	private String operatingCarrier;
	
	private String flightNumber;
	
	private String cabinClass;
	
	private String rbd;
	
	private String baggage;
	
	private String equipment;
	
	
}
