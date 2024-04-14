package com.travel.travtronics.request.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.travel.travtronics.bpf.model.InputTypeConfig;
import com.travel.travtronics.bpf.model.Item;
import com.travel.travtronics.util.Status;

import lombok.Data;

@Data
public class PricingLinesFetchModel {

	private Long id;

	private Long headerId;

	private Long item;

	private String taxSlab;

	private Long organizationId;

	private String api;

	private String charge;

	private Item.EntityImport qualifier;

	private Item.EntityImport fieldDependent;

	private Item.EntityImport percentage;

	@JsonIgnore
	private Integer field;

	private String variableFrequency;

	private String operator;

	private String value;

	private Item.EntityImport portalVisible;

	private Item.EntityImport statutory;

	private String isAdditinalCharge;

	private String isChargeOveride;

	private Date startDate;

	private Date endDate;

	private String attr1;

	private String attr2;

	private String attr3;

	private Integer createdBy;

	private Integer updatedBy;

	private Date createdDate;

	private Date updatedDate;

	private Status status;

	private Boolean pbouom;

	private Long umoValue;

	@JsonProperty("field")
	private InputTypeConfig configModel;

	private String toOperator;

	private String toValue;

	private Boolean isApi;

	private Boolean isRange;

	private Integer priceTemplateType;

}
