package com.travel.travtronics.response.dto;

import java.util.Date;
import java.util.List;

import com.travel.travtronics.util.Status;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PackageScheduleDto {

	private Long id;

	private Long packageConfigurationId;

	private String scheduleName;

	private Date startDate;

	private Integer countMin;

	private Integer countMax;

	private String description;

	private PackageCityDto startCity;

	private PackageCityDto endCity;

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

	private Date createdDate;

	private Date updatedDate;

	private Status recordStatus;

	private String packageScheduleTextEditor;

	private Integer srTypeLink;

	private Double advanceAmountMin;

	private Double advanceAmountMax;

	private Double commissionAmount;

	private Double commissionPercentage;

	private Double priceWithFlights;

	private Double priceWithOutFlights;

	private List<PackageScheduleDatesDto> scheduleDates;

}