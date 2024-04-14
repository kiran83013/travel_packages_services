package com.travel.travtronics.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PkgSupplierFlightRequest {
	
	private Long id;
	
	private Integer scheduleId;
	
	private Integer serviceRequestId;
	
	private Integer serviceRequestLineId;
	
	private Integer supplierId;
	
	private Integer validatingCarrier;
	
	private Integer supplierFlightStatus;
	
	private Integer addedBy;
	
	private String addedDevice;
	
	private String addedIp;
	
	private List<PkgScheduleSegmentFlightRequest> segmentsData;
	
	private List<PkgSchedulePriceFlightRequest> priceLines;

}
