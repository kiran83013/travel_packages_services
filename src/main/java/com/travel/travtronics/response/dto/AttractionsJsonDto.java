package com.travel.travtronics.response.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AttractionsJsonDto {
	private String remarks;

	private Boolean required;

	private Boolean extraCost;

	private List<Integer> attractionDay;

	private List<AttractionNames> attractionName;

	private AttractionsRequiredPassengers attractionsRequiredPassenger;

}
