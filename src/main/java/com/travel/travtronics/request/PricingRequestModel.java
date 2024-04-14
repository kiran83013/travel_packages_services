package com.travel.travtronics.request;

import java.util.List;

public class PricingRequestModel {
    private PricingHeaderRequest pricingHeader;
    private List<PricingLineRequest> pricingLines;
    public PricingHeaderRequest getPricingHeader() {
        return pricingHeader;
    }
    public void setPricingHeader(PricingHeaderRequest pricingHeader) {
        this.pricingHeader = pricingHeader;
    }
    public List<PricingLineRequest> getPricingLines() {
        return pricingLines;
    }
    public void setPricingLines(List<PricingLineRequest> pricingLines) {
        this.pricingLines = pricingLines;
    }
}
