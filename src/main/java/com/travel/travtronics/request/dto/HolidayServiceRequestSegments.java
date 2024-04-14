package com.travel.travtronics.request.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HolidayServiceRequestSegments {

	private Long requestSegmentId;

	private Long requestId;

	private Long requestLineId;

	private Integer lineRoomCount;

	private String propertyType;

	private String airlineCode;

	private List<Map<String, Object>> attractions;

	private String lineUuid;

	private String fromAirportOrCityName;

	private String fromCountryName;

	private String toAirportOrCityName;

	private String toCountryName;

	private String className;

	private List<String> flexClassName;

	private String rbd;

	private Date depatureDate;

	private List<Map<Object, Object>> flexDepature;

	private List<Map<Object, Object>> flexReturn;

	private Date returnDate;

	private Boolean validateCarrier;

	@Valid
	private List<HolidayRoomsRequest> roomsData;

	@Valid
	private List<HolidayPaxModel> holidayPersonList;

	private List<Map<String, Object>> hotelSelectionData;

	private String hotelSelectionType;

	private List<Map<String, Object>> hospitalSelectionData;

	private String hospitalSelectionType;

	private Boolean transportFlag;

	private Boolean accomodationHotelFlag;

	private Boolean accomodationHospitalFlag;

	private List<String> flexAirLineCode;

	private List<String> flexFromCode;

	private List<String> flexToCode;

	private Long budgetFrom;

	private Long budgetTo;

	private List<String> transitPointCode;

	private List<String> excludePointCode;

	private Long createdBy;

	private Date createdDate;

	private String teamLeader;

	private String fromCode;

	private String toCode;

	private String holidayDays;

	private String hotelRatings;

	private String modeOfTransport;

	private Integer noofAdt;

	private Integer noofChd;

	private Integer noofInf;

	private String lpoNumber;

	private Date lpoDate;

	private String lpoAmount;

	private List<Map<Object, Object>> addons;

	/*
	 * private String depatureTime;
	 * 
	 * private String arrivalTime;
	 * 
	 * private String airlineCode;
	 */

	private Long updatedBy;

	private Date updatedDate;

	private List<Map<Object, Object>> exclusions;

}
