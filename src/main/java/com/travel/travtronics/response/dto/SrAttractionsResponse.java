package com.travel.travtronics.response.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SrAttractionsResponse {

	private Long attractionId;

	private Long attractionRequestId;

	private String attractionName;

	private String attractionDescription;

	private Integer attractionStatus;

	private Integer attractionCreatedBy;

	private String attractionCreatedDate;

	private String attractionCreatedDevice;

	private String attractionCreatedIp;

	private Integer attractionUpdatedBy;

	private String attractionUpdatedDate;

	private String attractionUpdatedDevice;

	private String attractionUpdatedIp;

	private String attractionAttribute1;

	private String attractionAttribute2;

	private String attractionAttribute3;

	private List<SrAttractionsLinesResponse> lines;

}
