package com.travel.travtronics.response;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeildLinesResponseDto {

	private FeildUiResponse ui;
	private String name;

	private String type;

	private List<Map<String, Object>> source;

	private String status;

	private Integer typeId;

	private String options;

	private String service;

	private String category;

	private Integer configId;

	private Integer createdBy;

	private Integer isPricing;

	private Integer updatedBy;

	private Map<String, Object> validators;

	private String createdDate;

	private String updatedDate;

	private String defaultValue;

	private String dependentField;

	private Integer organizationId;

	private Long feildOrder;

	private String serviceUrl;

	private Boolean isExternalUrl;

	private List<DependentFieldData> dependentFields;

	private Boolean isDependentFields;

	private List<Object> formInput;
}
