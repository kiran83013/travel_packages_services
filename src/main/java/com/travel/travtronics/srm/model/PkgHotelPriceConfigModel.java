package com.travel.travtronics.srm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pkg_hotel_price_config")
public class PkgHotelPriceConfigModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "supplier")
	private String supplier;
	
	@Column(name = "hotel")
	private String hotel;
	
	@Column(name = "room_name")
	private String roomName;
	
	@Column(name = "room_type")
	private String roomType;
	
	@Column(name = "maximum_adults_allowed")
	private Integer maximumAdultsAllowed;
	
	@Column(name = "maximum_children_allowed")
	private Integer maximumChildrenAllowed;
	
	@Column(name = "price_per_night")
	private Integer pricePerNight;

}
