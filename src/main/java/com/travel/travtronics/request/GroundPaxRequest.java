package com.travel.travtronics.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroundPaxRequest {

	private Long requestId;

	private Long requestLineId;

	private Long paxId;

	private Long statusId;

	private Long createdBy;

	private Date createdDate;

	private Long updatedBy;

	@Builder.Default
	private Long requestLinePaxId = 0L;

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

	private String nationalityName;

	private String issuedCountryName;

	private String paxTypeName;

	private String prefix;

}
