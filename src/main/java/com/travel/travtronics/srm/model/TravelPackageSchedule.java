package com.travel.travtronics.srm.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.travel.travtronics.util.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Data
@Table(name = "package_configuration_schedule")
public class TravelPackageSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	// @NotNull
	@Column(name = "package_configuration_id", nullable = false)
	private Long packageConfigurationId;

	// @NotBlank
	@Column(name = "schedule_name", nullable = false)
	private String scheduleName;

	// @NotNull
	@Column(name = "start_date", nullable = false)
	private Date startDate;

	// @Min(1)
	// @NotNull
	@Column(name = "count_min", nullable = false)
	private Integer countMin;

	// @Min(1)
	// @NotNull
	@Column(name = "count_max", nullable = false)
	private Integer countMax;

	// @NotBlank
	@Column(name = "description", columnDefinition = "LONGTEXT")
	private String description;

	// @NotBlank
	@Column(name = "start_city", nullable = false)
	private String startCity;

	// @NotBlank
	@Column(name = "end_city", nullable = false)
	private String endCity;

	@Column(name = "status")
	private Long status;

	@Column(name = "team")
	private Long team;

	@Column(name = "owner")
	private Long owner;

	@Column(name = "time_zone")
	private Integer timeZone;

	@Column(name = "booking_start_date")
	private Date bookingStartDate;

	@Column(name = "booking_end_date")
	private Date bookingEndDate;

	@Column(name = "marketing_start_date")
	private Date marketingStartDate;

	@Column(name = "marketing_end_date")
	private Date marketingEndDate;

	@Column(name = "marketing_budget_min")
	private Integer marketingBudgetMin;

	@Column(name = "marketing_budget_max")
	private Integer marketingBudgetMax;

	@Column(name = "sales_start_date")
	private Date salesStartDate;

	@Column(name = "campaign_code")
	private String campaignCode;

	@Column(name = "coupon_template")
	private Long couponTemplate;

	@Column(name = "meta_slug")
	private String metaSlug;

	@Column(name = "meta_title")
	private String metaTitle;

	@Column(name = "meta_keywords", columnDefinition = "LONGTEXT")
	private String metaKeywords;

	@Column(name = "meta_description", columnDefinition = "LONGTEXT")
	private String metaDescription;

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

	@Enumerated(EnumType.STRING)
	@Column(name = "record_status")
	private Status recordStatus;

	@Column(name = "package_schedule_text_editor")
	private String packageScheduleTextEditor;

	@Column(name = "sr_type_link")
	private Integer srTypeLink;

	@Column(name = "advance_amount_min")
	private Double advanceAmountMin;

	@Column(name = "advance_amount_max")
	private Double advanceAmountMax;

	@Column(name = "commission_amount")
	private Double commissionAmount;

	@Column(name = "commission_percentage")
	private Double commissionPercentage;

	@Column(name = "price_with_flights")
	private Double priceWithFlights;
	
	@Column(name = "price_without_flights")
	private Double priceWithOutFlights;

}
