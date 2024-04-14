package com.travel.travtronics.request;

import java.util.Date;

import com.travel.travtronics.bpf.model.Item;
import com.travel.travtronics.util.Status;

import lombok.Data;

@Data
public class ItemRequest {
	private String name;

	private String Code;

	private Long organizationId;

	private String fc;

	private Long type;

	private Long category;

	private Long subCategory;

	private Long taxCategory;

	private Item.EntityImport tax;

	private Item.EntityImport slab;

	private String authority;

	private String description;

	private Long country;

	private String state;

	private String city;

	private String zipCode;

	private Date startDate;

	private Date endDate;

	private Integer createdBy;

	private Integer updatedBy;

	private Date createdDate;

	private Date updatedDate;

	private Status status;

	private String unitofmeasurement;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getFc() {
		return fc;
	}

	public void setFc(String fc) {
		this.fc = fc;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public Long getSubCategory() {
		return subCategory;
	}

	public void setTaxCategory(Long taxCategory) {
		this.taxCategory = taxCategory;
	}

	public Long getTaxCategory() {
		return taxCategory;
	}

	public void setSubCategory(Long subCategory) {
		this.subCategory = subCategory;
	}

	public Item.EntityImport getTax() {
		return tax;
	}

	public void setTax(Item.EntityImport tax) {
		this.tax = tax;
	}

	public Item.EntityImport getSlab() {
		return slab;
	}

	public void setSlab(Item.EntityImport slab) {
		this.slab = slab;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCountry() {
		return country;
	}

	public void setCountry(Long country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}
