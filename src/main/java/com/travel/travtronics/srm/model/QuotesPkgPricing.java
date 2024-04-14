package com.travel.travtronics.srm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quotes_pkg_pricing")
public class QuotesPkgPricing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "quote_header_id")
	private Integer quoteHeaderId;

	@Column(name = "service_type_header_id")
	private Integer serviceTypeHeaderId;
	
	@Column(name = "pricing_header_id")
	private Integer pricingHeaderId;

	@Column(name = "pricing_line_id")
	private Integer pricingLineId;

	@Column(name = "item_id")
	private Integer itemId;

	@Column(name = "item_name")
	private String itemName;

	@Column(name = "item_code")
	private String itemCode;

	private Integer quantity;

	@Column(name = "base")
	private Double base;

	@Column(name = "tax")
	private Double tax;

	@Column(name = "tax_breakup")
	private String taxBreakup;

	@Column(name = "m1")
	private Double m1;

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

	@Column(name = "added_ip")
	private String addedIp;

	@Column(name = "added_device")
	private String addedDevice;

}
