package com.travel.travtronics.request;

import java.util.List;

public class TaxRequestModel {
    private TaxHeaderRequest taxSlabHeader;

    private List<TaxLinesRequest> taxSlabLines;

    public TaxHeaderRequest getTaxSlabHeader() {
        return taxSlabHeader;
    }

    public void setTaxSlabHeader(TaxHeaderRequest taxSlabHeader) {
        this.taxSlabHeader = taxSlabHeader;
    }

    public List<TaxLinesRequest> getTaxSlabLines() {
        return taxSlabLines;
    }

    public void setTaxSlabLines(List<TaxLinesRequest> taxSlabLines) {
        this.taxSlabLines = taxSlabLines;
    }

}
