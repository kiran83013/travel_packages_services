package com.travel.travtronics.response.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttractionsRequiredPassengers {
	private Boolean all;

	private List<AttractionsRequiredPaxInfo> pax;

}
