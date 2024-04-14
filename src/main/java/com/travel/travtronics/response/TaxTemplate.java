package com.travel.travtronics.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class TaxTemplate {

	private Integer taxId;

	private Integer taxCategoryId;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String taxCategoryName;

	private String taxName;

	private String taxCode;

	private String taxDescription;

	private Long budgetFrom;

	private Long budgetTo;

	private double slabPercentage;

	private double slabAmount;

	private double calculatedAmount;

	public String getTaxCategoryName() {
		return taxCategoryName;
	}

	public void setTaxCategoryName(String taxCategoryName) {
		this.taxCategoryName = taxCategoryName;
	}

	public Integer getTaxId() {
		return taxId;
	}

	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}

	public Integer getTaxCategoryId() {
		return taxCategoryId;
	}

	public void setTaxCategoryId(Integer taxCategoryId) {
		this.taxCategoryId = taxCategoryId;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getTaxDescription() {
		return taxDescription;
	}

	public void setTaxDescription(String taxDescription) {
		this.taxDescription = taxDescription;
	}

	public Long getBudgetFrom() {
		return budgetFrom;
	}

	public void setBudgetFrom(Long budgetFrom) {
		this.budgetFrom = budgetFrom;
	}

	public Long getBudgetTo() {
		return budgetTo;
	}

	public void setBudgetTo(Long budgetTo) {
		this.budgetTo = budgetTo;
	}

	public double getSlabPercentage() {
		return slabPercentage;
	}

	public void setSlabPercentage(double slabPercentage) {
		this.slabPercentage = slabPercentage;
	}

	public double getSlabAmount() {
		return slabAmount;
	}

	public void setSlabAmount(double slabAmount) {
		this.slabAmount = slabAmount;
	}

	public double getCalculatedAmount() {
		return calculatedAmount;
	}

	public void setCalculatedAmount(double calculatedAmount) {
		this.calculatedAmount = calculatedAmount;
	}

}
