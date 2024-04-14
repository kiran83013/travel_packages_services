package com.travel.travtronics.srm.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.travtronics.converter.GroundTransportConverter;
import com.travel.travtronics.request.GroundTransportRequest;
import com.travel.travtronics.srm.dao.GTRequestDao;
import com.travel.travtronics.srm.model.GTLine;
import com.travel.travtronics.srm.model.GTPax;
import com.travel.travtronics.srm.repository.GroundLinePaxRepository;
import com.travel.travtronics.srm.repository.GroundTranportLineRepository;
import com.travel.travtronics.srm.repository.ServiceRequestLineRepository;

@Repository("gtRequestDao")
public class GTRequestDaoImpl implements GTRequestDao {

	@Autowired
	ServiceRequestLineRepository seqnumRepository;

	@Autowired
	GroundTranportLineRepository groundTranportLineRepository;

	@Autowired
	GroundLinePaxRepository groundLinePaxRepository;

	@Override
	public List<GroundTransportRequest> gtRequestFromPackage(List<GroundTransportRequest> groundTransport) {
		return groundTransport.stream().map(request -> saveGroundTransportRequest(request))
				.collect(Collectors.toList());
	}

	public GroundTransportRequest saveGroundTransportRequest(GroundTransportRequest requestData) {
		Long requestLineId = seqnumRepository.generateSeqenceNumber(String.valueOf(requestData.getRequestId()), "33",
				2);

		requestData.setRequestLineId(requestLineId.intValue());

		GTLine convertedLine = GroundTransportConverter.convertGroundTransportRequestTOEntity(requestData);

		GTLine savedLine = groundTranportLineRepository.save(convertedLine);

		List<GTPax> savedPaxLine = requestData.getPax().stream().peek(pax -> {
			pax.setRequestLineId(Long.valueOf(requestLineId));
			pax.setRequestId(savedLine.getRequestId().longValue());
		}).map(GroundTransportConverter::convertGroundTransportPaxRequestTOEntity).map(groundLinePaxRepository::save)
				.collect(Collectors.toList());

		GroundTransportRequest responseData = GroundTransportConverter.generateGroundTransportResponse(savedLine,
				savedPaxLine);

		return responseData;
	}

}
