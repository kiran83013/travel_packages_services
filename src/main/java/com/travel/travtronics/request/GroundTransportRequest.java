package com.travel.travtronics.request;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroundTransportRequest {
	private String requestLineUuid;

	@NotNull(message = "requestId cannot be null")
	private Integer requestId;

	@NotNull(message = "requestLineId cannot be null")
	private Integer requestLineId;

	private String departureCity;

	private String departureLocation;

	private String departureCountry;

	private String departureDate;

	private String arrivalCity;

	private String arrivalLocation;

	private String arrivalCountry;

	private String returnDate;

	private Integer noOfAdults;

	private Integer noOfChilds;

	private Integer noOfInfants;

	private Integer type;

	private String typeName;

	private List<Map<Object, Object>> specialRequests;

	private String vehicleType;

	private Integer createdBy;

	private LocalDateTime createdDate;

	private Integer updatedBy;

	private LocalDateTime updatedDate;

	private Integer lineStatusId;

	private String lineStatus;

	private List<GroundPaxRequest> pax;

}
