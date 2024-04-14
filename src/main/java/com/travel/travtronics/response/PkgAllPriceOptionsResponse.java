package com.travel.travtronics.response;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PkgAllPriceOptionsResponse {
	
	private List<FlightPriceOptions> flight;
	
	private List<HotelPriceOptions> hotels;
	
	private List<Map<String, Object>> ancillaries;
	
	private List<Map<String, Object>> attractions;
	
	
	@Getter
	@Setter
	public static class FlightPriceOptions{
		
		private Integer srId;
		
		private Integer srLineId;
		
		private List<PkgScheduleSupplierFlightResponse> options;
		
	}
	
	@Getter
	@Setter
	public static class HotelPriceOptions{
		
		private Integer srId;
		
		private Integer srLineId;
		
		private List<PkgScheduleSupplierHotelResponse> options;
		
	}

}


