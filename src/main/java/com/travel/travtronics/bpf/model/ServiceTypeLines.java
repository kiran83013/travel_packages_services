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

@Entity
@Table(name = "service_types_lines")
public class ServiceTypeLines {

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

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHeaderId() {
		return headerId;
	}

	public void setHeaderId(Long headerId) {
		this.headerId = headerId;
	}

	public Integer getField() {
		return field;
	}

	public void setField(Integer field) {
		this.field = field;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public Long getDesktopColumn() {
		return desktopColumn;
	}

	public void setDesktopColumn(Long desktopColumn) {
		this.desktopColumn = desktopColumn;
	}

	public String getFormData() {
		return formData;
	}

	public void setFormData(String formData) {
		this.formData = formData;
	}

	public Long getDesktopOffset() {
		return desktopOffset;
	}

	public void setDesktopOffset(Long desktopOffset) {
		this.desktopOffset = desktopOffset;
	}

	public Long getMobileColumn() {
		return mobileColumn;
	}

	public void setMobileColumn(Long mobileColumn) {
		this.mobileColumn = mobileColumn;
	}

	public Long getMobileOffset() {
		return mobileOffset;
	}

	public void setMobileOffset(Long mobileOffset) {
		this.mobileOffset = mobileOffset;
	}

	public Long getMin() {
		return min;
	}

	public void setMin(Long min) {
		this.min = min;
	}

	public Long getMax() {
		return max;
	}

	public void setMax(Long max) {
		this.max = max;
	}

	public Long getMinLength() {
		return minLength;
	}

	public void setMinLength(Long minLength) {
		this.minLength = minLength;
	}

	public Long getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Long maxLength) {
		this.maxLength = maxLength;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public Boolean getNewRow() {
		return newRow;
	}

	public void setNewRow(Boolean newRow) {
		this.newRow = newRow;
	}

	public Long getIsSpecialCharacters() {
		return isSpecialCharacters;
	}

	public void setIsSpecialCharacters(Long isSpecialCharacters) {
		this.isSpecialCharacters = isSpecialCharacters;
	}

	public Long getIsPricing() {
		return isPricing;
	}

	public void setIsPricing(Long isPricing) {
		this.isPricing = isPricing;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getBackendColumn() {
		return backendColumn;
	}

	public void setBackendColumn(Integer backendColumn) {
		this.backendColumn = backendColumn;
	}

	public Integer getBackendOffset() {
		return backendOffset;
	}

	public void setBackendOffset(Integer backendOffset) {
		this.backendOffset = backendOffset;
	}

	public Long getFieldOrder() {
		return fieldOrder;
	}

	public void setFieldOrder(Long fieldOrder) {
		this.fieldOrder = fieldOrder;
	}

	public Boolean getEndStage() {
		return endStage;
	}

	public void setEndStage(Boolean endStage) {
		this.endStage = endStage;
	}

	public Long getStageNumber() {
		return stageNumber;
	}

	public void setStageNumber(Long stageNumber) {
		this.stageNumber = stageNumber;
	}

}
