package com.travel.travtronics.response;

import java.util.List;

public class ChargablePricingResponse {

	private Long priceLineId;

	private double taxPrice;
	
	private double discount;
	
	private double ItemPrice;
		
	private double totalPrice;
	
	private List<TaxTemplate> taxBreakup;

	public Long getPriceLineId() {
		return priceLineId;
	}

	public void setPriceLineId(Long priceLineId) {
		this.priceLineId = priceLineId;
	}

	public double getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(double taxPrice) {
		this.taxPrice = taxPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getItemPrice() {
		return ItemPrice;
	}

	public void setItemPrice(double itemPrice) {
		ItemPrice = itemPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<TaxTemplate> getTaxBreakup() {
		return taxBreakup;
	}

	public void setTaxBreakup(List<TaxTemplate> taxBreakup) {
		this.taxBreakup = taxBreakup;
	}
	
	
}
