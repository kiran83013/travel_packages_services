package com.travel.travtronics.request;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travel.travtronics.bpf.model.Item;
import com.travel.travtronics.util.Status;

import lombok.Data;

@Data
public class PricingLineRequest {
	private Long id;

	@JsonIgnore
	private Long headerId;

	private Long item;

	private String taxSlab;

	private Long organizationId;

	private String api;

	private String charge;

	private Item.EntityImport qualifier;

	private Item.EntityImport fieldDependent;

	private Item.EntityImport percentage;

	private Integer field;

	private String variableFrequency;

	private String operator;

	private String value;

	private Item.EntityImport portalVisible;

	private Item.EntityImport statutory;

	private String isAdditinalCharge;

	private String isChargeOveride;

	private Integer createdBy;

	private Date startDate;

	private Date endDate;

	private String attr1;

	private String attr2;

	private String attr3;

	private Date createdDate;

	private Date updatedDate;

	private Status status;

	private Boolean pbouom;

	private Long umoValue;

	private String toOperator;

	private String toValue;

	private Boolean isApi;

	private Boolean isRange;

	private Integer priceTemplateType;

}
