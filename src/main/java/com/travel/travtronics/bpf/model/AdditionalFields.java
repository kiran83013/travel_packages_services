package com.travel.travtronics.bpf.model;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.travel.travtronics.util.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "additional_fields")
public class AdditionalFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "HeaderId")
	private Long headerId;

	@Column(name = "Field")
	private Integer field;

	@Column(name = "OrganizationId")
	private String organizationId;

	@Column(name = "Description")
	private String description;

	@Column(name = "Required")
	private Boolean required;

	@Column(name = "Info")
	private String info;

	@Column(name = "Hint")
	private String hint;

	@Column(name = "DesktopColumn")
	private Long desktopColumn;

	@JsonRawValue
	private String formData;

	@Column(name = "DesktopOffset")
	private Long desktopOffset;

	@Column(name = "MobileColumn")
	private Long mobileColumn;

	@Column(name = "MobileOffset")
	private Long mobileOffset;

	@Column(name = "Min")
	private Long min;

	@Column(name = "Max")
	private Long max;

	@Column(name = "MinLength")
	private Long minLength;

	@Column(name = "MaxLength")
	private Long maxLength;

	@Column(name = "MinDate")
	private Date minDate;

	@Column(name = "MaxDate")
	private Date maxDate;

	@Column(name = "NewRow")
	private Boolean newRow;

	@Column(name = "isSpecialCharacters")
	private Long isSpecialCharacters;

	@Column(name = "IsPricing")
	private Long isPricing;

	@Column(name = "CreatedBy", updatable = false)
	private Long createdBy;

	@Column(name = "CreatedDate", updatable = false)
	private String createdDate = LocalDateTime.now().toString();

	@Column(name = "UpdatedBy")
	private Long updatedBy;

	@Column(name = "UpdatedDate")
	private String updatedDate = LocalDateTime.now().toString();

	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private Status status;

	@Column(name = "BackendColumn")
	private Integer backendColumn;

	@Column(name = "BackendOffset")
	private Integer backendOffset;

	@Column(name = "FieldOrder")
	private Long fieldOrder;

	@Column(name = "EndStage")
	private Boolean endStage;

	@Column(name = "StageNumber")
	private Long stageNumber;

	@Column(name = "TransitionId")
	private Long transitionId;
}
