package com.travel.travtronics.response.dto;

import java.util.Date;

import com.travel.travtronics.util.Status;

import lombok.Data;

@Data
public class PackageScheduleDatesDto {

	private Long id;

	private Date startDate;

	private Date endDate;

	private Long createdBy;

	private Long updatedBy;

	private Status recordStatus;

}
