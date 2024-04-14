package com.travel.travtronics.srm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user_info")
public class UserInfoModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="business_unit")
	private Long businessUnit;
	
	@Column(name="cost_center")
	private Long costCenter;
	
	@Column(name="department")
	private Long department;
	
	@Column(name="email")
	private String email;
	
	@Column(name="iam_id")
	private String iamId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="location")
	private String location;
	
	@Column(name="credit_limit_amount")
	private double creditLimitAmount;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(Long businessUnit) {
		this.businessUnit = businessUnit;
	}

	public Long getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(Long costCenter) {
		this.costCenter = costCenter;
	}

	public Long getDepartment() {
		return department;
	}

	public void setDepartment(Long department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIamId() {
		return iamId;
	}

	public void setIamId(String iamId) {
		this.iamId = iamId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getCreditLimitAmount() {
		return creditLimitAmount;
	}

	public void setCreditLimitAmount(double creditLimitAmount) {
		this.creditLimitAmount = creditLimitAmount;
	}
	
	
	
	
}
