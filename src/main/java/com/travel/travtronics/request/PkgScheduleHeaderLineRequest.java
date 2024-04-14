package com.travel.travtronics.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PkgScheduleHeaderLineRequest {
	
	private Long id;
	
	private Long headerId;
	
	private String lineGroupId;
	
	private Integer lineOptionId;
	
	private Integer defaultOption;
	
	private Integer lineStatus;

}
