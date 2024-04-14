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
@Data
@Table(name = "package_configuration_header")
public class MedInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "sr_id")
	private Long srId;
	
    @Column(name = "med_name")
    private String medName;
	
    @Column(name = "med_code")
	private String medCode;

	@Column(name = "med_description", columnDefinition = "LONGTEXT")
	private String medDescription;
	
    @Column(name = "med_days")
	private Long medDays;
	
	@Column(name = "med_category")
	private Long medCategory;
	
	@Column(name = "med_type")
	private Long medType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "record_status")
	private Status recordStatus;
	
    @Column(name = "valid_from")
	private Date validFrom;
	
	@Column(name = "valid_to")
	private Date validTo;
	
	@Column(name = "created_by", updatable = false)
	private Long createdBy;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	@CreationTimestamp
	@Column(name = "created_date", updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name = "updated_date")
	private Date updatedDate;
}
