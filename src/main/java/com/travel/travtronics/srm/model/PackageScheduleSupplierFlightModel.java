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
@Table(name = "package_schedule_supplier_flight")
public class PackageScheduleSupplierFlightModel {
	
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
	
	@Column(name = "supplier_id")
	private Integer supplierId;
	
	@Column(name = "validating_carrier")
	private Integer validatingCarrier;
	
	@Column(name = "supplier_flight_status")
	private Integer supplierFlightStatus;
	
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
