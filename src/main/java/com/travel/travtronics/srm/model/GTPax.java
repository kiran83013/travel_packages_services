package com.travel.travtronics.srm.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sr_ground_transport_pax")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GTPax {
	@Column(name = "request_id")
	private Long requestId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_line_pax_id")
	private Long requestLinePaxId;

	@Column(name = "request_line_id")
	private Long requestLineId;

	@Column(name = "pax_id")
	private Long paxId;

	@Column(name = "status_id")
	private Long statusId;

	@Column(name = "created_by", updatable = false)
	private Long createdBy;

	@Column(name = "created_date", updatable = false)
	private Date createdDate;

	@Column(name = "updated_by")
	private Long updatedBy;

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

	@Column(name = "dob")
	private String dob;

	@Column(name = "passport")
	private String passport;

	@Column(name = "issued_country")
	private Long issuedCountry;

	@Column(name = "pax_type")
	private Long paxType;

	@Column(name = "passport_issue_date")
	private Date passportIssueDate;

	@Column(name = "passport_expired_date")
	private Date passportExpiredDate;

	@Column(name = "pax_is_deleted")
	private Boolean paxIsDeleted;

	@Column(name = "nationality_name")
	private String nationalityName;

	@Column(name = "issued_country_name")
	private String issuedCountryName;

	@Column(name = "pax_type_name")
	private String paxTypeName;

	@Column(name = "prefix")
	private String prefix;

}
