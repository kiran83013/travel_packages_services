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
@Table(name = "package_schedule_supplier_hotel")
public class PackageScheduleSupplierHotelModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "schedule_id")
	private Integer scheduleId;
	
	@Column(name = "service_request_id")
	private Integer serviceRequestId;
	
	@Column(name = "service_request_line_id")
	private Integer serviceRequestLineId;
	
	@Column(name = "check_in_day")
	private Integer checkInDay;
	
	@Column(name = "check_out_day")
	private Integer checkOutDay;
	
	@Column(name = "days_count")
	private Integer daysCount;
	
	@Column(name = "night_count")
	private Integer nightCount;
	
	@Column(name = "supplier_id")
	private Integer supplierId;
	
	@Column(name = "hotel_code")
	private String hotelCode;
	
	@Column(name = "hotel_name")
	private String hotelName;
	
	@Column(name = "hotel_address")
	private String hotelAddress;
	
	@Column(name = "supplier_hotel_status")
	private Integer supplierHotelStatus;
	
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
