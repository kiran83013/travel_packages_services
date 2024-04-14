package com.travel.travtronics.response.dto;

import java.util.List;

import com.travel.travtronics.srm.model.SrAttractionsLinePaxModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SrAttractionsLinesResponse {

	private Long attractionLineId;

	private Long attractionHeaderId;

	private Long attractionRequestId;

	private Integer attractionId;

	private String attractionLineName;

	private String attractionLineCity;

	private String attractionLineLocation;

	private String attractionLineCountry;

	private Integer attractionLinePaxCount;

	private Integer attractionLineDay;

	private String attractionLineDate;

	private Integer attractionLineStatus;

	private Integer attractionLineCreatedBy;

	private String attractionLineCreatedDate;

	private String attractionLineCreatedDevice;

	private String attractionLineCreatedIp;

	private Integer attractionLineUpdatedBy;

	private String attractionLineUpdatedDate;

	private String attractionLineUpdatedDevice;

	private String attractionLineUpdatedIp;

	private String attractionLineAttribute1;

	private String attractionLineAttribute2;

	private String attractionLineAttribute3;

	private Integer attractionStatusId;

	private String attractionStatus;

	private List<SrAttractionsLinePaxModel> passengers;

}
