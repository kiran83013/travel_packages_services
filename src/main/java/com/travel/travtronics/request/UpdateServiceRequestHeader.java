package com.travel.travtronics.request;

import java.util.Date;

public class UpdateServiceRequestHeader {

	private Long customerId;

	private Long contactId;

	private Long createdChannel;

	private Long updatedChannel;

	private Long srTypeId;

	private Long srStatusId;
	
	private Long wiw;
	
	private String notes;
	
	private Long teamId;

	private Boolean openFlag;

	private Boolean closeFlag;

	private Boolean cancelled;

	private Date submittedDate;

	private Date closeDate;

	private Date cancelledDate;

	private Long updatedBy;

	private Date updatedDate;
	
	private String updatedDevice;
	
	private String updatedIP;
	
	private Date browserUpdatedDate;
	
	private Integer isProcess;
	
	private Integer parentSrId;
		
	public Integer getIsProcess() {
		return isProcess;
	}

	public void setIsProcess(Integer isProcess) {
		this.isProcess = isProcess;
	}

	public Integer getParentSrId() {
		return parentSrId;
	}

	public void setParentSrId(Integer parentSrId) {
		this.parentSrId = parentSrId;
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
	
}
