package com.travel.travtronics.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.travel.travtronics.bpf.model.ServicePricing;

public class ServicePriceFinalResponse extends ServicePricing {

	@JsonProperty("priceList")
	private PriceHeaderLineResponse priceListModel;

	public PriceHeaderLineResponse getPriceListModel() {
		return priceListModel;
	}

	public void setPriceListModel(PriceHeaderLineResponse priceListModel) {
		this.priceListModel = priceListModel;
	}

}
