package com.travel.travtronics.request.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MedInfoSearchParametersDTO {

	private String medName;
	private Long medCategory;
	private Long medType;
	private Date validFrom;
	private Date validTo;
	private Long createdBy;
	private Long updatedBy;
	private String recordStatus;
	private String primaryTags;
	private String secondaryTags;
	private String fromCityCodes;
	private String toCityCodes;
}