package com.travel.travtronics.request.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HolidayPaxModel {

	private Long paxId;

	private String prefix;

	private String firstName;

	private String lastName;

	private String email;

	private String phone;

	private String dob;

	private Long nationality;

	private String nationlaityName;

	private Long issuedCountry;

	private String issuedCountryName;

	private String passport;

	private Long paxType;

	private String paxCode;

	private Date passportIssueDate;

	private Date passportExpiredDate;

	private Boolean paxIsDeleted;

	private Long requestId;

	private Long requestLinePaxId;

	private Long requestLineId;

	private Long statusId;

	private Long createdBy;

	private Date createdDate;

	private Long updatedBy;

	private Date updatedDate;

	private Integer assign;

}
