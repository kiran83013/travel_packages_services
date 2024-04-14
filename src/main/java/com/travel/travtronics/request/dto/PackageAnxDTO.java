package com.travel.travtronics.request.dto;

import java.util.List;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackageAnxDTO {

	private AnxRequestLine requestData;
	private RequestLinePaxDto paxData;
	private List<SrHotelAddonsDto> addonsData;

}
