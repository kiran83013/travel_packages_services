package com.travel.travtronics.response;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.travel.travtronics.util.Status;

import lombok.Data;


@Data
public class MedInfoResponse {
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
	private Integer productId;
	private String productName;
	private List<Map<String, String>> primaryTags;
	private List<Map<String, String>> secondaryTags;
	private List<Map<String, String>> fromCities;
	private List<Map<String, String>> toCities;
	private Long createdBy;
	private Long updatedBy;
	private Date createdDate;
	private Date updatedDate;

}
