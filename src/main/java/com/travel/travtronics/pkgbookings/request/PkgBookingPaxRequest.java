package com.travel.travtronics.pkgbookings.request;

import java.util.Date;

import com.travel.travtronics.util.GendersEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PkgBookingPaxRequest {
	
	private Long passengerId;

    private Long bookingId;

    private String bookingReferenceNumber;

    private Integer customerTravelUserId;

    private String paxType;

    private String title;

    private String firstName;

    private String middleName;

    private String lastSurName;

    private String dob;

    private Integer age;

    private GendersEnum gender;

    private String mobile;

    private String email;

    private Integer passengerLead;

    private Integer passengerStatus;

}
