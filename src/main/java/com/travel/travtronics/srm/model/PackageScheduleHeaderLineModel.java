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
@Table(name = "package_schedule_header_line")
public class PackageScheduleHeaderLineModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "header_id")
	private Long headerId;
	
	@Column(name = "line_group_id")
	private String lineGroupId;
	
	@Column(name = "line_option_id")
	private Integer lineOptionId;
	
	@Column(name = "default_option")
	private Integer defaultOption;
	
	@Column(name = "line_status")
	private Integer lineStatus;
	
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
