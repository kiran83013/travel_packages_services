package com.travel.travtronics.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonRawValue;

import lombok.Data;

@Data
public class AddSrAdditionalData {

	@JsonRawValue
	private String addlData;

	private Date submittedDate;

	private Long submittedBy;

	private String submittedDevice;

	private String submittedIP;

	private Date browserSubmittedDate;
}