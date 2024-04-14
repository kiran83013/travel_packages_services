package com.travel.travtronics.srm.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;

import com.travel.travtronics.mapper.MapListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sr_ground_transport_line")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class GTLine {

	@Id
	@Column(name = "request_line_uuid")
	private String requestLineUuid;

	@Column(name = "request_id")
	private Integer requestId;

	@Column(name = "request_line_id")
	private Integer requestLineId;

	@Column(name = "departure_city")
	private String departureCity;

	@Column(name = "departure_location")
	private String departureLocation;

	@Column(name = "departure_country")
	private String departureCountry;

	@Column(name = "departure_date")
	private String departureDate;

	@Column(name = "arrival_city")
	private String arrivalCity;

	@Column(name = "arrival_location")
	private String arrivalLocation;

	@Column(name = "arrival_country")
	private String arrivalCountry;

	@Column(name = "return_date")
	private String returnDate;

	@Column(name = "number_of_adults")
	private Integer noOfAdults;

	@Column(name = "number_of_children")
	private Integer noOfChilds;

	@Column(name = "number_of_infants")
	private Integer noOfInfants;

	@Column(name = "ground_tranport_type")
	private Integer type;

	@Column(name = "ground_tranport_name")
	private String typeName;

	@Column(name = "special_requests", columnDefinition = "JSON")
	@Convert(converter = MapListConverter.class)
	private List<Map<Object, Object>> specialRequests;

	@Column(name = "vehicle_type")
	private String vehicleType;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "line_status_id")
	private Integer lineStatusId;

	@CreatedDate
	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private LocalDateTime updatedDate;

}
