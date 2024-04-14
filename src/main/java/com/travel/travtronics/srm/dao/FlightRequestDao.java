package com.travel.travtronics.srm.dao;

import java.util.Map;

import com.travel.travtronics.request.dto.PackageFlightDTO;

public interface FlightRequestDao {

	Map<String, Object> flightRequestFromPackage(PackageFlightDTO flightData, String token) throws Exception;
}
