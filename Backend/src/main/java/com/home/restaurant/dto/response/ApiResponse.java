package com.home.restaurant.dto.response;

import com.home.restaurant.constants.RestApiCode;

public class ApiResponse {
	private int code;
	private String message;

	public ApiResponse(RestApiCode appCode) {
		this.message = appCode.getMessage();
		this.code = appCode.getCode();
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}
}