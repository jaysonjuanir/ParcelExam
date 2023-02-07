package com.exam.parcel.response;

import com.exam.parcel.request.RulesRequest;

public class RulesResponse extends RulesRequest {

	private String ruleName;
	private String message;
	private Boolean isSuccess;
	private String errorMessage;

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
