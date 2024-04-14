package com.travel.travtronics.srm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quotes_segments_info")
public class QuotesSegmentsInfoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private Integer id;

	@Column(name = "header_id")
	private Integer headerId;

	@Column(name = "ref_no")
	private Integer refNo;

	@Column(name = "seg_from")
	private String segFrom;

	@Column(name = "seg_to")
	private String segTo;

	@Column(name = "air_line")
	private String airLine;

	@Column(name = "flight_no")
	private String flightNo;

	@Column(name = "dep_date")
	private String depDate;

	@Column(name = "dep_time")
	private String depTime;

	@Column(name = "arr_date")
	private String arrDate;

	@Column(name = "arr_time")
	private String arrTime;

	@Column(name = "cabin_class")
	private String cabinClass;

	@Column(name = "rbd")
	private String rbd;

	@Column(name = "bag")
	private String bag;

	@Column(name = "operating_carrier")
	private String operatingCarrier;

	@Column(name = "fare_rules")
	private String fareRules;

	@Column(name = "hotel_anx_name")
	private String hotelAnxName;

	@Column(name = "hotel_anx_code")
	private String hotelAnxCode;

	@Column(name = "room_or_service_name")
	private String roomOrServiceName;

	@Column(name = "room_or_service_type")
	private String roomOrServiceType;

	@Column(name = "room_or_service_no")
	private Integer roomOrServiceNo;

	@Column(name = "city")
	private String city;

	@Column(name = "address")
	private String address;

	@Column(name = "supplier_cancellation_policy")
	private String supplierCancellationPolicy;

	@Column(name = "internal_cancellation_policy")
	private String internalCancellationPolicy;

	@Column(name = "record_status")
	private Integer recordStatus;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private String createdDate;

}
