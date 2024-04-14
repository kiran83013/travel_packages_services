package com.travel.travtronics.srm.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sr_package_price_line")
public class SrPackagePriceLineModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name = "request_id")
	private Long requestId;
	
	@Column(name = "sr_package_line_id")
	private Integer srPackageLineId;
	
	@Column(name = "scheduled_id")
	private Integer scheduledId;
	
	@Column(name = "schedule_date_id")
	private Integer scheduleDateId;
	
	@Column(name = "service_type_header_id")
	private Integer serviceTypeHeaderId;
	
	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name = "total_price")
	private Double totalPrice;
	
	@Column(name = "quote_id")
	private Long quoteId;
	
	@Column(name = "adt_count")
	private Integer adtCount;
	
	@Column(name = "chd_count")
	private Integer chdCount;
	
	@Column(name = "inf_count")
	private Integer infCount;
	
	@Column(name = "chd_ages")
	private String chdAges;
	
	@Column(name = "required_date")
	private String requiredDate;
	
	@Column(name = "record_status")
	private Integer recordStatus;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "created_date")
	private String createdDate;
	
	@Column(name = "created_device")
	private String createdDevice;
	
	@Column(name = "created_ip")
	private String createdIp;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "updated_date")
	private String updatedDate;
	
	@Column(name = "updated_device")
	private String updatedDevice;
	
	@Column(name = "updated_ip")
	private String updatedIp;
	
	
	
	
}
