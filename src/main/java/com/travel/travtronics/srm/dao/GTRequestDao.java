package com.travel.travtronics.srm.dao;

import java.util.List;

import com.travel.travtronics.request.GroundTransportRequest;

public interface GTRequestDao {

	List<GroundTransportRequest> gtRequestFromPackage(List<GroundTransportRequest> groundTransport);

}
