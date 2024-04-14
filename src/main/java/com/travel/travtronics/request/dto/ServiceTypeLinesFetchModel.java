package com.travel.travtronics.request.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.travel.travtronics.response.ConfigResponse;
import com.travel.travtronics.response.DependentFieldData;
import com.travel.travtronics.util.Status;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceTypeLinesFetchModel {

	private Long id;

	private Long headerId;

	@JsonIgnore
	private Integer field;

	private String description;

	private String organizationId;

	private Boolean required;

	private String info;

	private String hint;

	private Long desktopColumn;

	@JsonRawValue
	private String formData;

	private Long desktopOffset;

	private Long mobileColumn;

	private Long mobileOffset;

	private Long min;

	private Long max;

	private Long minLength;

	private Long maxLength;

	private Date minDate;

	private Date maxDate;

	private Boolean newRow;

	private Long isSpecialCharacters;

	private Long isPricing;

	private Long createdBy;

	private String createdDate;

	private Long updatedBy;

	private String updatedDate;

	private Status status;

	@JsonProperty("field")
	private ConfigResponse conigModel;

	private Integer backendColumn;

	private Integer backendOffset;

	private Long fieldOrder;

	private Boolean endStage;

	private Long stageNumber;

	private List<DependentFieldData> dependentFields;

	private Boolean isDependentFields;

	public List<DependentFieldData> getDependentFields() {
		return dependentFields;
	}

	public void setDependentFields(List<DependentFieldData> dependentFields) {
		this.dependentFields = dependentFields;
	}

	public Boolean getIsDependentFields() {
		return isDependentFields;
	}

	public void setIsDependentFields(Boolean isDependentFields) {
		this.isDependentFields = isDependentFields;
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

	public ConfigResponse getConigModel() {
		return conigModel;
	}

	public void setConigModel(ConfigResponse conigModel) {
		this.conigModel = conigModel;
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

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

}
