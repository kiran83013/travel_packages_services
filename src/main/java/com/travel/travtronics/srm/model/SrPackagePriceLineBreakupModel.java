package com.travel.travtronics.srm.model;

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
@Table(name = "sr_package_price_line_breakup")
public class SrPackagePriceLineBreakupModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name = "sr_package_price_line_id")
	private Long srPackagePriceLineId;
	
	@Column(name = "pricing_header_id")
	private Long pricingHeaderId;
	
	@Column(name = "pricing_line_id")
	private Long pricingLineId;
	
	@Column(name = "item_id")
	private Integer itemId;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "item_code")
	private String itemCode;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "base_price")
	private Double basePrice;
	
	@Column(name = "tax_price")
	private Double taxPrice;
	
	@Column(name = "tax_breakup")
	private String taxBreakup;
	
	@Column(name = "m1")
	private Integer m1;
	
	@Column(name = "marku_info")
	private String markuInfo;
	
	@Column(name = "m2")
	private Double m2;
	
	@Column(name = "d1")
	private Double d1;
	
	@Column(name = "d2")
	private Double d2;
	
	@Column(name = "input_vat")
	private Double inputVat;
	
	@Column(name = "output_vat")
	private Double outputVat;
	
	@Column(name = "total_price")
	private Double totalPrice;
	
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
