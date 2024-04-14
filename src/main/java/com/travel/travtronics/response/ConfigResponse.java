package com.travel.travtronics.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.travel.travtronics.bpf.model.InputTypeConfig;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ConfigResponse extends InputTypeConfig {

	@JsonProperty("type")
	private String typeName;

	private String service;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Boolean getIsExternalUrl() {
		return isExternalUrl;
	}

	public void setIsExternalUrl(Boolean isExternalUrl) {
		this.isExternalUrl = isExternalUrl;
	}

	private Boolean isExternalUrl;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
