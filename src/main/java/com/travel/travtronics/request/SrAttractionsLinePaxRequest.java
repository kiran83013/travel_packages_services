package com.travel.travtronics.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SrAttractionsLinePaxRequest {

	private Integer attractionLinePaxId;

	private String attractionLinePassengerType;

	private String attractionLinePassengerTitle;

	private String attractionLinePassengerFristName;

	private String attractionLinePassengerMiddleName;

	private String attractionLinePassengerLastName;

	private String attractionLinePassengerDob;

	private String attractionLinePassengerGender;

	private String attractionLinePassengerPhone;

	private String attractionLinePassengerEmail;

}
