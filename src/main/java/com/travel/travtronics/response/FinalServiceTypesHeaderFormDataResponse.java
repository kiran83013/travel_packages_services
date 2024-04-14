package com.travel.travtronics.response;

import java.util.List;

import com.travel.travtronics.dtos.ServiceTypeHeaderResponse;

public class FinalServiceTypesHeaderFormDataResponse {

	private ServiceTypeHeaderResponse headerInfo;

	private List<FeildLineMappedResponseDto> linesData;

	public ServiceTypeHeaderResponse getHeaderInfo() {
		return headerInfo;
	}

	public void setHeaderInfo(ServiceTypeHeaderResponse headerInfo) {
		this.headerInfo = headerInfo;
	}

	public List<FeildLineMappedResponseDto> getLinesData() {
		return linesData;
	}

	public void setLinesData(List<FeildLineMappedResponseDto> linesData) {
		this.linesData = linesData;
	}

}
