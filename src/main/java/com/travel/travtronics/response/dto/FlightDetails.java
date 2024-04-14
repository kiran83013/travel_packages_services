package com.travel.travtronics.response.dto;

import java.util.Date;
import java.util.List;

import com.travel.travtronics.srm.model.HolidayRequestPax;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightDetails {
	private Long lineId;

	private String segmentBoardPoint;

	private String segmentBoardCityOrAirport;

	private String segmentDepartDate;

	private String segmentDepartTime;

	private Integer segmentDepartTerminal;

	private String segmentOffPoint;

	private String segmentArrivalCityOrAirport;

	private Date segmentArrivalDate;

	private String segmentArrivalTime;

	private Integer segmentArrivalTerminal;

	private String segmentAirlineOperating;

	private String segmentAirlineMarketing;

	private String segmentAirlineNo;

	private String segmentClassDesignator;

	private String segmentRbdCode;

	private String segmentNumOfStops;

	private Integer adtCount;

	private Integer chdCount;

	private Integer infCount;

	private Integer paxCount;

	private Boolean validateCarrier;
	private Long budgetFrom;

	private Long budgetTo;
	private List<String> flexAirLineCode;
	private List<String> flexFromCode;

	private List<String> flexToCode;

	private List<AddOnDetails> addOns;

	private List<AddOnDetails> ancillaries;

	private List<HolidayRequestPax> paxInfo;

}
