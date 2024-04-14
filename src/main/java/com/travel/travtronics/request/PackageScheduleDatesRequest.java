package com.travel.travtronics.request;

import lombok.Data;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.Date;

import com.travel.travtronics.util.Status;

@Data
public class PackageScheduleDatesRequest {

	private Long id;

	private Date startDate;

	private Date endDate;

	private Long createdBy;

	private Long updatedBy;

	@Enumerated(EnumType.STRING)
	private Status recordStatus;
	
}
