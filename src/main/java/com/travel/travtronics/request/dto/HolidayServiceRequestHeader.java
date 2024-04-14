package com.travel.travtronics.request.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HolidayServiceRequestHeader {
	private Long requestId;

	private Long passengerTypeId;

	private Integer lineStatusId;

	private Long createdBy;

	private Date createdDate;

	private Long updatedBy;

	private Date updatedDate;

	private List<Map<Object, Object>> dealCode;

	private List<String> expandableParametersCode;
}
