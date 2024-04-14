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
@Table(name = "sr_attraction_lines")
@Getter
@Setter
public class SrAttractionsLinesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attraction_line_id")
	private Long attractionLineId;

	@Column(name = "attraction_header_id")
	private Long attractionHeaderId;

	@Column(name = "attraction_request_id")
	private Long attractionRequestId;

	@Column(name = "attraction_id")
	private Integer attractionId;

	@Column(name = "attraction_line_name")
	private String attractionLineName;

	@Column(name = "attraction_line_city")
	private String attractionLineCity;

	@Column(name = "attraction_line_location")
	private String attractionLineLocation;

	@Column(name = "attraction_line_country")
	private String attractionLineCountry;

	@Column(name = "attraction_line_pax_count")
	private Integer attractionLinePaxCount;

	@Column(name = "attraction_line_day")
	private Integer attractionLineDay;

	@Column(name = "attraction_line_date")
	private String attractionLineDate;

	@Column(name = "attraction_line_status")
	private Integer attractionLineStatus;

	@Column(name = "attraction_line_created_by")
	private Integer attractionLineCreatedBy;

	@Column(name = "attraction_line_created_date")
	private String attractionLineCreatedDate;

	@Column(name = "attraction_line_created_device")
	private String attractionLineCreatedDevice;

	@Column(name = "attraction_line_created_ip")
	private String attractionLineCreatedIp;

	@Column(name = "attraction_line_updated_by")
	private Integer attractionLineUpdatedBy;

	@Column(name = "attraction_line_updated_date")
	private String attractionLineUpdatedDate;

	@Column(name = "attraction_line_updated_device")
	private String attractionLineUpdatedDevice;

	@Column(name = "attraction_line_updated_ip")
	private String attractionLineUpdatedIp;

	@Column(name = "attraction_line_attribute_1")
	private String attractionLineAttribute1;

	@Column(name = "attraction_line_attribute_2")
	private String attractionLineAttribute2;

	@Column(name = "attraction_line_attribute_3")
	private String attractionLineAttribute3;

	@Column(name = "attraction_line_status_id")
	private Integer attractionStatusId;

}
