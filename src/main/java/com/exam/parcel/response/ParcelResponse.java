package com.exam.parcel.response;

public class ParcelResponse {

	private Double cost;
	private Boolean isSuccess;
	private String message;

	public ParcelResponse() {
	}

	public ParcelResponse(String message, Boolean isSuccess) {
		this.message = message;
		this.isSuccess = isSuccess;
	}

	public ParcelResponse(Double cost) {
		this.cost = cost;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
