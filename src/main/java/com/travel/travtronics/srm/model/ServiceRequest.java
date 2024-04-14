package com.travel.travtronics.srm.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sr_request")
@Getter
@Setter
public class ServiceRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REQUESTID")
	private Long requestId;

	@Column(name = "CUSTOMERID")
	private Long customerId;

	@Column(name = "CUSTOMERCONTACTID")
	private Long contactId;

	@Column(name = "Type")
	private Integer requestType;

	@Column(name = "PriorityID")
	private Integer priorityId;

	@Column(name = "SeverityID")
	private Integer severityId;

//	@Column(name="StatusID")
//	private Status status;

	@Column(name = "ChannelID")
	private Integer channelId;

//	@Column(name="AddressID")
//	private Long addressId;

	@Column(name = "CreatedBy")
	private Long createdBy;

	@Column(name = "CreatedDate")
	private Date createdDate;

	@Column(name = "UpdatedBy")
	private Long updatedBy;

	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@Column(name = "ClosedBy")
	private Long closedBy;

	@Column(name = "ClosedDate")
	private Date closedDate;

	@Column(name = "RequestStatus")
	private String requestStatus;

	@Column(name = "Copy")
	private Integer copy;

	@Column(name = "CopyFrom")
	private Integer copyFrom;

	@Column(name = "CopyTo")
	private Integer copyTo;

	@Column(name = "ApproverAssignedBy")
	private Long approverAssignedBy;

	@Column(name = "ApproverID")
	private Long approverId;

	@Column(name = "ApproverStatus")
	private Long approverStatus;

	@Column(name = "ApproverRemarks")
	private String approverRemarks;

	@Column(name = "ApproverCreatedDate")
	private Date approverCreatedDate;

	@Column(name = "ApproverCreatedBy")
	private Long approverCreatedBy;

	@Column(name = "ApproverModifiedDate")
	private Date approverModifiedDate;

	@Column(name = "ApproverModifiedBy")
	private Long approverModifiedBy;

	@Column(name = "PackageRequest")
	private Integer packageRequest = 0;

	@Column(name = "DmcFlag")
	private Integer dmcFlag = 0;

	@Column(name = "MedInfoFlag")
	private Integer medInfoFlag = 0;

	@Column(name = "ProductId")
	private Integer productId = 0;

	public enum Priority {
		L, M, H
	}

	public enum Severity {
		L, M, H
	}

	public enum Status {
		L, M, H
	}

	public enum RequestType {
		CORPORATE, RETAIL
	}

}
