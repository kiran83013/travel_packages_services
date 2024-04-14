package com.travel.travtronics.srm.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import com.travel.travtronics.mapper.StringListConverter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sr_flight_segment")

@ToString
@Getter
@Setter
public class ServiceRequestSegment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RequestSegmentId")
	private Long requestSegmentId;

	@Column(name = "REQUESTLINEID")
	private Long requestlineID;

	@Column(name = "REQUESTID")
	private Long requestID;

//	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
//	@JoinColumn(name = "REQUESTLINEID", nullable=false, referencedColumnName = "REQUESTLINEID")
//	private ServiceRequestLine serviceRequestLine;

	@Column(name = "FromCode")
	private String fromCode;

	@Column(name = "ToCode")
	private String toCode;

	@Column(name = "DepatureDate")
	private Date depatureDate;

	@Column(name = "DepatureTime")
	private String depatureTime;

	@Column(name = "ArrivalTime")
	private String arrivalTime;

	@Column(name = "ClassName")
	private String className;

	@Column(name = "Rbd")
	private String rbd;

	@Column(name = "AirlineCode")
	private String airlineCode;

	@Column(name = "ValidateCarrier")
	private boolean validateCarrier;
//	private String departureTime
//	ArrivalTime

	// @ElementCollection
	@Column(name = "NearestAirportDepartureCode")
	@Convert(converter = StringListConverter.class)
	private List<String> nearestAirportDepartureCode;

	// @ElementCollection
	@Column(name = "NearestAirportArrivalCode")
	@Convert(converter = StringListConverter.class)
	private List<String> nearestAirportArrivalCode;

	// @ElementCollection
	@Column(name = "TransitPointCode")
	@Convert(converter = StringListConverter.class)
	private List<String> transitPointCode;

	// @ElementCollection
	@Column(name = "ExcludePointCode")
	@Convert(converter = StringListConverter.class)
	private List<String> excludePointCode;

	@Column(name = "CreatedBy", updatable = false)
	private Long createdBy;

	@CreationTimestamp
	@Column(name = "CreatedDate", updatable = false)
	private Date createdDate;

	@Column(name = "UpdatedBy")
	private Long updatedBy;

	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@Column(name = "FlexClassName")
	@Convert(converter = StringListConverter.class)
	private List<String> flexClassName;

	@Column(name = "FlexFromCode")
	@Convert(converter = StringListConverter.class)
	private List<String> flexFromCode;

	@Column(name = "FlexToCode")
	@Convert(converter = StringListConverter.class)
	private List<String> flexToCode;

	@Column(name = "FlexAirLineCode")
	@Convert(converter = StringListConverter.class)
	private List<String> flexAirLineCode;

	@Column(name = "FlexStops")
	@Convert(converter = StringListConverter.class)
	private List<String> flexStops;

	@Column(name = "FlexDepature", columnDefinition = "json")
	private List<Map<Object, Object>> flexDepature;

	@Column(name = "FlexReturn", columnDefinition = "json")
	private List<Map<Object, Object>> flexReturn;

	@Column(name = "FightDirection")
	private String flightDirection;

	@Column(name = "ReturnDate")
	private Date returnDate;

	@Column(name = "BudgetFrom")
	private Long budgetFrom;

	@Column(name = "BudgetTo")
	private Long budgetTo;

	@Column(name = "FromAirportOrCityName")
	private String fromAirportOrCityName;

	@Column(name = "FromCountryName")
	private String fromCountryName;

	@Column(name = "ToAirportOrCityName")
	private String toAirportOrCityName;

	@Column(name = "ToCountryName")
	private String toCountryName;

	@Column(name = "FlightSegmentStatus")
	private String flightSegmentStatus;

}
