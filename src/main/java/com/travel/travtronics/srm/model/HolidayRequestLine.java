package com.travel.travtronics.srm.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import com.travel.travtronics.mapper.StringListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sr_package_line")

@DynamicUpdate
public class HolidayRequestLine {

	@Id
	@Column(name = "line_uuid")
	private String lineUuid;

	@Column(name = "request_id")
	private Long requestId;

	@Column(name = "request_line_id")
	private Long requestLineId;

	@Column(name = "alliance_code")
	private String allianceCode;

	@Column(name = "airline_code")
	private String airlineCode;

	
	@Column(name = "deal_code", columnDefinition = "JSON")
	private List<Map<Object, Object>> dealCode;

	@Column(name = "expandable_parameters_code")
	@Convert(converter = StringListConverter.class)
	private List<String> expandableParametersCode;

	@Column(name = "no_of_adt")
	private Integer noofAdt;

	@Column(name = "no_of_chd")
	private Integer noofChd;

	@Column(name = "no_of_inf")
	private Integer noofInf;

	@Column(name = "passenger_type_id")
	private Long passengerTypeId;

	@Column(name = "status_id")
	private Long statusId;

	@Column(name = "holidays_count")
	private Integer holidaysCount;

	@Column(name = "created_by", updatable = false)
	private Long createdBy;

	@CreationTimestamp
	@Column(name = "created_date", updatable = false)
	private Date createdDate;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;


	@Column(name = "addons", columnDefinition = "JSON")
	private List<Map<Object, Object>> addons;

	
	@Column(name = "exclusions", columnDefinition = "JSON")
	private List<Map<Object, Object>> exclusions;

	@Column(name = "line_status_id")
	private Integer lineStatusId;

	@Column(name = "lpo_number")
	private String lpoNumber;

	@Column(name = "lPo_date")
	private Date lpoDate;

	@Column(name = "lpo_amount")
	private String lpoAmount;

	
	@Column(name = "attractions", columnDefinition = "JSON")
	private List<Map<String, Object>> attractions;

	
	@Column(name = "hotel_selections_data", columnDefinition = "json")
	private List<Map<String, Object>> hotelSelectionData;

	@Column(name = "hotel_selection_type")
	private String hotelSelectionType;

	
	@Column(name = "hospital_selections_data", columnDefinition = "json")
	private List<Map<String, Object>> hospitalSelectionData;

	@Column(name = "hospital_selection_type")
	private String hospitalSelectionType;

	@Column(name = "transport_not_required")
	private Boolean transportFlag;

	@Column(name = "accomodation_not_required_for_hotel")
	private Boolean accomodationHotelFlag;

	@Column(name = "accomodation_not_required_for_hospital")
	private Boolean accomodationHospitalFlag;
	
	

	public Boolean getAccomodationHospitalFlag() {
		return accomodationHospitalFlag;
	}

	public void setAccomodationHospitalFlag(Boolean accomodationHospitalFlag) {
		this.accomodationHospitalFlag = accomodationHospitalFlag;
	}

	public Boolean getTransportFlag() {
		return transportFlag;
	}

	public void setTransportFlag(Boolean transportFlag) {
		this.transportFlag = transportFlag;
	}

	public Boolean getAccomodationHotelFlag() {
		return accomodationHotelFlag;
	}

	public void setAccomodationHotelFlag(Boolean accomodationHotelFlag) {
		this.accomodationHotelFlag = accomodationHotelFlag;
	}

	public List<Map<String, Object>> getHotelSelectionData() {
		return hotelSelectionData;
	}

	public void setHotelSelectionData(List<Map<String, Object>> hotelSelectionData) {
		this.hotelSelectionData = hotelSelectionData;
	}

	public String getHotelSelectionType() {
		return hotelSelectionType;
	}

	public void setHotelSelectionType(String hotelSelectionType) {
		this.hotelSelectionType = hotelSelectionType;
	}

	public List<Map<String, Object>> getHospitalSelectionData() {
		return hospitalSelectionData;
	}

	public void setHospitalSelectionData(List<Map<String, Object>> hospitalSelectionData) {
		this.hospitalSelectionData = hospitalSelectionData;
	}

	public String getHospitalSelectionType() {
		return hospitalSelectionType;
	}

	public void setHospitalSelectionType(String hospitalSelectionType) {
		this.hospitalSelectionType = hospitalSelectionType;
	}

	public List<Map<Object, Object>> getExclusions() {
		return exclusions;
	}

	public void setExclusions(List<Map<Object, Object>> exclusions) {
		this.exclusions = exclusions;
	}

	public List<Map<String, Object>> getAttractions() {
		return attractions;
	}

	public void setAttractions(List<Map<String, Object>> attractions) {
		this.attractions = attractions;
	}

	public String getLineUuid() {
		return lineUuid;
	}

	public void setLineUuid(String lineUuid) {
		this.lineUuid = lineUuid;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Long getRequestLineId() {
		return requestLineId;
	}

	public void setRequestLineId(Long requestLineId) {
		this.requestLineId = requestLineId;
	}

	public String getAllianceCode() {
		return allianceCode;
	}

	public void setAllianceCode(String allianceCode) {
		this.allianceCode = allianceCode;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public List<Map<Object, Object>> getDealCode() {
		return dealCode;
	}

	public void setDealCode(List<Map<Object, Object>> dealCode) {
		this.dealCode = dealCode;
	}

	public List<String> getExpandableParametersCode() {
		return expandableParametersCode;
	}

	public void setExpandableParametersCode(List<String> expandableParametersCode) {
		this.expandableParametersCode = expandableParametersCode;
	}

	public Integer getNoofAdt() {
		return noofAdt;
	}

	public void setNoofAdt(Integer noofAdt) {
		this.noofAdt = noofAdt;
	}

	public Integer getNoofChd() {
		return noofChd;
	}

	public void setNoofChd(Integer noofChd) {
		this.noofChd = noofChd;
	}

	public Integer getNoofInf() {
		return noofInf;
	}

	public void setNoofInf(Integer noofInf) {
		this.noofInf = noofInf;
	}

	public Long getPassengerTypeId() {
		return passengerTypeId;
	}

	public void setPassengerTypeId(Long passengerTypeId) {
		this.passengerTypeId = passengerTypeId;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<Map<Object, Object>> getAddons() {
		return addons;
	}

	public void setAddons(List<Map<Object, Object>> addons) {
		this.addons = addons;
	}

	public Integer getLineStatusId() {
		return lineStatusId;
	}

	public void setLineStatusId(Integer lineStatusId) {
		this.lineStatusId = lineStatusId;
	}

	public String getLpoNumber() {
		return lpoNumber;
	}

	public void setLpoNumber(String lpoNumber) {
		this.lpoNumber = lpoNumber;
	}

	public Date getLpoDate() {
		return lpoDate;
	}

	public void setLpoDate(Date lpoDate) {
		this.lpoDate = lpoDate;
	}

	public String getLpoAmount() {
		return lpoAmount;
	}

	public void setLpoAmount(String lpoAmount) {
		this.lpoAmount = lpoAmount;
	}

	public Integer getHolidaysCount() {
		return holidaysCount;
	}

	public void setHolidaysCount(Integer holidaysCount) {
		this.holidaysCount = holidaysCount;
	}

}
