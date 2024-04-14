package com.travel.travtronics.srm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "sr_ancillary_addons")
@DynamicUpdate
@Getter
@Setter
public class SrAnxAddons {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "addon_id")
	private Integer id;

	@Column(name = "addon_sr_id")
	private Integer addonSrId;

	@Column(name = "addon_line_id")
	private Integer addonLineId;

	@Column(name = "addon_passenger_id")
	private String addonPassengerId;

	@Column(name = "addon_room_id")
	private String addonRoomId;

	@Column(name = "addon_status")
	private Integer addonStatus;

	@Column(name = "addon_delete_flag")
	private Boolean addonIsDeleted;

	@Column(name = "addon_type_id")
	private Integer addonTypeId;

	@Column(name = "addon_type")
	private String addonType;

	@Column(name = "addon_sub_type_id")
	private Integer addonSubTypeId;

	@Column(name = "addon_sub_type")
	private String addonSubType;

	@Column(name = "addon_title")
	private String addonTitle;

	@Column(name = "addon_description")
	private String addonDescription;

	@Column(name = "addon_extra_cost")
	private Integer addonExtraCost;

	@Column(name = "addon_created_by", updatable = false)
	private Integer addonCreatedBy;

	@Column(name = "addon_created_date", updatable = false)
	private String addonCreatedDate;

	@Column(name = "addon_created_device", updatable = false)
	private String addonCreatedDevice;

	@Column(name = "addon_created_ip", updatable = false)
	private String addonCreatedIp;

	@Column(name = "addon_updated_by")
	private Integer addonUpdatedBy;

	@Column(name = "addon_updated_date")
	private String addonUpdatedDate;

	@Column(name = "addon_updated_device")
	private String addonUpdatedDevice;

	@Column(name = "addon_updated_ip")
	private String addonUpdatedIp;

	@Column(name = "addon_attribute_1")
	private String addonAttr1;

	@Column(name = "addon_attribute_2")
	private String addonAttr2;

	@Column(name = "addon_attribute_3")
	private String addonAttr3;

	@Column(name = "addon_attribute_4")
	private String addonAttr4;

	@Column(name = "addon_attribute_5")
	private String addonAttr5;

	@Column(name = "addon_attribute_6")
	private String addonAttr6;

	@Column(name = "addon_attribute_7")
	private String addonAttr7;

	@Column(name = "addon_attribute_8")
	private String addonAttr8;

	@Column(name = "addon_attribute_9")
	private String addonAttr9;

	@Column(name = "addon_attribute_10")
	private String addonAttr10;

	@Column(name = "addon_nights")
	private String addonNights;

	@Column(name = "addon_passengers")
	private String addonPassengers;

	@Column(name = "addon_count")
	private Integer addonCount;

	@Column(name = "addon_with_booking")
	private Integer addonWithBooking;

	@Column(name = "addon_required")
	private Integer addonRequired;

	@Column(name = "addon_remarks")
	private String addonRemarks;

}
