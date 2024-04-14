package com.travel.travtronics.bpf.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonRawValue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@DynamicUpdate
@Table(name = "service_request_data")
public class ServiceRequestDataModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "SRID")
	private Long srId;
	
	@JsonRawValue
	@Column(name = "Data")
	private String data;
	
	@JsonRawValue
	@Column(name = "Addl_Data")
	private String addlData;

	@Column(name = "Submitted_Date")
	private Date submittedDate;

	@Column(name = "Created_By")
	private Long createdBy;

	@Column(name = "Updated_By")
	private Long updatedBy;

	@CreationTimestamp
	@Column(name = "Created_Date")
	private Date createdDate;

	@UpdateTimestamp
	@Column(name = "Updated_Date")
	private Date updatedDate;
	
	@Column(name = "Created_Device")
	private String createdDevice;
	
	@Column(name = "Created_IP")
	private String createdIP;
	
	@Column(name = "Updated_Device")
	private String updatedDevice;
	
	@Column(name = "Updated_IP")
	private String updatedIP;
	
	@Column(name = "Browser_Created_Date")
	private Date browserCreatedDate;
	
	@Column(name = "Browser_Updated_Date")
	private Date browserUpdatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSrId() {
		return srId;
	}

	public void setSrId(Long srId) {
		this.srId = srId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
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

	public String getCreatedDevice() {
		return createdDevice;
	}

	public void setCreatedDevice(String createdDevice) {
		this.createdDevice = createdDevice;
	}

	public String getCreatedIP() {
		return createdIP;
	}

	public void setCreatedIP(String createdIP) {
		this.createdIP = createdIP;
	}

	public String getUpdatedDevice() {
		return updatedDevice;
	}

	public void setUpdatedDevice(String updatedDevice) {
		this.updatedDevice = updatedDevice;
	}

	public String getUpdatedIP() {
		return updatedIP;
	}

	public void setUpdatedIP(String updatedIP) {
		this.updatedIP = updatedIP;
	}

	public Date getBrowserCreatedDate() {
		return browserCreatedDate;
	}

	public void setBrowserCreatedDate(Date browserCreatedDate) {
		this.browserCreatedDate = browserCreatedDate;
	}

	public Date getBrowserUpdatedDate() {
		return browserUpdatedDate;
	}

	public void setBrowserUpdatedDate(Date browserUpdatedDate) {
		this.browserUpdatedDate = browserUpdatedDate;
	}

	public String getAddlData() {
		return addlData;
	}

	public void setAddlData(String addlData) {
		this.addlData = addlData;
	}
	
	
	
	
}
