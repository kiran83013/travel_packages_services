package com.travel.travtronics.srm.dao;

import java.util.Map;

import com.travel.travtronics.request.dto.AnxRequestLine;
import com.travel.travtronics.request.dto.PackageAnxDTO;

public interface AnxRequestDao {

	Map<String, Object> anxRequestFromPackage(PackageAnxDTO anxRequest, String token) throws Exception;

}
