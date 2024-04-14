package com.travel.travtronics.response.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddOnDetails {

	private Integer addOnId;

	private String addOnName;

	private String addOnCode;

	private String addOnDescription;

	private String addOnType;

	private String remarks;

	private Boolean extraCost;

	private Integer paxCount;

	private Boolean required;

	private List<AttractionsRequiredPaxInfo> paxDetails;

	private List<String> daysList;

	/*
	 * this node does not contains standard data -may vary based on ui form data
	 */
	@JsonInclude(value = Include.NON_NULL)
	private JsonNode dynamicTabData;

}
