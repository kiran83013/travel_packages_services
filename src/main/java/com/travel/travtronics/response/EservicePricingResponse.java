package com.travel.travtronics.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EservicePricingResponse {

	private Long servicTypeHeaderId;
	
	private Long priceHeahderId;
	
	private Long priceLineId;

	private Integer itemId;

	private String itemName;
	
	private String itemCode;
	
	private Integer inputValue;

	private String Description;

	private double taxPrice;

	private double discount;

	private double ItemPrice;

	private double totalPrice;

	@JsonIgnore
	private Integer organizationId;

	@JsonIgnore
	private String organizationName;

	private List<TaxTemplate> taxBreakup;

	

}
