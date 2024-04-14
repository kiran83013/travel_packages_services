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
@Table(name = "package_schedule_segment_flight")
public class PackageScheduleSegmentFlightModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "supplier_flight_id")
	private Long supplierFlightId;
	
	@Column(name = "dept_code")
	private String deptCode;
	
	@Column(name = "dept_day")
	private Integer deptDay;
	
	@Column(name = "dept_time")
	private String deptTime;
	
	@Column(name = "arr_code")
	private String arrCode;
	
	@Column(name = "arr_day")
	private Integer arrDay;
	
	@Column(name = "arr_time")
	private String arrTime;
	
	@Column(name = "marketing_carrier")
	private String marketingCarrier;
	
	@Column(name = "operating_carrier")
	private String operatingCarrier;
	
	@Column(name = "flight_number")
	private String flightNumber;
	
	@Column(name = "cabin_class")
	private String cabinClass;
	
	@Column(name = "rbd")
	private String rbd;
	
	@Column(name = "baggage")
	private String baggage;
	
	@Column(name = "equipment")
	private String equipment;
	
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
