package com.travel.travtronics.request.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelRequestModel {

	private SrHotelLinesDto srLine;
	private List<SrHotelRoomsDto> srRooms;

}
