package com.travel.travtronics.request.dto;

import java.util.List;

import com.travel.travtronics.request.AttractionsRequest;
import com.travel.travtronics.request.GroundTransportRequest;

import lombok.Getter;
import lombok.Setter;

/*
 * to save all products 
 */
@Getter
@Setter
public class PackageProductDTO {

	private List<PackageFlightDTO> flight;

	private List<PackageHotelDTO> hotel;

	private List<PackageHospitalDTO> hospital;

	private List<PackageAnxDTO> anx;

	private List<AttractionsRequest> attraction;

	private List<GroundTransportRequest> groundTransport;

}
