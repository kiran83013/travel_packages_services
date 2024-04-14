package com.travel.travtronics.srm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.travel.travtronics.request.HolidayRequest;
import com.travel.travtronics.request.dto.PackageProductDTO;
import com.travel.travtronics.response.HolidayResponse;
import com.travel.travtronics.response.dto.PackageDetailsResponse;

public interface PackageRequestDao {

	ArrayList<HolidayResponse> packageRequest(HolidayRequest model);

	List<HolidayResponse> packageRequest(Long requestId) throws Exception;

	Map<String, Object> packageItenaryData(Long requestId) throws Exception;

	PackageProductDTO confirmPackageItenaryData(Long srId, Long loggedInUserId) throws Exception;

//	public PackageDetailsResponse getPackageDetailsBySrId(Long requestId, Boolean isPlannerProduct) throws Exception;

}
