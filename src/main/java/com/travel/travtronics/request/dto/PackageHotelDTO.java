package com.travel.travtronics.request.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackageHotelDTO {
	private HotelRequestModel requestData;

	private List<SrHotelAddonsDto> addonsData;

}
