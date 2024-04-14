package com.travel.travtronics.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PkgScheduleHeaderHotelReponse {

	private Long id;

	private Integer scheduleId;

	private Integer productId;

	private Integer serviceRequestId;

	private Integer serviceRequestLineId;

	private Integer headerStatus;

	private Integer removePrice;

	private Integer dynamicPrice;

	private Integer priceOverride;

	private Integer discountOverride;

	private Integer baseOverride;

	private Integer taxOverride;

	private Integer inputvatOverride;

	private Integer outputvatOverride;

	private Integer addedBy;

	private String addedDate;

	private String addedDevice;

	private String addedIp;

	private Integer updatedBy;

	private String updatedDate;

	private String updatedDevice;

	private String updatedIp;

	private Long priceListItemId;
	private Long priceListHeaderId;
	
	private List<PkgScheduleHeaderLinesResponse> headerLines;

}
