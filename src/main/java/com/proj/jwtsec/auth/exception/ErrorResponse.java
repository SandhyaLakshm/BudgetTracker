package com.proj.jwtsec.auth.exception;

public class ErrorResponse {
	
	private String statusMsg;
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public ErrorResponse(String statusMsg, int statusCode) {
		super();
		this.statusMsg = statusMsg;
		this.statusCode = statusCode;
	}
	private int statusCode;

}
