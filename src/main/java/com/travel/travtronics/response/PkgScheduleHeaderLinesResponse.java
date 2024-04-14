package com.travel.travtronics.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PkgScheduleHeaderLinesResponse {
	
	private Long id;
	
	private Long headerId;
	
	private String lineGroupId;
	
	private Integer lineOptionId;
	
	private Integer defaultOption;
	
	private Integer lineStatus;
	
	private Integer addedBy;
	
	private String addedDate;
	
	private String addedDevice;
	
	private String addedIp;
	
	private Integer updatedBy;
	
	private String updatedDate;
	
	private String updatedDevice;
	
	private String updatedIp;

}
