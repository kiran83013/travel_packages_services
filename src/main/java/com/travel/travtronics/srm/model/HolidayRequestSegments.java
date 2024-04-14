package com.travel.travtronics.srm.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import com.travel.travtronics.mapper.StringListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sr_package_segments")
@DynamicUpdate
@Getter
@Setter
public class HolidayRequestSegments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_segment_id")
	private Long requestSegmentId;

	@Column(name = "request_line_id")
	private Long requestLineId;

	@Column(name = "request_id")
	private Long requestId;

	@Column(name = "from_code")
	private String fromCode;

	@Column(name = "to_code")
	private String toCode;

	@Column(name = "depature_date")
	private Date depatureDate;

	@Column(name = "depature_time")
	private String depatureTime;

	@Column(name = "arrival_time")
	private String arrivalTime;

	@Column(name = "class_name")
	private String className;

	@Column(name = "rbd")
	private String rbd;

	@Column(name = "airline_code")
	private String airlineCode;

	@Column(name = "validate_carrier")
	private Boolean validateCarrier;

	@Column(name = "transit_point_code")
	@Convert(converter = StringListConverter.class)
	private List<String> transitPointCode;

	@Column(name = "flex_air_line_code")
	@Convert(converter = StringListConverter.class)
	private List<String> flexAirLineCode;

	@Column(name = "exclude_point_code")
	@Convert(converter = StringListConverter.class)
	private List<String> excludePointCode;

	@Column(name = "created_by", updatable = false)
	private Long createdBy;

	@CreationTimestamp
	@Column(name = "created_date", updatable = false)
	private Date createdDate;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "flex_class_name")
	@Convert(converter = StringListConverter.class)
	private List<String> flexClassName;

	@Column(name = "flex_depature", columnDefinition = "json")
	private List<Map<Object, Object>> flexDepature;

	@Column(name = "flex_return", columnDefinition = "json")
	private List<Map<Object, Object>> flexReturn;

	@Column(name = "return_date")
	private Date returnDate;

	@Column(name = "budget_from")
	private Long budgetFrom;

	@Column(name = "budget_to")
	private Long budgetTo;

	@Column(name = "from_airport_or_cityname")
	private String fromAirportOrCityName;

	@Column(name = "from_country_name")
	private String fromCountryName;

	@Column(name = "to_airport_or_cityname")
	private String toAirportOrCityName;

	@Column(name = "to_country_name")
	private String toCountryName;

	@Column(name = "holiday_days")
	private String holidayDays;

	@Column(name = "hotel_ratings")
	private String hotelRatings;

	@Column(name = "mode_of_transport")
	private String modeOfTransport;

	@Column(name = "team_leader")
	private String teamLeader;

	@Column(name = "line_room_count")
	private Integer lineRoomCount;

	@Column(name = "property_type")
	private String propertyType;

	@Column(name = "flex_from_code")
	@Convert(converter = StringListConverter.class)
	private List<String> flexFromCode;

	@Column(name = "flex_to_code")
	@Convert(converter = StringListConverter.class)
	private List<String> flexToCode;

}
