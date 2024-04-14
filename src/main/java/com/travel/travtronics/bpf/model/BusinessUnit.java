package com.travel.travtronics.bpf.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
@Entity
@Table(name = "business_unit")
public class BusinessUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BusinessUnitId")
	private Long businessUnitId;

	@Column(name = "OrganizationId")
	private Long organizationId;

	@Column(name = "Code")
	private String code;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;

	@Column(name = "AccCode")
	private String accCode;

	@Column(name = "StartDate")
	private Date startDate;

	@Column(name = "EndDate")
	private Date endDate;

	@Column(name = "Status")
	private String status;

	@Column(name = "Attr1")
	private Long attr1;

	@Column(name = "Attr2")
	private Long attr2;

	@Column(name = "Attr3")
	private Long attr3;

	@Column(name = "Attr4")
	private Long attr4;

	@Column(name = "Attr5")
	private Long attr5;

	@Column(name = "Attr6")
	private String attr6;

	@Column(name = "Attr7")
	private String attr7;

	@Column(name = "Attr8")
	private String attr8;

	@Column(name = "Attr9")
	private String attr9;

	@Column(name = "Attr10")
	private String attr10;

	@Column(name = "Attr11")
	private String attr11;

	@Column(name = "Attr12")
	private String attr12;

	@Column(name = "Attr13")
	private Date attr13;

	@Column(name = "Attr14")
	private Date attr14;

	@Column(name = "Attr15")
	private Date attr15;

	@Column(name = "Attr16")
	private Boolean attr16;

	@Column(name = "Attr17")
	private Boolean attr17;

	@Column(name = "Attr18")
	private Boolean attr18;

	@Column(name = "Attr19")
	private Boolean attr19;

	@Column(name = "Attr20")
	private Boolean attr20;

	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "UpdatedBy")
	private String updatedBy;

	@CreationTimestamp
	@Column(name = "CreatedDate", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Column(name = "UpdatedDate")
	private Date updatedDate;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(Long businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAccCode() {
		return accCode;
	}

	public void setAccCode(String accCode) {
		this.accCode = accCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getAttr1() {
		return attr1;
	}

	public void setAttr1(Long attr1) {
		this.attr1 = attr1;
	}

	public Long getAttr2() {
		return attr2;
	}

	public void setAttr2(Long attr2) {
		this.attr2 = attr2;
	}

	public Long getAttr3() {
		return attr3;
	}

	public void setAttr3(Long attr3) {
		this.attr3 = attr3;
	}

	public Long getAttr4() {
		return attr4;
	}

	public void setAttr4(Long attr4) {
		this.attr4 = attr4;
	}

	public Long getAttr5() {
		return attr5;
	}

	public void setAttr5(Long attr5) {
		this.attr5 = attr5;
	}

	public String getAttr6() {
		return attr6;
	}

	public void setAttr6(String attr6) {
		this.attr6 = attr6;
	}

	public String getAttr7() {
		return attr7;
	}

	public void setAttr7(String attr7) {
		this.attr7 = attr7;
	}

	public String getAttr8() {
		return attr8;
	}

	public void setAttr8(String attr8) {
		this.attr8 = attr8;
	}

	public String getAttr9() {
		return attr9;
	}

	public void setAttr9(String attr9) {
		this.attr9 = attr9;
	}

	public String getAttr10() {
		return attr10;
	}

	public void setAttr10(String attr10) {
		this.attr10 = attr10;
	}

	public String getAttr11() {
		return attr11;
	}

	public void setAttr11(String attr11) {
		this.attr11 = attr11;
	}

	public String getAttr12() {
		return attr12;
	}

	public void setAttr12(String attr12) {
		this.attr12 = attr12;
	}

	public Date getAttr13() {
		return attr13;
	}

	public void setAttr13(Date attr13) {
		this.attr13 = attr13;
	}

	public Date getAttr14() {
		return attr14;
	}

	public void setAttr14(Date attr14) {
		this.attr14 = attr14;
	}

	public Date getAttr15() {
		return attr15;
	}

	public void setAttr15(Date attr15) {
		this.attr15 = attr15;
	}

	public Boolean getAttr16() {
		return attr16;
	}

	public void setAttr16(Boolean attr16) {
		this.attr16 = attr16;
	}

	public Boolean getAttr17() {
		return attr17;
	}

	public void setAttr17(Boolean attr17) {
		this.attr17 = attr17;
	}

	public Boolean getAttr18() {
		return attr18;
	}

	public void setAttr18(Boolean attr18) {
		this.attr18 = attr18;
	}

	public Boolean getAttr19() {
		return attr19;
	}

	public void setAttr19(Boolean attr19) {
		this.attr19 = attr19;
	}

	public Boolean getAttr20() {
		return attr20;
	}

	public void setAttr20(Boolean attr20) {
		this.attr20 = attr20;
	}
}
