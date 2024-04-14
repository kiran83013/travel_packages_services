package com.travel.travtronics.request.dto;

import java.util.Date;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnxRequestLine {

	private Integer anxLineRequestId;
	
	private Integer anxLineTypeId;
	
	private String anxLineType;
	
	private Integer anxLineAdtCount;
	
	private Integer anxLineChdCount;
	
	private Integer anxLineInfCount;
	

	private Map<String, Object> anxLineJson;
	
	
	private Map<String, Object> anxLineAddons;
	
	private Integer anxLineStatus;
	
	private Integer loggedInUserId;
	
	private String deviceInfo;
	
	private String deviceIp;
	
	private String anxLineLpoNumber;
	
	private Date anxLineLpoDate;
	
	private double anxLineLpoAmount;
	
	private Integer statusId;
	
	private Integer teamId;
	
	private Integer wiwId;
	
	private Integer submitedBy;
	
	private Date submitedDate;
	
	private String anxLineAttr1;
	
	private String anxLineAttr2;
	
	private String anxLineAttr3;
	
	private String anxLineAttr4;
	
	private String anxLineAttr5;
	
	private String anxLineAttr6;
	
	private Integer ancillaryLineStatusId;
	

	
	
	
}
