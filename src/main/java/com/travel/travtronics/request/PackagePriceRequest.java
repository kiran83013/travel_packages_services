package com.travel.travtronics.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PackagePriceRequest {

	private Integer itineraryId;

	private String validFromDate;

	private String validToDate;

	private Integer createdBy;

	private String ipAddress;

	private String name;
	private String description;

	@JsonProperty("businessUnit")
	private Integer buId;

	@JsonProperty("costCenter")
	private Integer ccId;

	@JsonProperty("location")
	private Integer locationId;

	private Integer customerType;

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getBuId() {
		return buId;
	}

	public void setBuId(Integer buId) {
		this.buId = buId;
	}

	public Integer getCcId() {
		return ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	private List<ItineraryPriceLinesRequest> itineraryPriceLines;

	public Integer getItineraryId() {
		return itineraryId;
	}

	public void setItineraryId(Integer itineraryId) {
		this.itineraryId = itineraryId;
	}

	public String getValidFromDate() {
		return validFromDate;
	}

	public void setValidFromDate(String validFromDate) {
		this.validFromDate = validFromDate;
	}

	public String getValidToDate() {
		return validToDate;
	}

	public void setValidToDate(String validToDate) {
		this.validToDate = validToDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public List<ItineraryPriceLinesRequest> getItineraryPriceLines() {
		return itineraryPriceLines;
	}

	public void setItineraryPriceLines(List<ItineraryPriceLinesRequest> itineraryPriceLines) {
		this.itineraryPriceLines = itineraryPriceLines;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
