package com.travel.travtronics.response.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HolidaySegmentResponse {

	private Long requestSegmentId;

	private Long requestLineId;

	private Long requestId;

	private String fromCode;

	private String toCode;

	private Date depatureDate;

	private String depatureTime;

	private String arrivalTime;

	private String className;

	private String rbd;

	private String airlineCode;
	private List<String> flexAirLineCode;

	private boolean validateCarrier;

	private List<String> transitPointCode;

	private List<String> excludePointCode;

	private Long createdBy;

	private Date createdDate;

	private Long updatedBy;

	private Date updatedDate;

	private List<String> flexClassName;

	private List<Map<Object, Object>> flexDepature;

	private List<Map<Object, Object>> flexReturn;

	private Date returnDate;

	private Long budgetFrom;

	private Long budgetTo;

	private String fromAirportOrCityName;

	private String fromCountryName;

	private String toAirportOrCityName;

	private String toCountryName;

	private String holidayDays;

	private String hotelRatings;

	private String modeOfTransport;

	private String teamLeader;

	private String propertyType;

	private Integer lineRoomCount;

	private String fromCity;

	private String toCity;

	private List<String> flexFromCode;

	private List<String> flexToCode;

}
