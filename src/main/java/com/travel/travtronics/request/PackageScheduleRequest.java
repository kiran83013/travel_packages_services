package com.travel.travtronics.request;

import java.util.Date;
import java.util.List;

import com.travel.travtronics.util.Status;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class PackageScheduleRequest {

	private Long id;

	private Long packageConfigurationId;

	private String scheduleName;

	private Date startDate;

	private Integer countMin;

	private Integer countMax;

	private String description;

	private String startCity;

	private String endCity;

	private Long status;

	private Long team;

	private Long owner;

	private Integer timeZone;

	private Date bookingStartDate;

	private Date bookingEndDate;

	private Date marketingStartDate;

	private Date marketingEndDate;

	private Integer marketingBudgetMin;

	private Integer marketingBudgetMax;

	private Date salesStartDate;

	private String campaignCode;

	private Long couponTemplate;

	private String metaSlug;

	private String metaTitle;

	private String metaKeywords;

	private String metaDescription;

	private Long createdBy;

	private Long updatedBy;

	private Status recordStatus;

	private String packageScheduleTextEditor;

	private Integer srTypeLink;

	@PositiveOrZero(message = "negitive values not allowed for advanceAmountMin")
	private Double advanceAmountMin;

	@PositiveOrZero(message = "negitive values not allowed for advanceAmountMax")
	private Double advanceAmountMax;

	private Double commissionAmount;

	private Double commissionPercentage;

	private Double priceWithFlights;
	private Double priceWithOutFlights;

	private List<PackageScheduleDatesRequest> scheduleDates;
}
