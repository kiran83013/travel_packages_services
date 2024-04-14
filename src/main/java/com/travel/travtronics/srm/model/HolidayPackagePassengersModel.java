package com.travel.travtronics.srm.model;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sr_package_room_passengers")
@DynamicUpdate
@Getter
@Setter
public class HolidayPackagePassengersModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "passenger_id")
	private Integer id;

	@Column(name = "passenger_addons_required")
	private Integer passengerAddonsRequired;

	@Column(name = "passenger_attribute_1")
	private String passengerAttr1;
	@Column(name = "passenger_attribute_2")
	private String passengerAttr2;
	@Column(name = "passenger_attribute_3")
	private String passengerAttr3;
	@Column(name = "passenger_attribute_4")
	private String passengerAttr4;
	@Column(name = "passenger_attribute_5")
	private String passengerAttr5;
	@Column(name = "passenger_attribute_6")
	private String passengerAttr6;
	@Column(name = "passenger_attribute_7")
	private String passengerAttr7;
	@Column(name = "passenger_attribute_8")
	private String passengerAttr8;
	@Column(name = "passenger_attribute_9")
	private String passengerAttr9;
	@Column(name = "passenger_attribute_10")
	private String passengerAttr10;

	@Column(name = "passenger_coutry")
	private Integer passengerCountry;

	@Column(name = "passenger_country_residency")
	private Integer passengerCountryResidency;

	@Column(name = "passenger_created_by", updatable = false)
	private Integer passengerCreatedBy;

	@Column(name = "passenger_created_date", updatable = false)
	private String passengerCreatedDate;

	@Column(name = "passenger_created_device", updatable = false)
	private String passengerCreatedDevice;

	@Column(name = "passenger_created_ip", updatable = false)
	private String passengerCreatedIp;

	@Column(name = "passenger_first_name")
	private String passengerFirstName;

	@Column(name = "passenger_middle_name")
	private String passengerMiddleName;

	@Column(name = "passenger_last_name")
	private String passengerLastName;

	@Column(name = "passenger_email")
	private String passengerEmail;

	@Column(name = "passenger_delete_flag")
	private Boolean passengerIsDeleted;

	@Column(name = "passenger_nationality")
	private Integer passengerNationality;

	@Column(name = "passenger_pax_id")
	private Integer passengerPaxId;

	@Column(name = "passenger_phone")
	private String passengerPhone;

	@Column(name = "passenger_room_id")
	private Integer passengerRoomId;

	@Column(name = "passenger_line_id")
	private Integer passengerLineId;

	@Column(name = "passenger_sr_id")
	private Integer passengerSrId;

	@Column(name = "passenger_status")
	private Integer passengerStatus;

	@Column(name = "passenger_title")
	private Integer passengerTitle;

	@Column(name = "passenger_type")
	private Integer passengerType;
	@Column(name = "passenger_updated_by")
	private Integer passengerUpdatedBy;

	@Column(name = "passenger_updated_date")
	private String passengerUpdatedDate;

	@Column(name = "passenger_updated_device")
	private String passengerUpdatedDevice;

	@Column(name = "passenger_updated_ip")
	private String passengerUpdatedIp;
}
