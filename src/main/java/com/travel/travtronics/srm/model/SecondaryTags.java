package com.travel.travtronics.srm.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.travel.travtronics.util.Status;

import lombok.Data;

@Entity
@Table(name = "package_config_secondary_tags")
@Data
public class SecondaryTags {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "header_id")
	private Long headerId;
	
	@Column(name = "tag_id")
	private Long tagId;
	
	@Column(name = "tag_name")
	private String tagName;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "record_status")
	private Status recordStatus;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	@CreationTimestamp
	@Column(name = "created_date")
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name = "updated_date")
	private Date updatedDate;
}
