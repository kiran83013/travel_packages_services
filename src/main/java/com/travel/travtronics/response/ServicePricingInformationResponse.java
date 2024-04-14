package com.travel.travtronics.response;

import lombok.Data;

@Data
public class ServicePricingInformationResponse {
    private String item;
    private String charge;
    private String field;
    private String operator;
    private String fieldValue;
}
