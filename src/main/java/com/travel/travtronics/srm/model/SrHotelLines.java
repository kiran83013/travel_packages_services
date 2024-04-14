package com.travel.travtronics.srm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sr_hotel_lines")
@Getter
@Setter
public class SrHotelLines {
	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)

	@Id
	@Column(name = "line_uuid")
	private String lineUuid;

	@Column(name = "line_id")
	private Integer id;

	@Column(name = "line_sr_id")
	private Integer lineSrId;

	@Column(name = "line_location")
	private String lineLocation;

	@Column(name = "line_city")
	private String lineCity;

	@Column(name = "line_check_in_date")
	private String lineCheckInDate;

	@Column(name = "line_check_out_date")
	private String lineCheckOutDate;

	@Column(name = "line_no_of_nights")
	private Integer lineNoOfNights;

	@Column(name = "line_total_days")
	private Integer lineTotalDays;

	@Column(name = "line_nationality")
	private String lineNationality;

	@Column(name = "line_country")
	private String lineCountry;

	@Column(name = "line_country_residency")
	private String lineCountryResidency;

	@Column(name = "line_room_count")
	private Integer lineRoomCount;

	@Column(name = "line_hotel_name")
	private String lineHotelName;

	@Column(name = "line_property_type")
	private String linePropertyType;

	@Column(name = "line_meal_type")
	private String lineMealType;

	@Column(name = "line_ratings")
	private Integer lineRatings;

	@Column(name = "line_adult_count")
	private Integer lineAdultCount;

	@Column(name = "line_child_count")
	private Integer lineChildCount;

	@Column(name = "line_infant_count")
	private Integer lineInfantCount;

	@Column(name = "line_search_type")
	private String lineSearchType;

	@Column(name = "line_latitude")
	private String lineLatitude;

	@Column(name = "line_longitude")
	private String lineLongitude;

	@Column(name = "line_radius")
	private String lineRadius;

	public enum MarkUpType {
		A, P, B
	}

	@Column(name = "line_markup_type")
	@Enumerated(EnumType.STRING)
	private MarkUpType lineMarkUpType;

	@Column(name = "line_markup_percentage")
	private String lineMarkupPercentage;

	@Column(name = "line_markup_amount")
	private String lineMarkupAmount;

	@Column(name = "line_apis")
	private String lineApis;

	@Column(name = "line_created_by", updatable = false)
	private Integer lineCreatedBy;

	@Column(name = "line_created_date", updatable = false)
	private String lineCreatedDate;

	@Column(name = "line_created_device")
	private String lineCreatedDevice;

	@Column(name = "line_created_ip")
	private String lineCreatedIp;

	@Column(name = "line_updated_by")
	private Integer lineUpdatedBy;

	@Column(name = "line_updated_date")
	private String lineUpdatedDate;

	@Column(name = "line_updated_device")
	private String lineUpdatedDevice;

	@Column(name = "line_updated_ip")
	private String lineUpdatedIp;

	@Column(name = "line_attribute_1")
	private String lineAttr1;

	@Column(name = "line_attribute_2")
	private String lineAttr2;
	@Column(name = "line_attribute_3")
	private String lineAttr3;
	@Column(name = "line_attribute_4")
	private String lineAttr4;
	@Column(name = "line_attribute_5")
	private String lineAttr5;
	@Column(name = "line_attribute_6")
	private String lineAttr6;
	@Column(name = "line_attribute_7")
	private String lineAttr7;
	@Column(name = "line_attribute_8")
	private String lineAttr8;
	@Column(name = "line_attribute_9")
	private String lineAttr9;
	@Column(name = "line_attribute_10")
	private String lineAttr10;

	@Column(name = "line_addons_required")
	private Integer lineAddonsRequired;

	@Column(name = "line_attribute_11")
	private String lineAttr11;

	@Column(name = "line_attribute_12")
	private String lineAttr12;

	@Column(name = "line_attribute_13")
	private String lineAttr13;

	@Column(name = "line_attribute_14")
	private String lineAttr14;
	@Column(name = "line_attribute_15")
	private String lineAttr15;
	@Column(name = "line_attribute_16")
	private String lineAttr16;
	@Column(name = "line_attribute_17")
	private String lineAttr17;
	@Column(name = "line_attribute_18")
	private String lineAttr18;
	@Column(name = "line_attribute_19")
	private String lineAttr19;
	@Column(name = "line_attribute_20")
	private String lineAttr20;

	@Column(name = "lpo_number")
	private String lpoNumber;

	@Column(name = "lpo_date")
	private String lpoDate;

	@Column(name = "lpo_amount")
	private String lpoAmount;

	@Column(name = "line_status_id")
	private Integer lineStatusId;

}
