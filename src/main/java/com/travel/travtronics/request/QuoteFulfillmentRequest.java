package com.travel.travtronics.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteFulfillmentRequest {

	private Long quoteId;
	
	private Integer addedBy;
	
	private String deviceInfo;
	
	private String ipAddress;
	
}
