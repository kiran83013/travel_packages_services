package com.travel.travtronics.response;

public class MessageStatusResponse {
	private int status;
	private String message;

	public MessageStatusResponse() {
	};

	public MessageStatusResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
