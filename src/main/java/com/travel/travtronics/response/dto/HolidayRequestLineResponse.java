package com.travel.travtronics.response.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HolidayRequestLineResponse {
	private String lineUuid;

	private Long requestId;

	private Long requestLineId;

	private String allianceCode;

	private String airlineCode;

	private List<Map<Object, Object>> dealCode;

	private List<Map<String, Object>> attractions;

	private List<Map<Object, Object>> exclusions;

	private List<String> expandableParametersCode;

	private Integer noofAdt;

	private Integer noofChd;

	private Integer noofInf;

	private Long passengerTypeId;

	private Long statusId;

	private Long createdBy;

	private Date createdDate;

	private Long updatedBy;

	private Date updatedDate;

	private List<Map<Object, Object>> addons;

	private Integer lineStatusId;

	private String lpoNumber;

	private Date lpoDate;

	private String lpoAmount;

	private List<Map<String, Object>> hotelSelectionData;

	private String hotelSelectionType;

	private List<Map<String, Object>> hospitalSelectionData;

	private String hospitalSelectionType;

	private Boolean transportFlag;

	private Boolean accomodationHotelFlag;

	private Boolean accomodationHospitalFlag;

}
