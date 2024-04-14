package com.travel.travtronics.request;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Validated
@Getter
@Setter
public class AttractionsRequest {

	@Valid
	@NotNull
	private Long attractionRequestId;

	@Valid
	@NotEmpty
	private String attractionName;

	private String attractionDescription;

	@Valid
	@NotNull
	private Integer attractionCreatedBy;

	@Valid
	@NotEmpty
	private String attractionCreatedDevice;

	@Valid
	@NotEmpty
	private String attractionCreatedIp;

	private String attractionAttribute1;

	private String attractionAttribute2;

	private String attractionAttribute3;

	@Valid
	@NotNull
	@NotEmpty
	private List<SrAttractionsLinesRequest> lines;

}
