package com.travel.travtronics.srm.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "package_tags")
public class PackageTags {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "category")
	private Long category;
	
	@Column(name = "subCategory")
	private Long subCategory;
	
	@Column(name = "type")
	private Long type;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "createdBy")
	private Long createdBy;
	
	@CreationTimestamp
	@Column(name = "createdDate")
	private Date createdDate;
	
	@Column(name = "updatedBy")
	private Long updatedBy;
	
	@UpdateTimestamp
	@Column(name = "updatedDate")
	private Date updatedDate;
}
