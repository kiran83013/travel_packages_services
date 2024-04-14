package com.travel.travtronics.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.travel.travtronics.bpf.model.Item;
import com.travel.travtronics.util.Status;

public class PriceLineResponseModel {

	private Long id;

	private Long headerId;

	@JsonIgnore
	private Long item;

	private String taxSlab;

	private String api;

	private String charge;

	private Item.EntityImport qualifier;

	private Item.EntityImport fieldDependent;

	private Item.EntityImport percentage;

	private Integer field;

	private String variableFrequency;

	private String operator;

	private String value;

	private Item.EntityImport portalVisible;

	private Item.EntityImport statutory;

	private Date startDate;

	private Date endDate;

	private String attr1;

	private String attr2;

	private String attr3;

	private Integer createdBy;

	private Integer updatedBy;

	private Date createdDate;

	private Date updatedDate;

	private Status status;

	@JsonProperty("item")
	private Item itemConfig;

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

	public Long getItem() {
		return item;
	}

	public void setItem(Long item) {
		this.item = item;
	}

	public String getTaxSlab() {
		return taxSlab;
	}

	public void setTaxSlab(String taxSlab) {
		this.taxSlab = taxSlab;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public Item.EntityImport getQualifier() {
		return qualifier;
	}

	public void setQualifier(Item.EntityImport qualifier) {
		this.qualifier = qualifier;
	}

	public Item.EntityImport getFieldDependent() {
		return fieldDependent;
	}

	public void setFieldDependent(Item.EntityImport fieldDependent) {
		this.fieldDependent = fieldDependent;
	}

	public Item.EntityImport getPercentage() {
		return percentage;
	}

	public void setPercentage(Item.EntityImport percentage) {
		this.percentage = percentage;
	}

	public Integer getField() {
		return field;
	}

	public void setField(Integer field) {
		this.field = field;
	}

	public String getVariableFrequency() {
		return variableFrequency;
	}

	public void setVariableFrequency(String variableFrequency) {
		this.variableFrequency = variableFrequency;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Item.EntityImport getPortalVisible() {
		return portalVisible;
	}

	public void setPortalVisible(Item.EntityImport portalVisible) {
		this.portalVisible = portalVisible;
	}

	public Item.EntityImport getStatutory() {
		return statutory;
	}

	public void setStatutory(Item.EntityImport statutory) {
		this.statutory = statutory;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAttr1() {
		return attr1;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

	public String getAttr2() {
		return attr2;
	}

	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}

	public String getAttr3() {
		return attr3;
	}

	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Item getItemConfig() {
		return itemConfig;
	}

	public void setItemConfig(Item itemConfig) {
		this.itemConfig = itemConfig;
	}

}
