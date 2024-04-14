package com.travel.travtronics.srm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer_contact")
public class CustomerContactInfoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "PaxId")
	private Integer paxId;
	
	@Column(name = "FirstName")
	private String firstName;
	
	@Column(name = "LastName")
	private String lastName;
	
	@Column(name = "CustomerId")
	private Integer customerId;
	
	@Column(name = "PrimaryEmail")
	private String primaryEmail;
	
	@Column(name = "PrimaryPhoneNumber")
	private String primaryPhoneNumber;
	
	@Column(name = "SecondaryEmail")
	private String secondaryEmail;
	
	@Column(name = "SecondaryPhoneNumber")
	private String secondaryPhoneNumber;

	
	
}
