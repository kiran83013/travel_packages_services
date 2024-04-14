package com.travel.travtronics.pkgbookings.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "booking_package_schedule")
public class BookingPackageScheduleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_package_schedule_id")
    private Long bookingPackageScheduleId;

    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "booking_package_id")
    private Integer bookingPackageId;

    @Column(name = "booking_schedule_id")
    private Integer bookingScheduleId;

    @Column(name = "booking_package_schedule_status")
    private Integer bookingPackageScheduleStatus;

    @Column(name = "package_name")
    private String packageName;

    @Column(name = "schedule_name")
    private String scheduleName;

    @Column(name = "schedule_start_date")
    private Date scheduleStartDate;

    @Column(name = "schedule_end_date")
    private Date scheduleEndDate;

    @Column(name = "schedule_details", columnDefinition = "LONGTEXT")
    private String scheduleDetails;

    @Column(name = "schedule_editor_data", columnDefinition = "LONGTEXT")
    private String scheduleEditorData;

    @Column(name = "schedule_service_type_id")
    private Integer scheduleServiceTypeId;

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
