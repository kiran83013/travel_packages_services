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
@Table(name = "sr_attractions")
@Getter
@Setter
public class SrAttractionsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attraction_id")
	private Long attractionId;

	@Column(name = "attraction_request_id")
	private Long attractionRequestId;

	@Column(name = "attraction_name")
	private String attractionName;

	@Column(name = "attraction_description")
	private String attractionDescription;

	@Column(name = "attraction_status")
	private Integer attractionStatus;

	@Column(name = "attraction_created_by")
	private Integer attractionCreatedBy;

	@Column(name = "attraction_created_date")
	private String attractionCreatedDate;

	@Column(name = "attraction_created_device")
	private String attractionCreatedDevice;

	@Column(name = "attraction_created_ip")
	private String attractionCreatedIp;

	@Column(name = "attraction_updated_by")
	private Integer attractionUpdatedBy;

	@Column(name = "attraction_updated_date")
	private String attractionUpdatedDate;

	@Column(name = "attraction_updated_device")
	private String attractionUpdatedDevice;

	@Column(name = "attraction_updated_ip")
	private String attractionUpdatedIp;

	@Column(name = "attraction_attribute_1")
	private String attractionAttribute1;

	@Column(name = "attraction_attribute_2")
	private String attractionAttribute2;

	@Column(name = "attraction_attribute_3")
	private String attractionAttribute3;

}
