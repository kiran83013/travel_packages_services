package com.travel.travtronics.request;

import java.util.Date;

import com.travel.travtronics.util.Status;

import lombok.Data;

@Data
public class MedInfoRequest {

	private Long id;
	
	private Long srId;
	
	private String medName;

	private String medCode;

	private String medDescription;

	private Long medDays;

	private Long medCategory;

	private Long medType;

	private Status recordStatus;

	private Date validFrom;

	private Date validTo;

	private Long createdBy;

	private Long updatedBy;
	
	private Date createdDate;
	
	private Date updatedDate;
	
	private String primaryTags;

	private String secondaryTags;

	private String fromCities;

	private String toCities;
}

