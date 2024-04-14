package com.travel.travtronics.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PkgScheduleSupplierFlightResponse {
	
	private Long id;
	
	private Integer scheduleId;
	
	private Integer serviceRequestId;
	
	private Integer serviceRequestLineId;
	
	private Integer supplierId;
	
	private Integer validatingCarrier;
	
	private Integer supplierFlightStatus;
	
	private Integer addedBy;
	
	private String addedDate;
	
	private String addedDevice;
	
	private String addedIp;
	
	private Integer updatedBy;
	
	private String updatedDate;
	
	private String updatedDevice;
	
	private String updatedIp;
	
	private List<PkgScheduleSegmentFlightResponse> segmentsData;
	
	private List<PkgSchedulePriceFlightResponse> priceLines;

}
