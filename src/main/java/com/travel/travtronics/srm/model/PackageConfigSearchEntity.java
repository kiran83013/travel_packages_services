package com.travel.travtronics.srm.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Immutable
@Subselect(value = "SELECT * FROM srm.`package_configuration_header`")
@Value
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Entity
public class PackageConfigSearchEntity {

	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;

	@Column(name = "sr_id", insertable = false, updatable = false)
	private Long srId;

	@Column(name = "med_name", insertable = false, updatable = false)
	private String medName;

	@Column(name = "med_code", insertable = false, updatable = false)
	private String medCode;

	@Column(name = "med_description", insertable = false, updatable = false)
	private String medDescription;

	@Column(name = "med_days", insertable = false, updatable = false)
	private Long medDays;

	@Column(name = "med_category", insertable = false, updatable = false)
	private Long medCategory;

	@Column(name = "med_type", insertable = false, updatable = false)
	private Long medType;

	@Column(name = "record_status", insertable = false, updatable = false)
	private String recordStatus;

	@Column(name = "valid_from", insertable = false, updatable = false)
	private Date validFrom;

	@Column(name = "valid_to", insertable = false, updatable = false)
	private Date validTo;

	@Column(name = "created_by", insertable = false, updatable = false)
	private Long createdBy;

	@Column(name = "updated_by", insertable = false, updatable = false)
	private Long updatedBy;

	@Column(name = "created_date", insertable = false, updatable = false)
	private Date createdDate;

	@Column(name = "updated_date", insertable = false, updatable = false)
	private Date updatedDate;

	// @NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "header_id", referencedColumnName = "id", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	@JsonManagedReference
	private Set<PrimaryTags> primaryTags = new LinkedHashSet<>();

	// @NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "header_id", referencedColumnName = "id", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Set<SecondaryTags> secondaryTags = new LinkedHashSet<>();

	// @NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "header_id", referencedColumnName = "id", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Set<FromCityImmutableEntity> fromCities = new LinkedHashSet<>();

	// @NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "header_id", referencedColumnName = "id", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Set<ToCityImmutableEntity> toCities = new LinkedHashSet<>();

}
