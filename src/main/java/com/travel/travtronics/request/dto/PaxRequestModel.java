package com.travel.travtronics.request.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaxRequestModel {
	@JsonIgnore
	private Long requestId;

	@JsonIgnore
	private Long requestLineId;

	private Long paxId;

	private Long statusId;
	@JsonIgnore
	private Long createdBy;

	private Date createdDate;
	@JsonIgnore
	private Long updatedBy;

	private Long requestLinePaxId;

	private Date updatedDate;

	private String firstName;

	private String lastName;

	private String email;

	private String phone;

	private Long nationality;

	private String dob;

	private String passport;

	private Long issuedCountry;

	private Long paxType;

	private Date passportIssueDate;

	private Date passportExpiredDate;

	private Boolean paxIsDeleted;

}
