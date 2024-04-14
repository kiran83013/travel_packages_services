package com.travel.travtronics.response;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SREntityResponse {

	private Long id;

	private Long customerId;

	private Long contactId;

	private Long createdChannel;

	private Long updatedChannel;

	private Long wiw;

	private String notes;

	private Long teamId;

	private Long srTypeId;

	private Long srStatusId;

	private Boolean open;

	private Boolean close;

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

	private Integer isProcess;

	private Integer parentSrId;

	private Long attribute1;

	private Long attribute2;

	private Integer scFlag;

	private LocalDateTime plannedStartDate;

	private LocalDateTime plannedEndDate;

	private Long slaHeaderId;

	private String taskStartDate;

	private String taskEndDate;

	private String lat;

	private String lng;

	private Long rfqId;

	private Long poId;

	private String SrTypeName;

	private Map<String, Object> leadData;
	
	private String statusName;
}
