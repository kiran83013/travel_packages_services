package com.travel.travtronics.request;

import java.util.List;

import com.travel.travtronics.request.dto.HolidayServiceRequestHeader;
import com.travel.travtronics.request.dto.HolidayServiceRequestSegments;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HolidayRequest {

	private HolidayServiceRequestHeader serviceRequestLine;
	@Valid
	private List<HolidayServiceRequestSegments> serviceRequestSegment;

}
