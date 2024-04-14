package com.travel.travtronics.response.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelRoomsDetails {
	private Integer roomNumber;

	private String roomName;

	private String roomType;

	private Integer roomAdultCount;

	private Integer roomChildCount;

	private Integer roomInfantCount;

	private String roomChildAges;

	private String roomInfantAges;

}
