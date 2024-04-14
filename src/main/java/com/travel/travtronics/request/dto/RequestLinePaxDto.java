package com.travel.travtronics.request.dto;

import java.util.List;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestLinePaxDto {

	@Valid
	private List<PaxRequestModel> paxData;

	private Long requestId;

	private Long requestLineId;

	private Long createdBy;

	private Long updatedBy;

}
