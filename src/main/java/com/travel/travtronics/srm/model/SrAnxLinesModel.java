package com.travel.travtronics.srm.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sr_ancillary_line")
@Getter
@Setter
public class SrAnxLinesModel {

	@Id
	@Column(name = "ancillary_line_uuid")
	private String lineUuid;

	@Column(name = "ancillary_line_id")
	private Long anxLineId;

	@Column(name = "ancillary_line_request_id")
	private Integer anxLineRequestId;

	@Column(name = "ancillary_line_type_id")
	private Integer anxLineTypeId;

	@Column(name = "ancillary_line_type")
	private String anxLineType;

	@Column(name = "ancillary_line_adt_count")
	private Integer anxLineAdtCount;

	@Column(name = "ancillary_line_chd_count")
	private Integer anxLineChdCount;

	@Column(name = "ancillary_line_inf_count")
	private Integer anxLineInfCount;

	@Column(name = "ancillary_line_json", columnDefinition = "JSON")
	private Map<String, Object> anxLineJson;

	@Column(name = "ancillary_line_addons", columnDefinition = "JSON")
	private Map<String, Object> anxLineAddons;

	@Column(name = "ancillary_line_status")
	private Integer anxLineStatus;

	@Column(name = "ancillary_line_created_by")
	private Integer anxLineCreatedBy;

	@CreationTimestamp
	@Column(name = "ancillary_line_created_date", updatable = false)
	private LocalDateTime anxLineCreatedDate;

	@Column(name = "ancillary_line_created_device")
	private String anxLineCreatedDevice;

	@Column(name = "ancillary_line_created_ip")
	private String anxLineCreatedIp;

	@Column(name = "ancillary_line_updated_by")
	private Integer anxLineUpdatedBy;

	@CreationTimestamp
	@Column(name = "ancillary_line_updated_date")
	private LocalDateTime anxLineUpdatedDate;

	@Column(name = "ancillary_line_updated_device")
	private String anxLineUpdatedDevice;

	@Column(name = "ancillary_line_updated_ip")
	private String anxLineUpdatedIp;

	@Column(name = "ancillary_line_lpo_number")
	private String anxLineLpoNumber;

	@Column(name = "ancillary_line_lpo_date")
	private Date anxLineLpoDate;

	@Column(name = "ancillary_line_lpo_amount")
	private double anxLineLpoAmount;

	@Column(name = "status_id")
	private Integer statusId;

	@Column(name = "team_id")
	private Integer teamId;

	@Column(name = "wiw_id")
	private Integer wiwId;

	@Column(name = "submited_by")
	private Integer submitedBy;

	@Column(name = "submited_date")
	private Date submitedDate;

	@Column(name = "ancillary_line_attribute_1")
	private String anxLineAttr1;

	@Column(name = "ancillary_line_attribute_2")
	private String anxLineAttr2;

	@Column(name = "ancillary_line_attribute_3")
	private String anxLineAttr3;

	@Column(name = "ancillary_line_attribute_4")
	private String anxLineAttr4;

	@Column(name = "ancillary_line_attribute_5")
	private String anxLineAttr5;

	@Column(name = "ancillary_line_attribute_6")
	private String anxLineAttr6;

	@Column(name = "ancillary_line_wf_status_id")
	private Integer ancillaryLineStatusId;

}
