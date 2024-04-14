package com.travel.travtronics.packages.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "package_itinerary")
public class PackageItineraryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITINERARY_ID")
	private Integer itineraryId;
	
	@Column(name = "ITINERARYHEADERID")
	private Integer itineraryHeaderId;
	
	@Column(name = "ItineraryActivities")
	private String itineraryActivities;
	
	@Column(name = "DayStartTime")
	private String dayStartTime;
	
	@Column(name = "DayEndTime")
	private String dayEndTime;	

	@Column(name = "ItineraryStratDate")
	private String itineraryStratDate;	

	@Column(name = "ItineraryEndDate")
	private String itineraryEndDate;
	
	@Column(name = "ItineraryName")
	private String itineraryName;
	
	@Column(name = "ItineraryDay")
	private String itineraryDay;
	
	@Column(name = "Category")
	private String category;
	
	@Column(name = "Description")
	private String description;

	public Integer getItineraryId() {
		return itineraryId;
	}

	public void setItineraryId(Integer itineraryId) {
		this.itineraryId = itineraryId;
	}

	public Integer getItineraryHeaderId() {
		return itineraryHeaderId;
	}

	public void setItineraryHeaderId(Integer itineraryHeaderId) {
		this.itineraryHeaderId = itineraryHeaderId;
	}

	public String getItineraryActivities() {
		return itineraryActivities;
	}

	public void setItineraryActivities(String itineraryActivities) {
		this.itineraryActivities = itineraryActivities;
	}

	public String getDayStartTime() {
		return dayStartTime;
	}

	public void setDayStartTime(String dayStartTime) {
		this.dayStartTime = dayStartTime;
	}

	public String getDayEndTime() {
		return dayEndTime;
	}

	public void setDayEndTime(String dayEndTime) {
		this.dayEndTime = dayEndTime;
	}

	public String getItineraryStratDate() {
		return itineraryStratDate;
	}

	public void setItineraryStratDate(String itineraryStratDate) {
		this.itineraryStratDate = itineraryStratDate;
	}

	public String getItineraryEndDate() {
		return itineraryEndDate;
	}

	public void setItineraryEndDate(String itineraryEndDate) {
		this.itineraryEndDate = itineraryEndDate;
	}

	public String getItineraryName() {
		return itineraryName;
	}

	public void setItineraryName(String itineraryName) {
		this.itineraryName = itineraryName;
	}

	public String getItineraryDay() {
		return itineraryDay;
	}

	public void setItineraryDay(String itineraryDay) {
		this.itineraryDay = itineraryDay;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
