package com.travel.travtronics.request;

public class FeildFormDataRequest {

	private String json;
	private Long jsonOrder;

	public FeildFormDataRequest(String json, Long jsonOrder) {
		super();
		this.json = json;
		this.jsonOrder = jsonOrder;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public Long getJsonOrder() {
		return jsonOrder;
	}

	public void setJsonOrder(Long jsonOrder) {
		this.jsonOrder = jsonOrder;
	}

}
