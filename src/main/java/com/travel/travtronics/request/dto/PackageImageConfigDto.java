package com.travel.travtronics.request.dto;


import com.travel.travtronics.util.Status;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PackageImageConfigDto {
	private Long id = 0L;

	private Long packageId;

	private String imageUrl;

	private int imageOrder = 0;

	private Status recordStatus = Status.Active;

	private Integer createdBy;

	private String createdDate;

	private Integer updatedBy;

	private String updatedDate;

}
