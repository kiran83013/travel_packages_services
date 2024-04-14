package com.travel.travtronics.bpf.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "service_request_logs")
public class ServiceRequestLogsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "sr_id")
	private Long srId;

	@Column(name = "Customer_Id")
	private Long customerId;

	@Column(name = "Contact_Id")
	private Long contactId;

	@Column(name = "Created_Channel", updatable = false)
	private Long createdChannel;

	@Column(name = "Updated_Channel")
	private Long updatedChannel;

	@Column(name = "WiW")
	private Long wiw;

	@Column(name = "Notes")
	private String notes;

	@Column(name = "Team_ID")
	private Long teamId;

	@Column(name = "SR_Type_Id")
	private Long srTypeId;

	@Column(name = "SR_Status_Id")
	private Long srStatusId;

	@Column(name = "Open_flag")
	private Boolean open;

	@Column(name = "Close_flag")
	private Boolean close;

	@Column(name = "Cancelled")
	private Boolean cancelled;

	@Column(name = "Submitted_Date")
	private Date submittedDate;

	@Column(name = "Closed_Date")
	private Date closeDate;

	@Column(name = "Cancelled_Date")
	private Date cancelledDate;

	@Column(name = "Created_By", updatable = false)
	private Long createdBy;

	@Column(name = "Updated_By")
	private Long updatedBy;

	@CreationTimestamp
	@Column(name = "Created_Date", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Column(name = "Updated_Date")
	private Date updatedDate;

	@Column(name = "Created_Device", updatable = false)
	private String createdDevice;

	@Column(name = "Created_IP", updatable = false)
	private String createdIP;

	@Column(name = "Updated_Device")
	private String updatedDevice;

	@Column(name = "Updated_IP")
	private String updatedIP;

	@Column(name = "Browser_Created_Date", updatable = false)
	private Date browserCreatedDate;

	@Column(name = "Browser_Updated_Date")
	private Date browserUpdatedDate;

	@Column(name = "Service_Type_Class")
	private Integer serviceTypeClass;

	@Column(name = "Attribute1")
	private Long attribute1;

	@Column(name = "Attribute2")
	private Long attribute2;

	@Column(name = "sc_flag")
	private Integer scFlag;

	@Column(name = "Planned_Start_Date")

	private LocalDateTime plannedStartDate;

	@Column(name = "Planned_End_Date")
	private LocalDateTime plannedEndDate;

	@Column(name = "lat")
	private String lat;

	@Column(name = "lng")
	private String lng;
	
	@Column(name = "log_created_date")
	private String logCreatedDate;

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public LocalDateTime getPlannedStartDate() {
		return plannedStartDate;
	}

	public void setPlannedStartDate(LocalDateTime plannedStartDate) {
		this.plannedStartDate = plannedStartDate;
	}

	public LocalDateTime getPlannedEndDate() {
		return plannedEndDate;
	}

	public void setPlannedEndDate(LocalDateTime plannedEndDate) {
		this.plannedEndDate = plannedEndDate;
	}

	public Integer getScFlag() {
		return scFlag;
	}

	public void setScFlag(Integer scFlag) {
		this.scFlag = scFlag;
	}

	public Long getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(Long attribute1) {
		this.attribute1 = attribute1;
	}

	public Long getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(Long attribute2) {
		this.attribute2 = attribute2;
	}

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

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getClose() {
		return close;
	}

	public void setClose(Boolean close) {
		this.close = close;
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

	public Long getSrId() {
		return srId;
	}

	public void setSrId(Long srId) {
		this.srId = srId;
	}

	public String getLogCreatedDate() {
		return logCreatedDate;
	}

	public void setLogCreatedDate(String logCreatedDate) {
		this.logCreatedDate = logCreatedDate;
	}
	
	

}
