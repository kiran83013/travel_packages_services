package com.travel.travtronics.packages.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "a3m_account")
public class AccountsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long userId;

	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "username")
	private String userName;

	@Email(message = "Email is not valid")
	@Column(name = "email", updatable = false)
	private String email;

	@Column(name = "email_secondary")
	private String emailSecondary;

	@Column(name = "phone_primary", updatable = false)
	private String phoneNumber;

	@Column(name = "phone_secondary")
	private String phoneSecondary;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "passwordvalid")
	private String passwordValid;

	@Column(name = "createdon", updatable = false)
	private String createdDate;

	@Column(name = "verifiedon")
	private String verifiedOn;

	@Column(name = "lastsignedinon")
	private String lastSignedInOn;

	@Column(name = "resetsenton")
	private String resetSentOn;

	@Column(name = "deletedon")
	private String deletedOn;

	@Column(name = "suspendedon")
	private String suspendedOn;

	@Column(name = "employee_booking_amount")
	private Double employeeBookingAmount;

	@Column(name = "credit_limit_amount")
	private Double creditLimitAmount;

	@Column(name = "running_balance")
	private Double runningBalance;

	@Column(name = "userupdatepassword")
	private Integer userUpdatePassword;

	@Column(name = "status")
	private String status;

	@Column(name = "start_date")
	private String startDate;

	@Column(name = "end_date")
	private String endDate;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailSecondary() {
		return emailSecondary;
	}

	public void setEmailSecondary(String emailSecondary) {
		this.emailSecondary = emailSecondary;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneSecondary() {
		return phoneSecondary;
	}

	public void setPhoneSecondary(String phoneSecondary) {
		this.phoneSecondary = phoneSecondary;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordValid() {
		return passwordValid;
	}

	public void setPasswordValid(String passwordValid) {
		this.passwordValid = passwordValid;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getVerifiedOn() {
		return verifiedOn;
	}

	public void setVerifiedOn(String verifiedOn) {
		this.verifiedOn = verifiedOn;
	}

	public String getLastSignedInOn() {
		return lastSignedInOn;
	}

	public void setLastSignedInOn(String lastSignedInOn) {
		this.lastSignedInOn = lastSignedInOn;
	}

	public String getResetSentOn() {
		return resetSentOn;
	}

	public void setResetSentOn(String resetSentOn) {
		this.resetSentOn = resetSentOn;
	}

	public String getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(String deletedOn) {
		this.deletedOn = deletedOn;
	}

	public String getSuspendedOn() {
		return suspendedOn;
	}

	public void setSuspendedOn(String suspendedOn) {
		this.suspendedOn = suspendedOn;
	}

	public Double getEmployeeBookingAmount() {
		return employeeBookingAmount;
	}

	public void setEmployeeBookingAmount(Double employeeBookingAmount) {
		this.employeeBookingAmount = employeeBookingAmount;
	}

	public Double getCreditLimitAmount() {
		return creditLimitAmount;
	}

	public void setCreditLimitAmount(Double creditLimitAmount) {
		this.creditLimitAmount = creditLimitAmount;
	}

	public Double getRunningBalance() {
		return runningBalance;
	}

	public void setRunningBalance(Double runningBalance) {
		this.runningBalance = runningBalance;
	}

	public Integer getUserUpdatePassword() {
		return userUpdatePassword;
	}

	public void setUserUpdatePassword(Integer userUpdatePassword) {
		this.userUpdatePassword = userUpdatePassword;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	
	
}
