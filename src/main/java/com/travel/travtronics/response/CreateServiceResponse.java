package com.travel.travtronics.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class CreateServiceResponse {

	private Long id;
	
	private Long customerId;

	private Long contactId;

	private Long createdChannel;

	private Long updatedChannel;

	private Long srTypeId;

	private Long srStatusId;
	
	private Long wiw;
	
	private String notes;
	
	private Long teamId;

	@JsonRawValue
	private String data;
	
	@JsonRawValue
	private String addlData;

	private Boolean openFlag;

	private Boolean closeFlag;

	private Boolean cancelled;

	private Date submittedDate;

	private Date closeDate;

	private Date cancelledDate;

	private Long createdBy;

	private Long updatedBy;

	private Date createdDate;

	private Date updatedDate;
	
	private String createdDevice;
	
	private String createdIP;
	
	private String updatedDevice;
	
	private String updatedIP;
	
	private Date browserCreatedDate;
	
	private Date browserUpdatedDate;
	
	private Integer serviceTypeClass;
	
	

	public Integer getServiceTypeClass() {
		return serviceTypeClass;
	}

	public void setServiceTypeClass(Integer serviceTypeClass) {
		this.serviceTypeClass = serviceTypeClass;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public Long getSrTypeId() {
		return srTypeId;
	}

	public void setSrTypeId(Long srTypeId) {
		this.srTypeId = srTypeId;
	}

	public Long getSrStatusId() {
		return srStatusId;
	}

	public void setSrStatusId(Long srStatusId) {
		this.srStatusId = srStatusId;
	}

	public Long getWiw() {
		return wiw;
	}

	public void setWiw(Long wiw) {
		this.wiw = wiw;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Boolean getOpenFlag() {
		return openFlag;
	}

	public void setOpenFlag(Boolean openFlag) {
		this.openFlag = openFlag;
	}

	public Boolean getCloseFlag() {
		return closeFlag;
	}

	public void setCloseFlag(Boolean closeFlag) {
		this.closeFlag = closeFlag;
	}

	public Boolean getCancelled() {
		return cancelled;
	}

	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Date getCancelledDate() {
		return cancelledDate;
	}

	public void setCancelledDate(Date cancelledDate) {
		this.cancelledDate = cancelledDate;
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


	public Long getCreatedChannel() {
		return createdChannel;
	}

	public void setCreatedChannel(Long createdChannel) {
		this.createdChannel = createdChannel;
	}

	public Long getUpdatedChannel() {
		return updatedChannel;
	}

	public void setUpdatedChannel(Long updatedChannel) {
		this.updatedChannel = updatedChannel;
	}

	public String getAddlData() {
		return addlData;
	}

	public void setAddlData(String addlData) {
		this.addlData = addlData;
	}
	
	
	
}
