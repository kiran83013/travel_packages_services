package com.travel.travtronics.request.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SrHospitalRoomsDto {
	private Integer id;

	private Integer roomSrId;

	private Integer roomLineId;

	private Integer roomRfqId;

	private Integer roomNumber;

	private Integer roomAdultCount;

	private Integer roomChildCount;

	private Integer roomInfantCount;

	private String roomChildAges;

	private String roomInfantAges;

	private Integer roomStatus;

	private Integer roomAddonsRequired;

	private Integer roomCreatedBy;

	private String roomCreatedDate;

	private String roomCreatedDevice;

	private String roomCreatedIp;

	private Integer roomUpdatedBy;

	private String roomUpdatedDate;

	private String roomUpdatedDevice;

	private String roomUpdatedIp;

	private String roomAttr1;

	private String roomAttr2;

	private String roomAttr3;

	private String roomAttr4;

	private String roomAttr5;

	private String roomAttr6;

	private String roomAttr7;

	private String roomAttr8;

	private String roomAttr9;

	private String roomAttr10;

	private String roomLineUuid;

	private Boolean roomIsDeleted;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<SrHospitalPassengersDto> roomPassengersInfo;

}
