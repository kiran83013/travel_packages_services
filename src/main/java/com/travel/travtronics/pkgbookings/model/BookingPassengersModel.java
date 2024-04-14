package com.travel.travtronics.pkgbookings.model;

import com.travel.travtronics.util.GendersEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "booking_passengers")
public class BookingPassengersModel {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Long passengerId;

    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "booking_reference_number")
    private String bookingReferenceNumber;

    @Column(name = "customer_travel_user_id")
    private Integer customerTravelUserId;

    @Column(name = "pax_type")
    private String paxType;

    @Column(name = "title")
    private String title;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_sur_name")
    private String lastSurName;

    @Column(name = "dob")
    private String dob;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private GendersEnum gender;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "passenger_lead")
    private Integer passengerLead;

    @Column(name = "passenger_status")
    private Integer passengerStatus;

    @Column(name = "added_by")
    private Integer addedBy;

    @Column(name = "added_date")
    private String addedDate;

    @Column(name = "added_device")
    private String addedDevice;

    @Column(name = "added_ip")
    private String addedIp;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "updated_date")
    private String updatedDate;

    @Column(name = "updated_device")
    private String updatedDevice;

    @Column(name = "updated_ip")
    private String updatedIp;

}

