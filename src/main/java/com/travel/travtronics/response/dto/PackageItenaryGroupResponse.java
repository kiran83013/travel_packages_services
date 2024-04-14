package com.travel.travtronics.response.dto;

import java.time.LocalDate;
import java.util.List;

import com.travel.travtronics.response.dto.PackageDetailsResponse.AnxItenary;
import com.travel.travtronics.response.dto.PackageDetailsResponse.AttractionItenary;
import com.travel.travtronics.response.dto.PackageDetailsResponse.FlightItenary;
import com.travel.travtronics.response.dto.PackageDetailsResponse.GroundTransportItenary;
import com.travel.travtronics.response.dto.PackageDetailsResponse.HospitalItenary;
import com.travel.travtronics.response.dto.PackageDetailsResponse.HotelItenary;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackageItenaryGroupResponse {
	private LocalDate date;
	private List<FlightItenary> flight;
	private List<HotelItenary> hotel;
	private List<HospitalItenary> hospital;
	private List<AttractionItenary> attractions;
	private List<AnxItenary> ancillary;
	private List<GroundTransportItenary> groundTransport;

}
