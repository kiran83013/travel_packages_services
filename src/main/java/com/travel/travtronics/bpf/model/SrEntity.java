package com.travel.travtronics.bpf.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "service_request")
@DynamicUpdate

//@NamedNativeQuery(name = "ServiceRequest.findAllRecentRequestInfo", resultClass = RecentRequestDto.class, resultSetMapping = "findAllRecentRequestInfo", query = QueryInstance.Sr_Recent_sr_info)
//@SqlResultSetMapping(name = "findAllRecentRequestInfo", classes = @ConstructorResult(targetClass = RecentRequestDto.class, columns = {
//		@ColumnResult(name = "sr_id", type = Long.class), @ColumnResult(name = "status_id", type = Long.class),
//		@ColumnResult(name = "status_name", type = String.class), @ColumnResult(name = "sr_type_id", type = Long.class),
//		@ColumnResult(name = "sr_type_name", type = String.class),
//		@ColumnResult(name = "creation_date", type = String.class),
//		@ColumnResult(name = "submitted_date", type = String.class),
//		@ColumnResult(name = "is_process", type = Integer.class),
//		@ColumnResult(name = "parent_sr_id", type = Integer.class),
//		@ColumnResult(name = "service_type_class", type = Integer.class),
//		@ColumnResult(name = "task_start_date", type = String.class),
//		@ColumnResult(name = "task_end_date", type = String.class)
//		}))
//@SqlResultSetMapping(name = "my_need_request", classes = @ConstructorResult(targetClass = MyLeadServiceRequestDTO.class, columns = {
//	    @ColumnResult(name = "ID", type = Long.class),
//	    @ColumnResult(name = "SR_Type_Id", type = Long.class),
//	    @ColumnResult(name = "Name", type = String.class),
//	    @ColumnResult(name = "Customer_Id", type = Long.class),
//	    @ColumnResult(name = "BusinessName", type = String.class),
//	    @ColumnResult(name = "SR_Status_Id", type = Long.class),
//	    @ColumnResult(name = "name", type = String.class),
//	    @ColumnResult(name = "Submitted_Date", type = Date.class),
//	    @ColumnResult(name = "TeamId", type = Long.class),
//	    @ColumnResult(name = "TeamName", type = String.class),
//	    @ColumnResult(name = "Created_Date", type = Date.class),
//	    @ColumnResult(name = "Service_Type_Class", type = Integer.class),
//	    @ColumnResult(name = "is_process", type = Integer.class),
//	    @ColumnResult(name = "parent_sr_id", type = Integer.class),
//	    @ColumnResult(name = "Contact_Id", type = Long.class),
//	    @ColumnResult(name = "IsDynamicForm", type = Integer.class),
//	    @ColumnResult(name = "task_start_date", type = String.class),
//	    @ColumnResult(name = "task_end_date", type = String.class),
//	    @ColumnResult(name = "DepartmentId", type = String.class),
//	    @ColumnResult(name = "rfq_id", type = Long.class),
//	    @ColumnResult(name = "Created_By", type = Long.class),
//	    @ColumnResult(name = "first_name", type = String.class),
//	    @ColumnResult(name = "last_name", type = String.class),
//	    @ColumnResult(name = "Data", type = String.class)
//	}))
//@NamedNativeQuery(name = "ServiceRequest.getMyNeedServiceRequests", resultClass = MyLeadServiceRequestDTO.class, resultSetMapping = "my_need_request", query = QueryInstance.MY_NEED_SERVICE_REQUEST)

public class SrEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "Customer_Id")
	private Long customerId;

	@Column(name = "Contact_Id")
	private Long contactId;

	@Column(name = "Created_Channel", updatable = false)
	private Long createdChannel;

	@Column(name = "Updated_Channel")
	private Long updatedChannel;

	@Column(name = "WiW")
	private Long wiw;

	@Column(name = "Notes")
	private String notes;

	@Column(name = "Team_ID")
	private Long teamId;

	@Column(name = "SR_Type_Id")
	private Long srTypeId;

	@Column(name = "SR_Status_Id")
	private Long srStatusId;

	@Column(name = "Open_flag")
	private Boolean open;

	@Column(name = "Close_flag")
	private Boolean close;

	@Column(name = "Cancelled")
	private Boolean cancelled;

	@Column(name = "Submitted_Date")
	private Date submittedDate;

	@Column(name = "Closed_Date")
	private Date closeDate;

	@Column(name = "Cancelled_Date")
	private Date cancelledDate;

	@Column(name = "Created_By", updatable = false)
	private Long createdBy;

	@Column(name = "Updated_By")
	private Long updatedBy;

	@CreationTimestamp
	@Column(name = "Created_Date", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Column(name = "Updated_Date")
	private Date updatedDate;

	@Column(name = "Created_Device", updatable = false)
	private String createdDevice;

	@Column(name = "Created_IP", updatable = false)
	private String createdIP;

	@Column(name = "Updated_Device")
	private String updatedDevice;

	@Column(name = "Updated_IP")
	private String updatedIP;

	@Column(name = "Browser_Created_Date", updatable = false)
	private Date browserCreatedDate;

	@Column(name = "Browser_Updated_Date")
	private Date browserUpdatedDate;

	@Column(name = "Service_Type_Class")
	private Integer serviceTypeClass;

	@Column(name = "is_process")
	private Integer isProcess;

	@Column(name = "parent_sr_id")
	private Integer parentSrId;

	@Column(name = "Attribute1")
	private Long attribute1;

	@Column(name = "Attribute2")
	private Long attribute2;

	@Column(name = "sc_flag")
	private Integer scFlag;

	@Column(name = "Planned_Start_Date")
	private LocalDateTime plannedStartDate;

	@Column(name = "Planned_End_Date")
	private LocalDateTime plannedEndDate;
	
	@Column(name = "sla_header_id")
	private Long slaHeaderId;
	
	@Column(name = "task_start_date")
	private String taskStartDate;

	@Column(name = "task_end_date")
	private String taskEndDate;

	@Column(name = "lat")
	private String lat;

	@Column(name = "lng")
	private String lng;
	
	@Column(name = "rfq_id")
	private Long rfqId;
	
	@Column(name = "po_id")
	private Long poId;
	
	

}
