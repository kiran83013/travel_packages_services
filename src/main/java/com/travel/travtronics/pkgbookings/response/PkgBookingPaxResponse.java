package com.travel.travtronics.pkgbookings.response;

import java.util.Date;

import com.travel.travtronics.util.GendersEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PkgBookingPaxResponse {
	
	private Long passengerId;

    private Long bookingId;

    private String bookingReferenceNumber;

    private Integer customerTravelUserId;

    private String paxType;

    private String title;

    private String firstName;

    private String middleName;

    private String lastSurName;

    private Date dob;

    private Integer age;

    private GendersEnum gender;

    private String mobile;

    private String email;

    private Boolean passengerLead;

    private Boolean passengerStatus;
    
    private Integer addedBy;

    private Date addedDate;

    private String addedDevice;

    private String addedIp;

    private Integer updatedBy;

    private Date updatedDate;

    private String updatedDevice;

    private String updatedIp;

}
