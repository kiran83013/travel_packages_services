package com.travel.travtronics.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class UpdateServiceRequestLineData {

	@JsonRawValue
	private String data;
	
	@JsonRawValue
	private String addlData;

	private Date submittedDate;

	private Long updatedBy;

	private Date updatedDate;
	
	private String updatedDevice;
	
	private String updatedIP;
	
	private Date browserUpdatedDate;

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

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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

