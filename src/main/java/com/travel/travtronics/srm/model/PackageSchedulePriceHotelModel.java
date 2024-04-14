package com.travel.travtronics.srm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "package_schedule_price_hotel")
public class PackageSchedulePriceHotelModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "sub_option_id")
	private Long subOptionId;
	
	@Column(name = "price_status")
	private Integer priceStatus;
	
	@Column(name = "adt_count")
	private Integer adtCount;
	
	@Column(name = "chd_count")
	private Integer chdCount;
	
	@Column(name = "inf_count")
	private Integer infCount;
	
	@Column(name = "base")
	private Double base;
	
	@Column(name = "tax")
	private Double tax;
	
	@Column(name = "tax_break_up")
	private String taxBreakUp;
	
	@Column(name = "m1")
	private Double m1;
	
	@Column(name = "d1")
	private Double d1;
	
	@Column(name = "m2")
	private Double m2;
	
	@Column(name = "d2")
	private Double d2;
	
	@Column(name = "output_vat")
	private Double outputVat;
	
	@Column(name = "input_vat")
	private Double inputVat;
	
	@Column(name = "total")
	private Double total;
	
	@Column(name = "currency_code")
	private String currencyCode;
	
	@Column(name = "added_by")
	private Integer addedBy;
	
	@Column(name = "added_date")
	private String addedDate;
	
	@Column(name = "added_device")
	private String addedDevice;
	
	@Column(name = "added_ip")
	private String addedIp;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "updated_date")
	private String updatedDate;
	
	@Column(name = "updated_device")
	private String updatedDevice;
	
	@Column(name = "updated_ip")
	private String updatedIp;

}
