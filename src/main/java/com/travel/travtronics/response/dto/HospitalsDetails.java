package com.travel.travtronics.response.dto;

import java.util.Date;
import java.util.List;

import com.travel.travtronics.srm.model.HolidayPackagePassengersModel;
import com.travel.travtronics.srm.model.HolidayRequestPax;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalsDetails {
	private Long lineId;

	private String hospitalName;

	private String hospitalCode;

	private String roomNumber;

	private String roomName;

	private String roomType;

	private String hospitalCountryCode;

	private String hospitalCountryName;

	private String hospitalCityCode;

	private String hospitalCityName;

	private String hospitalStar;

	private String hospitalRating;

	private String hospitalAddress;

	private String hospitalDescription;

	private String hospitalPhone;

	private Date checkInDate;

	private Date checkOutDate;

	private Long budgetFrom;

	private Long budgetTo;

	private String teamLeader;

	private Integer roomsCount;

	private Integer noOfDays;

	private Integer adtCount;

	private Integer chdCount;

	private Integer infCount;

	private Integer paxCount;

	private Integer roomAdtCount;

	private Integer roomChdCount;

	private Integer roomInfCount;

	private String roomChildAges;

	private String roomInfantAges;

	private List<HospitalRoomsDetails> roomsInfo;

	private List<AddOnDetails> addOns;

	private List<AddOnDetails> ancillaries;

	private List<HolidayRequestPax> paxInfo;

	private List<HolidayPackagePassengersModel> roomPaxInfo;

}
