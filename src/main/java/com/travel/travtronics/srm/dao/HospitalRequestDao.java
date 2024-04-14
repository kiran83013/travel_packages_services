package com.travel.travtronics.srm.dao;

import java.util.Map;

import com.travel.travtronics.request.dto.PackageHospitalDTO;

public interface HospitalRequestDao {

	Map<String, Object> hospitalRequestFromPackage(PackageHospitalDTO hospitalRequest, String token) throws Exception;

}
