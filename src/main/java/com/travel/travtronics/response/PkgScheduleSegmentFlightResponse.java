package com.travel.travtronics.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PkgScheduleSegmentFlightResponse {
	
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
	
	private Integer addedBy;
	
	private String addedDate;
	
	private String addedDevice;
	
	private String addedIp;
	
	private Integer updatedBy;
	
	private String updatedDate;
	
	private String updatedDevice;
	
	private String updatedIp;
	
	

}
