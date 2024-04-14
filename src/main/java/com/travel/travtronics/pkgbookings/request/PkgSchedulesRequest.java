package com.travel.travtronics.pkgbookings.request;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PkgSchedulesRequest {
	
	private Long bookingPackageScheduleId;

    private Long bookingId;

    private Integer bookingPackageId;

    private Integer bookingScheduleId;

    private Integer bookingPackageScheduleStatus;

    private String packageName;

    private String scheduleName;

    private Date scheduleStartDate;

    private Date scheduleEndDate;

    private String scheduleDetails;

    private String scheduleEditorData;

    private Integer scheduleServiceTypeId;

}
