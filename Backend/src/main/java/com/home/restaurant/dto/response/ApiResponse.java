package com.home.restaurant.dto.response;

import java.util.Optional;

public class ApiResponse<T> {
	private Optional<T> data;
	private String message;

	public ApiResponse(T data, String message) {
		this.data = Optional.ofNullable(data);
		this.message = message;
	}

	public Optional<T> getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}
}