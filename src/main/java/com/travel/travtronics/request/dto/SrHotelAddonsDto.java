package com.travel.travtronics.request.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.travel.travtronics.response.dto.AttractionsRequiredPaxInfo;
import com.travel.travtronics.srm.model.SrHotelAddons.AddonFor;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SrHotelAddonsDto {

	private Integer id;

	@NotNull
	private Integer addonSrId;

	private Integer addonRfqId;

	private Integer addonLineId;

	private String addonPassengerId;

	private String addonRoomId;

	private Integer addonStatus;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private AddonFor addonFor;

	private Integer addonTypeId;

	private String addonType;

	private Integer addonSubTypeId;

	private String addonSubType;

	private String addonTitle;

	private String addonDescription;

	private Integer addonExtraCost;

	private Integer addonCreatedBy;

	private String addonCreatedDate;

	private String addonCreatedDevice;

	private String addonCreatedIp;

	private Integer addonUpdatedBy;

	private String addonUpdatedDate;

	private String addonUpdatedDevice;

	private String addonUpdatedIp;

	private String addonAttr1;

	private String addonAttr2;

	private String addonAttr3;

	private String addonAttr4;

	private String addonAttr5;

	private String addonAttr6;

	private String addonAttr7;

	private String addonAttr8;

	private String addonAttr9;

	private String addonAttr10;

	private String addonNights;

	private Integer addonCount;

	private Integer addonWithBooking;

	private Integer addonRequired;

	private String addonRemarks;

	private String addonPassengers;

	private Boolean addonIsDeleted;

	@JsonIgnore
	List<AttractionsRequiredPaxInfo> paxDetails;
}