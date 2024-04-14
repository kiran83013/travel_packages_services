package com.travel.travtronics.srm.model;

import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sr_package_pax")
@DynamicUpdate
@Getter
@Setter
public class HolidayRequestPax {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_line_pax_id")
	private Long requestLinePaxId;

	@Column(name = "request_id")
	private Long requestId;

	@Column(name = "request_line_id")
	private Long requestLineId;

	@Column(name = "pax_id")
	private Long paxId;

	@Column(name = "status_id")
	private Long statusId;

	@Column(name = "created_by", updatable = false)
	private Long createdBy;

	@CreatedDate
	@Column(name = "created_date", updatable = false)
	private Date createdDate;

	@Column(name = "updated_by")
	private Long updatedBy;

	@CreatedDate
	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "nationality")
	private Long nationality;

	@Column(name = "nationality_name")
	private String nationlaityName;

	@Column(name = "dob")
	private String dob;

	@Column(name = "passport")
	private String passport;

	@Column(name = "issued_country")
	private Long issuedCountry;

	@Column(name = "issued_country_name")
	private String issuedCountryName;

	@Column(name = "pax_type")
	private Long paxType;

	@Column(name = "pax_code")
	private String paxCode;

	@Column(name = "passport_issue_date")
	private Date passportIssueDate;

	@Column(name = "passport_expired_date")
	private Date passportExpiredDate;

	@Column(name = "pax_is_deleted")
	private Boolean paxIsDeleted;

	@Column(name = "prefix")
	private String prefix;

	@Column(name = "assign")
	private Integer assign;

}
