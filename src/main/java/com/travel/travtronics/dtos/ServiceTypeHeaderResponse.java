package com.travel.travtronics.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceTypeHeaderResponse {

	private Long id;

	private Long createdBy;

	private String createdDate;

	private Long updatedBy;

	private String updatedDate;

	private String organizationId;

	private String departmentId;

	private String serviceTypeGroup;

	private String name;

	private Long desktopEnabled;

	private Long mobileEnabled;

	private Long isDynamicForm;

	private String formUrl;

	private String status;

	private String description;

	private String preValidations;

	private String instructions;

	private Long serviceClass;

	private Long serviceCategory;

	private Long serviceType;

	

}
