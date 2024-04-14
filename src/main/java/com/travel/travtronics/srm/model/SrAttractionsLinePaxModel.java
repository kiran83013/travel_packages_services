package com.travel.travtronics.srm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sr_attraction_line_pax")
@Getter
@Setter
public class SrAttractionsLinePaxModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attraction_line_passenger_id")
	private Long attractionLinePassengerId;

	@Column(name = "attraction_line_id")
	private Long attractionLineId;

	@Column(name = "attraction_line_pax_id")
	private Integer attractionLinePaxId;

	@Column(name = "attraction_line_passenger_type")
	private String attractionLinePassengerType;

	@Column(name = "attraction_line_passenger_title")
	private String attractionLinePassengerTitle;

	@Column(name = "attraction_line_passenger_frist_name")
	private String attractionLinePassengerFristName;

	@Column(name = "attraction_line_passenger_middle_name")
	private String attractionLinePassengerMiddleName;

	@Column(name = "attraction_line_passenger_last_name")
	private String attractionLinePassengerLastName;

	@Column(name = "attraction_line_passenger_dob")
	private String attractionLinePassengerDob;

	@Column(name = "attraction_line_passenger_gender")
	private String attractionLinePassengerGender;

	@Column(name = "attraction_line_passenger_phone")
	private String attractionLinePassengerPhone;

	@Column(name = "attraction_line_passenger_email")
	private String attractionLinePassengerEmail;

	@Column(name = "attraction_line_passenger_status")
	private Integer attractionLinePassengerStatus;

	@Column(name = "attraction_line_passenger_created_by")
	private Integer attractionLinePassengerCreatedBy;

	@Column(name = "attraction_line_passenger_created_date")
	private String attractionLinePassengerCreatedDate;

	@Column(name = "attraction_line_passenger_created_device")
	private String attractionLinePassengerCreatedDevice;

	@Column(name = "attraction_line_passenger_created_ip")
	private String attractionLinePassengerCreatedIp;

	@Column(name = "attraction_line_passenger_updated_by")
	private Integer attractionLinePassengerUpdatedBy;

	@Column(name = "attraction_line_passenger_updated_date")
	private String attractionLinePassengerUpdatedDate;

	@Column(name = "attraction_line_passenger_updated_device")
	private String attractionLinePassengerUpdatedDevice;

	@Column(name = "attraction_line_passenger_updated_ip")
	private String attractionLinePassengerUpdatedIp;

	@Column(name = "attraction_line_passenger_attribute_1")
	private String attractionLinePassengerAttribute1;

	@Column(name = "attraction_line_passenger_attribute_2")
	private String attractionLinePassengerAttribute2;

	@Column(name = "attraction_line_passenger_attribute_3")
	private String attractionLinePassengerAttribute3;

}
