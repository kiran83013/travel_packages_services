package com.travel.travtronics.srm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "package_schedule_sub_hotel")
public class PackageScheduleSubHotelModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "option_id")
	private Long optionId;
	
	@Column(name = "room_name_id")
	private Integer roomNameId;
	
	@Column(name = "room_name")
	private String roomName;
	
	@Column(name = "room_type_id")
	private Integer roomTypeId;
	
	@Column(name = "room_type")
	private String roomType;
	
	@Column(name = "max_adt")
	private Integer maxAdt;
	
	@Column(name = "max_chd")
	private Integer maxChd;
	
	@Column(name = "max_inf")
	private Integer maxInf;
	
	@Column(name = "sub_status")
	private Integer subStatus;
	
	@Column(name = "added_by")
	private Integer addedBy;
	
	@Column(name = "added_date")
	private String addedDate;
	
	@Column(name = "added_device")
	private String addedDevice;
	
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
