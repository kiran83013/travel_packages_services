package com.travel.travtronics.srm.dao;

import java.util.Map;

import com.travel.travtronics.request.dto.PackageHotelDTO;

public interface HotelRequestDao {

	Map<String, Object> hotelRequestFromPackage(PackageHotelDTO hotelRequest, String token) throws Exception;

}
