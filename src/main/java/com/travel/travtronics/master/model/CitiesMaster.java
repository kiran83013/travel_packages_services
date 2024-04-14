package com.travel.travtronics.master.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_cities")
@Getter
@Setter
public class CitiesMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "CityName")
	private String name;

	@Column(name = "CityCode", length = 5)
	private String code;

	@Column(name = "CityDescription")
	private String description;

	@Column(name = "GeoCityName")
	private String geoCityName;

	@Column(name = "CityNameUnique")
	private String cityNameUnique;

	@Column(name = "CountryCode", length = 5)
	private String countryCode;

	@Column(name = "CountryName")
	private String countryName;

	@Column(name = "CreatedBy")
	private Integer createdBy;

	@Column(name = "CreatedDate")
	private Date createdDate;

	@Column(name = "UpdatedBy")
	private Integer updatedBy;

	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@Column(name = "Status")
	private Boolean status;

}
