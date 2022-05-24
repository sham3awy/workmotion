package com.workmotion.senior.assignment.models.dto;

public class BaseResponse {
	private String code;
	private String messageType;
	private String errorMessageContent;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getErrorMessageContent() {
		return errorMessageContent;
	}

	public void setErrorMessageContent(String errorMessageContent) {
		this.errorMessageContent = errorMessageContent;
	}
}