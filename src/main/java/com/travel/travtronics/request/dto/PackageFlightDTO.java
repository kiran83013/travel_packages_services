package com.travel.travtronics.request.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackageFlightDTO {

	private ServiceRequestLineModel requestData;

	private RequestLinePaxDto paxData;

}
