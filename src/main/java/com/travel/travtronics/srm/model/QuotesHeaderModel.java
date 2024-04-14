package com.travel.travtronics.srm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quotes_header")
public class QuotesHeaderModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "custmer_id")
	private Integer custmerId;

	@Column(name = "contact_id")
	private Integer contactId;

	@Column(name = "supplier_id")
	private Integer supplierId;

	@Column(name = "merge_id")
	private Integer mergeId;

	@Column(name = "seq_no")
	private Integer seqNo;

	@Column(name = "sr_id")
	private Integer srId;

	@Column(name = "sr_line_id")
	private Integer srLineId;

	@Column(name = "product_id")
	private Integer productId;

	@Column(name = "status_id")
	private Integer statusId;

	@Column(name = "status_code")
	private String statusCode;

	@Column(name = "booking_ref_no")
	private String bookingRefNo;

	@Column(name = "channel")
	private String channel;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "fare_rules")
	private String fareRules;

	@Column(name = "internal_policies")
	private String internalPolicies;

	@Column(name = "reject_reason")
	private String rejectReason;

	@Column(name = "customer_option")
	private Integer customerOption;

	@Column(name = "encrypt_sr")
	private String encryptSr;

	@Column(name = "encrypt_sr_line")
	private String encryptSrLine;
	
	@Column(name = "lead_id")
	private Integer leadId;
	
	@Column(name = "schedule_id")
	private Integer scheduleId;
	
	@Column(name = "adt_count")
	private Integer adtCount;
	
	@Column(name = "chd_count")
	private Integer chdCount;
	
	@Column(name = "inf_count")
	private Integer infCount;
	
	@Column(name = "chd_ages")
	private String chdAges;
	
	@Column(name = "required_date")
	private String requiredDate;

	@Column(name = "record_status")
	private Integer recordStatus;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private String createdDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private String updatedDate;

	@Column(name = "submitted_by")
	private Integer submittedBy;

	@Column(name = "submitted_date")
	private String submittedDate;

	@Column(name = "approved_by")
	private Integer approvedBy;

	@Column(name = "approved_date")
	private String approvedDate;

	@Column(name = "fullfilled_by")
	private Integer fullfilledBy;

	@Column(name = "fullfilled_date")
	private String fullfilledDate;

}
