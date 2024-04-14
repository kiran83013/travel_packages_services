package com.travel.travtronics.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.travel.travtronics.util.Status;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TaxLinesResponse {
	private Long id;

	private Long taxSlabHeaderId;

	private Long taxType;

	private String description;

	private Long organizationId;

	private Long from;

	private Long to;

	private Long slabAmount;

	private double slabPercentage;

	private Date startDate;

	private Date endDate;

	private String attr1;

	private String attr2;

	private String attr3;

	private Date createdDate;

	private Date updatedDate;

	private String createdBy;

	private String updatedBy;

	private Status status;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTaxSlabHeaderId() {
		return taxSlabHeaderId;
	}

	public void setTaxSlabHeaderId(Long taxSlabHeaderId) {
		this.taxSlabHeaderId = taxSlabHeaderId;
	}

	public Long getTaxType() {
		return taxType;
	}

	public void setTaxType(Long taxType) {
		this.taxType = taxType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getFrom() {
		return from;
	}

	public void setFrom(Long from) {
		this.from = from;
	}

	public Long getTo() {
		return to;
	}

	public void setTo(Long to) {
		this.to = to;
	}

	public Long getSlabAmount() {
		return slabAmount;
	}

	public void setSlabAmount(Long slabAmount) {
		this.slabAmount = slabAmount;
	}

	public double getSlabPercentage() {
		return slabPercentage;
	}

	public void setSlabPercentage(double slabPercentage) {
		this.slabPercentage = slabPercentage;
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

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}
