package com.travel.travtronics.response;

public interface PriceHearderTemplate {

	Long getPriceLineId();

	Integer getItemId();

	String getItemName();

	String getItemDesc();

	double getItemPrice();

	Integer getTaxCatId();

	Integer getField();

	String getApi();

	Boolean getIsApi();

	String getOperator();

	String getValue();

	String getToOperator();

	String getToValue();

	double getPercentage();

	String getQualifier();

	Integer getOrganizationId();

	String getOrganizationName();

	String getIacflag();

	Boolean getPbouom();

	Integer getUmoValue();

	Integer getUnitofMeasurement();

	Boolean getIsRange();

}
