package com.travel.travtronics.response;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FeildLineMappedResponseDto {

	private FeildUiResponse ui;
	
	private String name;

	private String type;

	private List<Map<String, Object>> source;

	private String status;

	private Integer typeId;

	private String options;

	private String service;

	private String category;

	private Integer configId;

	private Integer createdBy;

	private Integer isPricing;

	private Integer updatedBy;

	private Map<String, Object> validators;

	private String createdDate;

	private String updatedDate;

	private String defaultValue;

	private String dependentField;

	private Integer organizationId;

	private Long feildOrder;

	private String serviceUrl;

	private Boolean isExternalUrl;
	
	private List<DependentFieldData> dependentFields;

	private Boolean isDependentFields;
	
	private List<Map<String, Object>> formInput;
	
	private EservicePricingResponse priceInfo;

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

	public Long getFeildOrder() {
		return feildOrder;
	}

	public void setFeildOrder(Long feildOrder) {
		this.feildOrder = feildOrder;
	}

	public FeildUiResponse getUi() {
		return ui;
	}

	public void setUi(FeildUiResponse ui) {
		this.ui = ui;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Map<String, Object>> getSource() {
		return source;
	}

	public void setSource(List<Map<String, Object>> source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
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

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getIsPricing() {
		return isPricing;
	}

	public void setIsPricing(Integer isPricing) {
		this.isPricing = isPricing;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Map<String, Object> getValidators() {
		return validators;
	}

	public void setValidators(Map<String, Object> validators) {
		this.validators = validators;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDependentField() {
		return dependentField;
	}

	public void setDependentField(String dependentField) {
		this.dependentField = dependentField;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

}
