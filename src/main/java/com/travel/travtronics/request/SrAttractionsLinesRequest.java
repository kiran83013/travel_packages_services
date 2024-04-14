package com.travel.travtronics.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SrAttractionsLinesRequest {
	@Valid
	@NotNull
	private Integer attractionId;

	@Valid
	@NotEmpty
	private String attractionLineName;

	@Valid
	@NotEmpty
	private String attractionLineCity;

	@Valid
	@NotEmpty
	private String attractionLineLocation;

	private String attractionLineCountry;

	private Integer attractionLinePaxCount;

	private Integer attractionLineDay;

	private String attractionLineDate;

	private List<SrAttractionsLinePaxRequest> passengers;
	private Integer attractionStatusId;

}
