package com.travel.travtronics.srm.model;

import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.travel.travtronics.mapper.StringListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sr_package_rooms")
@DynamicUpdate
@Getter
@Setter
public class HolidayPackageRoomsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id")
	private Integer id;

	@Convert(converter = StringListConverter.class)
	@Column(name = "room_names")
	private List<String> roomName;

	@Convert(converter = StringListConverter.class)
	@Column(name = "room_types")
	private List<String> roomType;

	@Column(name = "room_addons_required")
	private Integer roomAddonsRequired;

	@Column(name = "room_adult_count")
	private Integer roomAdultCount;

	@Column(name = "room_attribute_1")
	private String roomAttr1;
	@Column(name = "room_attribute_2")
	private String roomAttr2;
	@Column(name = "room_attribute_3")
	private String roomAttr3;
	@Column(name = "room_attribute_4")
	private String roomAttr4;
	@Column(name = "room_attribute_5")
	private String roomAttr5;
	@Column(name = "room_attribute_6")
	private String roomAttr6;
	@Column(name = "room_attribute_7")
	private String roomAttr7;
	@Column(name = "room_attribute_8")
	private String roomAttr8;
	@Column(name = "room_attribute_9")
	private String roomAttr9;
	@Column(name = "room_attribute_10")
	private String roomAttr10;

	@Column(name = "room_child_ages")
	private String roomChildAges;

	@Column(name = "room_child_count")
	private Integer roomChildCount;

	@Column(name = "room_infant_ages")
	private String roomInfantAges;

	@Column(name = "room_infant_count")
	private Integer roomInfantCount;

	@Column(name = "room_delete_flag")
	private Boolean roomIsDeleted;

	@Column(name = "room_created_by", updatable = false)
	private Integer roomCreatedBy;

	@Column(name = "room_created_date", updatable = false)
	private String roomCreatedDate;

	@Column(name = "room_created_device", updatable = false)
	private String roomCreatedDevice;

	@Column(name = "room_created_ip", updatable = false)
	private String roomCreatedIp;

	@Column(name = "room_updated_by")
	private Integer roomUpdatedBy;

	@Column(name = "room_updated_date")
	private String roomUpdatedDate;

	@Column(name = "room_updated_device")
	private String roomUpdatedDevice;

	@Column(name = "room_updated_ip")
	private String roomUpdatedIp;

	@Column(name = "room_line_id")
	private Integer roomLineId;

	@Column(name = "room_number")
	private Integer roomNumber;

	@Column(name = "room_sr_id")
	private Integer roomSrId;

	@Column(name = "room_status")
	private Integer roomStatus;

}
