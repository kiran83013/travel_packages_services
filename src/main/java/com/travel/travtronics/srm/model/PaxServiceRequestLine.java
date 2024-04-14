package com.travel.travtronics.srm.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "sr_flight_pax")
@Getter
@Setter
public class PaxServiceRequestLine {

	@Column(name = "REQUESTID")
	private Long requestId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RequestLinePaxId")
	private Long requestLinePaxId;

	@Column(name = "REQUESTLINEID")
	private Long requestLineId;

	@Column(name = "PaxId")
	private Long paxId;

	@Column(name = "StatusId")
	private Long statusId;

	@Column(name = "CreatedBy", updatable = false)
	private Long createdBy;

	@CreatedDate
	@Column(name = "CreatedDate", updatable = false)
	private Date createdDate;

	@Column(name = "UpdatedBy")
	private Long updatedBy;

	@CreatedDate
	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "Email")
	private String email;

	@Column(name = "Phone")
	private String phone;

	@Column(name = "Nationality")
	private Long nationality;

	@Column(name = "Dob")
	private String dob;

	@Column(name = "Passport")
	private String passport;

	@Column(name = "IssuedCountry")
	private Long issuedCountry;

	@Column(name = "PaxType")
	private Long paxType;

	@Column(name = "PassportIssueDate")
	private Date passportIssueDate;

	@Column(name = "PassportExpiredDate")
	private Date passportExpiredDate;

	@Column(name = "PaxIsDeleted")
	private Boolean paxIsDeleted;

}
