package com.travel.travtronics.srm.model;

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
@Table(name = "package_schedule_price_item_link")
public class PackageSchedulePriceItemLink {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "service_request_id")
	private Integer serviceRequestId;

	@Column(name = "service_request_line_id")
	private Integer serviceRequestLineId;

	@Column(name = "service_request_type_id")
	private Integer serviceRequestTypeId;

	@Column(name = "schedule_id")
	private Integer scheduleId;

	@Column(name = "product_id")
	private Integer productId;

	@Column(name = "price_item_id")
	private Integer priceItemId;

	@Column(name = "link_status")
	private Integer linkStatus;

	@Column(name = "added_by", updatable = false)
	private Integer addedBy;
	
	@CreationTimestamp
	@Column(name = "added_date",updatable = false)
	private Date addedDate;

	@Column(name = "added_device", columnDefinition = "LONGTEXT")
	private String addedDevice;

	@Column(name = "added_ip")
	private String addedIp;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@UpdateTimestamp
	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "updated_device", columnDefinition = "LONGTEXT")
	private String updatedDevice;

	@Column(name = "updated_ip")
	private String updatedIp;

	@Column(name = "attribute_1")
	private String attribute1;

	@Column(name = "attribute_2")
	private String attribute2;

	@Column(name = "attribute_3")
	private String attribut3;

	@Column(name = "attribute_4")
	private Integer attribute4;

	@Column(name = "attribute_5")
	private Integer attribute5;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getServiceRequestId() {
		return serviceRequestId;
	}

	public void setServiceRequestId(Integer serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}

	public Integer getServiceRequestLineId() {
		return serviceRequestLineId;
	}

	public void setServiceRequestLineId(Integer serviceRequestLineId) {
		this.serviceRequestLineId = serviceRequestLineId;
	}

	public Integer getServiceRequestTypeId() {
		return serviceRequestTypeId;
	}

	public void setServiceRequestTypeId(Integer serviceRequestTypeId) {
		this.serviceRequestTypeId = serviceRequestTypeId;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getPriceItemId() {
		return priceItemId;
	}

	public void setPriceItemId(Integer priceItemId) {
		this.priceItemId = priceItemId;
	}

	public Integer getLinkStatus() {
		return linkStatus;
	}

	public void setLinkStatus(Integer linkStatus) {
		this.linkStatus = linkStatus;
	}

	public Integer getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(Integer addedBy) {
		this.addedBy = addedBy;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public String getAddedDevice() {
		return addedDevice;
	}

	public void setAddedDevice(String addedDevice) {
		this.addedDevice = addedDevice;
	}

	public String getAddedIp() {
		return addedIp;
	}

	public void setAddedIp(String addedIp) {
		this.addedIp = addedIp;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
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

	public String getUpdatedIp() {
		return updatedIp;
	}

	public void setUpdatedIp(String updatedIp) {
		this.updatedIp = updatedIp;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribut3() {
		return attribut3;
	}

	public void setAttribut3(String attribut3) {
		this.attribut3 = attribut3;
	}

	public Integer getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(Integer attribute4) {
		this.attribute4 = attribute4;
	}

	public Integer getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(Integer attribute5) {
		this.attribute5 = attribute5;
	}

}
