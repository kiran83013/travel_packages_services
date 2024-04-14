package com.travel.travtronics.srm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "quotes_pricing")
public class PackagesModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;

	@Column(name = "header_id")
	private Integer headerId;
	
	@Column(name="base")
	private double base;

	@Column(name="tax")
	private double tax;

	@Column(name="commission")
	private double commission;

	@Column(name="m1")
	private double m1;

	@Column(name="m2")
	private double m2;

	@Column(name="d1")
	private double d1;

	@Column(name="d2")
	private double d2;
	
	@Column(name="input_vat")
	private double inputVat;
	
	@Column(name="output_vat")
	private double outputVat;

	@Column(name="total_price")
	private double totalPrice;

	@Column(name="pax_or_room_type")
	private String paxOrRoomType;

	@Column(name="pax_or_rooms_count")
	private Integer paxOrRoomsCount;
	
	@Column(name="room_or_service_no")
	private Integer roomOrServiceNo;

	@Column(name="nights_info")
	private String nightsInfo;
	
	@Column(name="segments_ref_ids")
	private String segmentsRefIds;
	
	@Column(name="record_status")
	private Integer recordStatus;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="created_date")
	private String createdDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHeaderId() {
		return headerId;
	}

	public void setHeaderId(Integer headerId) {
		this.headerId = headerId;
	}

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public double getM1() {
		return m1;
	}

	public void setM1(double m1) {
		this.m1 = m1;
	}

	public double getM2() {
		return m2;
	}

	public void setM2(double m2) {
		this.m2 = m2;
	}

	public double getD1() {
		return d1;
	}

	public void setD1(double d1) {
		this.d1 = d1;
	}

	public double getD2() {
		return d2;
	}

	public void setD2(double d2) {
		this.d2 = d2;
	}

	public double getInputVat() {
		return inputVat;
	}

	public void setInputVat(double inputVat) {
		this.inputVat = inputVat;
	}

	public double getOutputVat() {
		return outputVat;
	}

	public void setOutputVat(double outputVat) {
		this.outputVat = outputVat;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPaxOrRoomType() {
		return paxOrRoomType;
	}

	public void setPaxOrRoomType(String paxOrRoomType) {
		this.paxOrRoomType = paxOrRoomType;
	}

	public Integer getPaxOrRoomsCount() {
		return paxOrRoomsCount;
	}

	public void setPaxOrRoomsCount(Integer paxOrRoomsCount) {
		this.paxOrRoomsCount = paxOrRoomsCount;
	}

	public Integer getRoomOrServiceNo() {
		return roomOrServiceNo;
	}

	public void setRoomOrServiceNo(Integer roomOrServiceNo) {
		this.roomOrServiceNo = roomOrServiceNo;
	}

	public String getNightsInfo() {
		return nightsInfo;
	}

	public void setNightsInfo(String nightsInfo) {
		this.nightsInfo = nightsInfo;
	}

	public String getSegmentsRefIds() {
		return segmentsRefIds;
	}

	public void setSegmentsRefIds(String segmentsRefIds) {
		this.segmentsRefIds = segmentsRefIds;
	}

	public Integer getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	
	
	
	
}
