package com.travel.travtronics.response.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class PackageHospitalsInfoDto {

	public List<String> hospitalDate;
	public String code;
	public String cityName;
	public String hospitalName;
	public String destinationCode;
	public String accommodationTypeCode;
	public Integer hospitalRoomCount;
	public List<HospitalRoomsLevelsData> roomLevelsData;

	@ToString
	@Getter
	@Setter
	public static class HospitalRoomsLevelsData {
		public String roomName;
		public String roomSrId;
		public String roomType;
		public String roomLineId;
		public String roomNumber;
		public String roomStatus;
		public String roomAdultCount;
		public String roomChildCount;
		public String roomInfantAges;
		public String roomInfantCount;
		public List<HsRoomChildAge> roomLevelchildAge;
		public String roomAddonsRequired;
		public List<HospitalRoomPassengersInfo> roomPassengersInfo;

	}

	public static class HsRoomChildAge {
		@Getter
		@Setter
		public String roomChildAges;

	}

	@Getter
	@Setter
	public static class HospitalRoomPassengersInfo {
		public Integer paxId;
		public Integer prefix;
		public String firstName;
		public String lastName;
		public String dob;
		public Integer nationality;
		public String nationalityName;
		public Integer issuedCountry;
		public String issuedCountryName;
		public String passport;
		public String email;
		public String phone;
		public String paxIsDeleted;
		public Integer paxType;
		public String paxCode;
		public Integer assign;
		public String passportExpiredDate;
		public String passportIssueDate;
		public Integer requestLinePaxId;
		public Integer requestLineId;
		public String createdDate;
		public String createdBy;
		public String updatedBy;
		public String updatedDate;
		public Integer requestId;

	}

}
