package com.travel.travtronics.request;

import java.util.Date;

import lombok.Data;

@Data
public class PackageTagsRequest {

	private Long id;

	private String name;

	private String code;

	private String description;

	private Long category;

	private Long subCategory;

	private Long type;

	private Boolean status;

	private Long createdBy;

	private Date createdDate;

	private Long updatedBy;

	private Date updatedDate;
	
}
