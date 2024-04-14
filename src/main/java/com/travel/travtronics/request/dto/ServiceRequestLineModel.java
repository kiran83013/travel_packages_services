package com.travel.travtronics.request.dto;

import java.util.List;

import com.travel.travtronics.srm.model.ServiceRequestLine;
import com.travel.travtronics.srm.model.ServiceRequestSegment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestLineModel {
	private ServiceRequestLine serviceRequestLine;

	private List<ServiceRequestSegment> serviceRequestSegment;

}
