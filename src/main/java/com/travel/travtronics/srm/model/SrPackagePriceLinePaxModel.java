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
@Table(name = "sr_package_price_line_pax")
public class SrPackagePriceLinePaxModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name = "sr_package_price_line_id")
	private Long srPackagePriceLineId;
	
	@Column(name = "pax_id")
	private Integer paxId;
	
	@Column(name = "pax_type")
	private String paxType;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "middle_name")
	private String middleName;
	
	@Column(name = "dob")
	private String dob;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone_no")
	private String phoneNo;
	
	@Column(name = "nationality")
	private String nationality;
	
	@Column(name = "passport")
	private String passport;
	
	@Column(name = "issued_country")
	private String issuedCountry;
	
	@Column(name = "passport_issue_date")
	private String passportIssueDate;
	
	@Column(name = "passport_expire_date")
	private String passportExpireDate;
	
	@Column(name = "added_by")
	private Integer addedBy;
	
	@Column(name = "added_date")
	private String addedDate;
	
	@Column(name = "added_ip")
	private String addedIp;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "updated_date")
	private String updatedDate;
	
	@Column(name = "updated_device")
	private String updatedDevice;
	
	@Column(name = "updated_ip")
	private String updatedIp;

}
