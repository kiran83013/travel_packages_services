package com.travel.travtronics.packages.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "package_itineraryheader")
public class PackageItineraryHeaderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITINERARYHEADERID")
	private Integer itineraryHeaderId;

	@Column(name = "Name")
	private String name;
	
	@Column(name = "StartDate", nullable = true)
	private String startDate;
	
	@Column(name = "EndDate", nullable = true)
	private String endDate;
	
	@Column(name = "CreatedBy")
	private Integer createdBy;
	
	@Column(name = "CreatedDate", nullable = true)
	private String createdDate;
	
	@Column(name = "UpdatedBy")
	private Integer updatedBy;
	
	@Column(name = "UpdatedDate", nullable = true)
	private String updatedDate;

	public Integer getItineraryHeaderId() {
		return itineraryHeaderId;
	}

	public void setItineraryHeaderId(Integer itineraryHeaderId) {
		this.itineraryHeaderId = itineraryHeaderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	
	

}
