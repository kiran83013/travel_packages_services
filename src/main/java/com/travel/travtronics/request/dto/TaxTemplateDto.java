package com.travel.travtronics.request.dto;

public interface TaxTemplateDto {
	
	Integer getTaxId();
	
	Integer getTaxCategoryId();
	
	String getTaxName();
	
	String getTaxCode();
	
	String getTaxDescription();
	
	Long getBudgetFrom();
	
	Long getBudgetTo();
	
	double getSlabPercentage();
	
	double getSlabAmount();

}
