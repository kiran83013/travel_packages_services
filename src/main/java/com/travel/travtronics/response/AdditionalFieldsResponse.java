package com.travel.travtronics.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.travel.travtronics.util.Status;

import lombok.Data;

@Data
public class AdditionalFieldsResponse {

	private Long id;

	private Long headerId;

	@JsonIgnore
	private Integer field;

	private String description;

	private String organizationId;

	private Boolean required;

	private String info;

	private String hint;

	private Long desktopColumn;

	@JsonRawValue
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

	private String createdDate;

	private Long updatedBy;

	private String updatedDate;

	private Status status;

	@JsonProperty("field")
	private ConfigResponse conigModel;

	private Integer backendColumn;

	private Integer backendOffset;

	private Long fieldOrder;

	private Boolean endStage;

	private Long stageNumber;

	private Long transitionId;

	private List<DependentFieldData> dependentFields;

	private Boolean isDependentFields;
}
