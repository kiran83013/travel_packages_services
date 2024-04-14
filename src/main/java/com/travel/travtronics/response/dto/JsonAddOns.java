package com.travel.travtronics.response.dto;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonAddOns {

	private String remarks;

	private Boolean required;

	private JsonAddOnType addOnType;

	private Boolean extraCost;

	private AddonsRequiredPassengers requiredPassenger;

	private JsonNode dynamicTabData;

}
