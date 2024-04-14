package com.travel.travtronics.response;

import java.util.List;

import com.travel.travtronics.bpf.model.PricingHeader;

public class PriceHeaderLineResponse {

	private PricingHeader pricingHeader;

	private List<PriceLineResponseModel> pricingLines;

	public PricingHeader getPricingHeader() {
		return pricingHeader;
	}

	public void setPricingHeader(PricingHeader pricingHeader) {
		this.pricingHeader = pricingHeader;
	}

	public List<PriceLineResponseModel> getPricingLines() {
		return pricingLines;
	}

	public void setPricingLines(List<PriceLineResponseModel> pricingLines) {
		this.pricingLines = pricingLines;
	}

}
