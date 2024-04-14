package com.travel.travtronics.pkgbookings.response;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PkgSchedulesResponse {
	
	private Long bookingPackageScheduleId;

    private Long bookingId;

    private Integer bookingPackageId;

    private Integer bookingScheduleId;

    private boolean bookingPackageScheduleStatus;

    private String packageName;

    private String scheduleName;

    private Date scheduleStartDate;

    private Date scheduleEndDate;

    private String scheduleDetails;

    private String scheduleEditorData;

    private Integer scheduleServiceTypeId;
    
    private Integer addedBy;

    private Date addedDate;

    private String addedDevice;

    private String addedIp;

    private Integer updatedBy;

    private Date updatedDate;

    private String updatedDevice;

    private String updatedIp;

}
