package com.travel.travtronics.request;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonRawValue;

import lombok.Data;

@Data
public class ServiceRequestData {

	private Long customerId;

	private Long contactId;

	private Long createdChannel;

	private Long updatedChannel;

	private Long srTypeId;

	private Long srStatusId;

	private Long wiw;

	private String notes;

	private Long teamId;

	@JsonRawValue
	private String data;
	
	@JsonRawValue
	private String addlData;

	private Boolean openFlag;

	private Boolean closeFlag;

	private Boolean cancelled;

	private Date submittedDate;

	private Date closeDate;

	private Date cancelledDate;

	private Long createdBy;

	private Long updatedBy;

	private Date createdDate;

	private Date updatedDate;

	private String createdDevice;

	private String createdIP;

	private String updatedDevice;

	private String updatedIP;

	private Date browserCreatedDate;

	private Date browserUpdatedDate;

	private Integer serviceTypeClass;

	private LocalDateTime plannedStartDate;

	private LocalDateTime plannedEndDate;

	private String lat;

	private String lng;
	
	private Integer isProcess;
	
	private Integer parentSrId;
	
	private Long rfqId;
	
	private Long poId;

}
