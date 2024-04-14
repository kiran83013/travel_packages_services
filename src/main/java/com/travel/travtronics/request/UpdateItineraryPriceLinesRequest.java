package com.travel.travtronics.request;

import com.travel.travtronics.util.EnumYesNo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateItineraryPriceLinesRequest {

	private Integer lineId;
	
	private Integer itineraryLineId;
	
	@NotNull
	private Integer itemId;
	
	@NotNull
	private EnumYesNo dynamicPrice;
	
	@NotNull
	private EnumYesNo strandedPrice;
	
	@NotNull
	private EnumYesNo setOption;
	
	private String setName;
	
	@NotNull
	private EnumYesNo setPrimary;
	
	@NotNull
	private EnumYesNo breakup;
	
	@NotNull
	private Double adtPrice;
	
	@NotNull
	private Double chdPrice;
	
	@NotNull
	private Double infPrice;
	
	@NotNull
	private Integer updatedBy;
	
	@NotBlank(message = "createdDate is should not be empty")
	private String ipAddress;

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
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

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
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
	
	
	
	
}
