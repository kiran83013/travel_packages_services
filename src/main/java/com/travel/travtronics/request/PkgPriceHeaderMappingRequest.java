package com.travel.travtronics.request;

import java.util.List;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PkgPriceHeaderMappingRequest {

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

	private String addedDeviceInfo;

	private String addedIp;

	private Long priceListItemId;

	private Long priceListHeaderId;

	private List<PkgScheduleHeaderLineRequest> headerLines;

}
