package com.travel.travtronics.request.dto;

import java.util.List;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HolidayRoomsRequest {

	private Integer id;
	private List<String> roomName;

	private List<String> roomType;

	private Integer roomAddonsRequired;

	private Integer roomAdultCount;

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

	private String roomChildAges;

	private Integer roomChildCount;

	private String roomInfantAges;

	private Integer roomInfantCount;

	private Boolean roomIsDeleted;

	private Integer roomCreatedBy;

	private String roomCreatedDate;

	private String roomCreatedDevice;

	private String roomCreatedIp;

	private Integer roomUpdatedBy;

	private String roomUpdatedDate;

	private String roomUpdatedDevice;

	private String roomUpdatedIp;

	private Integer roomLineId;

	private Integer roomNumber;

	private Integer roomRfqId;

	private Integer roomSrId;

	private Integer roomStatus;

	@Valid
	private List<HolidayPassengersRequest> roomPassengersInfo;

}
