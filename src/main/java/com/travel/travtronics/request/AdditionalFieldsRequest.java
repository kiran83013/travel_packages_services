package com.travel.travtronics.request;

import java.util.Date;

import com.travel.travtronics.util.Status;

import lombok.Data;

@Data
public class AdditionalFieldsRequest {

	private Long id;

	private Long headerId;

	private Integer field;

	private String description;

	private Boolean required;

	private String organizationId;

	private String info;

	private String hint;

	private Long desktopColumn;

	private String formData;

	private Long desktopOffset;

	private Long mobileColumn;

	private Long mobileOffset;

	private Long min;

	private Long max;

	private Long minLength;

	private Long maxLength;

	private Date minDate;

	private Date maxDate;

	private Boolean newRow;

	private Long isSpecialCharacters;

	private Long isPricing;

	private Long createdBy;

	private Long updatedBy;

	private Status status;

	private Integer backendColumn;

	private Integer backendOffset;

	private Long fieldOrder;

	private Boolean endStage;

	private Long stageNumber;

	private Long transitionId;

}
