package com.travel.travtronics.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightSegmentsPriceDataReponse {
	
	private List<PkgScheduleSegmentFlightResponse> segmentsData;
	
	private List<PkgSchedulePriceFlightResponse> priceLines;

}
