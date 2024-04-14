package com.travel.travtronics.srm.dao;

import java.util.Map;

import com.travel.travtronics.request.AttractionsRequest;
import com.travel.travtronics.response.dto.SrAttractionsResponse;

public interface AttrRequestDao {

	SrAttractionsResponse attrRequestFromPackage(AttractionsRequest anxRequest, String token) throws Exception;

}
