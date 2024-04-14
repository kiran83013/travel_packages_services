package com.travel.travtronics.packages.model;

import com.travel.travtronics.util.EnumYesNo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "package_itinerary_price_lines")
public class PackageItineraryPriceLinesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "line_id")
	private Integer lineId;

	@Column(name = "header_id")
	private Integer headerId;
	
	@Column(name = "itinerary_line_id")
	private Integer itineraryLineId;
	
	@Column(name = "item_id")
	private Integer itemId;
	
	@Column(name = "itinerary_day")
	private String itineraryDay;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "dynamic_price")
	@Enumerated(EnumType.STRING)
	private EnumYesNo dynamicPrice;
	
	@Column(name = "stranded_price")
	@Enumerated(EnumType.STRING)
	private EnumYesNo strandedPrice;
	
	@Column(name = "set_option")
	@Enumerated(EnumType.STRING)
	private EnumYesNo setOption;
	
	@Column(name = "set_name")
	private String setName;
	
	@Column(name = "set_primary")
	@Enumerated(EnumType.STRING)
	private EnumYesNo setPrimary;
	
	@Column(name = "breakup")
	@Enumerated(EnumType.STRING)
	private EnumYesNo breakup;
	
	@Column(name = "adt_price")
	private Double adtPrice;
	
	@Column(name = "chd_price")
	private Double chdPrice;
	
	@Column(name = "inf_price")
	private Double infPrice;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "created_date")
	private String createdDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "updated_date")
	private String updatedDate;
	
	@Column(name = "ip_address")
	private String ipAddress;

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public Integer getHeaderId() {
		return headerId;
	}

	public void setHeaderId(Integer headerId) {
		this.headerId = headerId;
	}

	public Integer getItineraryLineId() {
		return itineraryLineId;
	}

	public void setItineraryLineId(Integer itineraryLineId) {
		this.itineraryLineId = itineraryLineId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public EnumYesNo getDynamicPrice() {
		return dynamicPrice;
	}

	public void setDynamicPrice(EnumYesNo dynamicPrice) {
		this.dynamicPrice = dynamicPrice;
	}

	public EnumYesNo getStrandedPrice() {
		return strandedPrice;
	}

	public void setStrandedPrice(EnumYesNo strandedPrice) {
		this.strandedPrice = strandedPrice;
	}

	public EnumYesNo getSetOption() {
		return setOption;
	}

	public void setSetOption(EnumYesNo setOption) {
		this.setOption = setOption;
	}

	public String getSetName() {
		return setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	public EnumYesNo getSetPrimary() {
		return setPrimary;
	}

	public void setSetPrimary(EnumYesNo setPrimary) {
		this.setPrimary = setPrimary;
	}

	public Double getAdtPrice() {
		return adtPrice;
	}

	public void setAdtPrice(Double adtPrice) {
		this.adtPrice = adtPrice;
	}

	public Double getChdPrice() {
		return chdPrice;
	}

	public void setChdPrice(Double chdPrice) {
		this.chdPrice = chdPrice;
	}

	public Double getInfPrice() {
		return infPrice;
	}

	public void setInfPrice(Double infPrice) {
		this.infPrice = infPrice;
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

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public EnumYesNo getBreakup() {
		return breakup;
	}

	public void setBreakup(EnumYesNo breakup) {
		this.breakup = breakup;
	}

	public String getItineraryDay() {
		return itineraryDay;
	}

	public void setItineraryDay(String itineraryDay) {
		this.itineraryDay = itineraryDay;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	
	
	
	
}
