package com.travel.travtronics.response.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttractionsDetails {
	private String attractionName;

	private Integer attractionID;

	private String city;

	private String country;

	private String location;

	private Integer paxCount;
	private String remarks;

	private List<String> daysList;

	private List<AttractionsRequiredPaxInfo> paxDetails;

}
