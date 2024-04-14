package com.travel.travtronics.request.dto;

import java.util.List;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalRequestModel {

	private SrHospitalLinesDto srLine;
	private List<SrHospitalRoomsDto> srRooms;

}
