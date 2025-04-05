package com.home.restaurant.dto.response;

import java.util.Optional;

import com.home.restaurant.constants.RestApiCode;

public class ApiResponse<T> {
	private int code;
	private String message;
	private Optional<T> data;

	public ApiResponse(RestApiCode appCode, T data) {
		this.message = appCode.getMessage();
		this.code = appCode.getCode();
		this.data = Optional.ofNullable(data);
	}

	public ApiResponse(RestApiCode appCode) {
		this.message = appCode.getMessage();
		this.code = appCode.getCode();
		this.data = null;
	}

	public Optional<T> getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}
}