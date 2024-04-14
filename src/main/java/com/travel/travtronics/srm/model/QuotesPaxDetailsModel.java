package com.travel.travtronics.srm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quotes_pax_details")
public class QuotesPaxDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "header_id")
	private Integer headerId;

	@Column(name = "pax_type")
	private String paxType;

	@Column(name = "pax_title")
	private String paxTitle;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "is_lead")
	private Integer lead;

	@Column(name = "dob")
	private String dob;

	@Column(name = "mail_id")
	private String mailId;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "passport_no")
	private String passportNo;

	@Column(name = "passport_issued_date")
	private String passportIssuedDate;

	@Column(name = "passport_expire_date")
	private String passportExpireDate;

	@Column(name = "room_no")
	private Integer roomNo;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private String createdDate;

	@Column(name = "record_status")
	private Integer recordStatus;
}
