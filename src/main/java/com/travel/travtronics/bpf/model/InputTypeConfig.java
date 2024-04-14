package com.travel.travtronics.bpf.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "input_type_config")
public class InputTypeConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer configId;

	private String name;

	@JsonProperty("typeId")
	private Integer type;

	@Column(name = "organizationId")
	private Long organizationId;

	@JsonRawValue
	private String source;

	@JsonRawValue
	private String ui;

	@JsonRawValue
	private String validators;

	@Column(name = "created_by", updatable = false)
	private Integer createdBy;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private String updatedDate;

	@Column(name = "created_date", updatable = false)
	private String createdDate;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;

	private String category;

	private Boolean multiselect;

	@JsonProperty("default")
	@Column(name = "default_check")
	private Boolean defaultType;

	@Column(name = "service")
	private Long serviceId;

	@JsonProperty("defaultValue")
	@Column(name = "dafault_value")
	private String dafaultValue;

	private Boolean oce;

	@Column(name = "dependent_feild")
	private String dependentField;

	private String options;

	public Boolean getDefaultType() {
		return defaultType;
	}

	public void setDefaultType(Boolean defaultType) {
		this.defaultType = defaultType;
	}

	public String getDafaultValue() {
		return dafaultValue;
	}

	public void setDafaultValue(String dafaultValue) {
		this.dafaultValue = dafaultValue;
	}

	public Boolean getOce() {
		return oce;
	}

	public void setOce(Boolean oce) {
		this.oce = oce;
	}

	public String getDependentField() {
		return dependentField;
	}

	public void setDependentField(String dependentField) {
		this.dependentField = dependentField;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public Boolean getMultiselect() {
		return multiselect;
	}

	public void setMultiselect(Boolean multiselect) {
		this.multiselect = multiselect;
	}

	public String getValidators() {
		return validators;
	}

	public void setValidators(String validators) {
		this.validators = validators;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUi() {
		return ui;
	}

	public void setUi(String ui) {
		this.ui = ui;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
}
