package com.travel.travtronics.srm.model;

import com.travel.travtronics.util.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "package_configuration_images")
@Getter
@Setter
public class PackageConfigurationImages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "package_id")
	private Long packageId;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "image_order")
	private int imageOrder;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "record_status")
	private Status recordStatus;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private String createdDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private String updatedDate;

}
