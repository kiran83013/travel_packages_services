package com.travel.travtronics.request.dto;

import java.util.List;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackageHospitalDTO {

	private HospitalRequestModel requestData;

	private List<SrHospitalAddonsDto> addonsData;

}
