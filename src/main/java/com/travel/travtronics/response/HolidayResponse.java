package com.travel.travtronics.response;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.travel.travtronics.request.dto.HolidayPaxModel;
import com.travel.travtronics.request.dto.HolidayRoomsRequest;
import com.travel.travtronics.response.dto.HolidaySegmentResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HolidayResponse {

	private String lineUuid;

	private Long requestId;

	private Long requestLineId;

	private String allianceCode;

	private String airlineCode;

	private List<Map<Object, Object>> dealCode;

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

	private List<Map<String, Object>> attractions;

	private Integer lineStatusId;

	private String lpoNumber;

	private Date lpoDate;

	private String lpoAmount;

	private List<Map<Object, Object>> exclusions;

	private List<Map<String, Object>> hotelSelectionData;

	private String hotelSelectionType;

	private List<Map<String, Object>> hospitalSelectionData;

	private String hospitalSelectionType;

	private Boolean transportFlag;

	private Boolean accomodationHotelFlag;

	private Boolean accomodationHospitalFlag;

	private HolidaySegmentResponse serviceSegments;
	private List<HolidayPaxModel> holidayPersonList;

	List<HolidayRoomsRequest> roomData;

}
