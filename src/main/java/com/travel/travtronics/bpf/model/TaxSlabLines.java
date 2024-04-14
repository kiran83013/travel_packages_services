package com.travel.travtronics.bpf.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "tax_slab_lines")
public class TaxSlabLines {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TaxSlabHeaderId")
	private Long taxSlabHeaderId;

	@Column(name = "TaxType")
	private Long taxType;

	@Column(name = "OrganizationId")
	private Long organizationId;

	@Column(name = "Description")
	private String description;

	@Column(name = "BudgetFrom")
	private Long from;

	@Column(name = "BudgetTo")
	private Long to;

	@Column(name = "SlabAmount")
	private Long slabAmount;

	@Column(name = "SlabPercentage")
	private double slabPercentage;

	@Column(name = "StartDate")
	private Date startDate;

	@Column(name = "EndDate")
	private Date endDate;

	@Column(name = "Attr1")
	private String attr1;

	@Column(name = "Attr2")
	private String attr2;

	@Column(name = "attr3")
	private String attr3;

	@CreationTimestamp
	@Column(name = "CreatedDate", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "UpdatedBy")
	private String updatedBy;

	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private Status status;

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

}
