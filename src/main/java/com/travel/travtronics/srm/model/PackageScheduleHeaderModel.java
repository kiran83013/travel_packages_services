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
@Table(name = "package_schedule_header")
public class PackageScheduleHeaderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "schedule_id")
	private Integer scheduleId;

	@Column(name = "product_id")
	private Integer productId;

	@Column(name = "service_request_id")
	private Integer serviceRequestId;

	@Column(name = "service_request_line_id")
	private Integer serviceRequestLineId;

	@Column(name = "header_status")
	private Integer headerStatus;

	@Column(name = "remove_price")
	private Integer removePrice;

	@Column(name = "dynamic_price")
	private Integer dynamicPrice;

	@Column(name = "price_override")
	private Integer priceOverride;

	@Column(name = "discount_override")
	private Integer discountOverride;

	@Column(name = "base_override")
	private Integer baseOverride;

	@Column(name = "tax_override")
	private Integer taxOverride;

	@Column(name = "inputvat_override")
	private Integer inputvatOverride;

	@Column(name = "outputvat_override")
	private Integer outputvatOverride;

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

	@Column(name = "price_list_item_id")
	private Long priceListItemId;

	@Column(name = "price_list_header_id")
	private Long priceListHeaderId;

}
