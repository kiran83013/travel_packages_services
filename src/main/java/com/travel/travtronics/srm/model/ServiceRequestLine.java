package com.travel.travtronics.srm.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;

import com.travel.travtronics.mapper.MapListConverter;
import com.travel.travtronics.mapper.StringListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sr_flight_line")

@Getter
@Setter
@ToString
public class ServiceRequestLine {

	@Id
	@Column(name = "LineUuid")
	private String lineUuid;

	@Column(name = "REQUESTID")
	private Long requestId;

	@Column(name = "REQUESTLINEID")
	private Long requestLineId;

	@Column(name = "TripTypeId")
	private Long tripTypeId;

	@Column(name = "NoofADT")
	private Integer noofADT;

	@Column(name = "NoofCHD")
	private Integer noofCHD;

	@Column(name = "NoofINF")
	private Integer noofINF;

	@Column(name = "AllianceCode")
	private String allianceCode;

	@Column(name = "StopOverCode")
	private String stopOverCode;

	// @ElementCollection
	@Column(name = "ExpandableParametersCode")
	@Convert(converter = StringListConverter.class)
	private List<String> expandableParametersCode;

	@Column(name = "PassengerTypeId")
	private Long passengerTypeId;

	@Column(name = "AirlineCode")
	private String airlineCode;

	@Column(name = "DealCode", columnDefinition = "JSON")
	private List<Map<Object, Object>> dealCode;

	@Column(name = "StatusId")
	private Long statusId;

	@Column(name = "isSuggestionTemplate")
	private Integer isSuggestionTemplate;

	@Column(name = "CreatedBy", updatable = false)
	private Long createdBy;

	@CreationTimestamp
	@Column(name = "CreatedDate", updatable = false)
	private Date createdDate;

	@Column(name = "UpdatedBy")
	private Long updatedBy;

	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@Column(name = "TypeOfFlight")
	private String typeOfFlight;

	@Column(name = "ConnectingDetails")
	private String connectingDetails;

	@Column(name = "FlexStops")
	@Convert(converter = StringListConverter.class)
	private List<String> flexStops;

	// )
	@Column(name = "AddOns", columnDefinition = "JSON")
	@Convert(converter = MapListConverter.class)
	private List<Map<Object, Object>> addons;

	@Column(name = "LineNo", updatable = false)
	private Integer lineNo;

	@Column(name = "LineStatusId")
	private Integer lineStatusId;

	@Column(name = "LpoNumber")
	private String lpoNumber;

	@Column(name = "LPoDate")
	private Date lPoDate;

	@Column(name = "LpoAmount")
	private String lpoAmount;

}
