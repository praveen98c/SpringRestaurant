package com.home.restaurant.constants;

public enum RestApiCode {

	// Error Codes
	USER_ALREADY_EXISTS(2001, "User already exists"), 
	USER_NOT_FOUND(2002, "User not found"),
	AUTH_FAILED(2003, "Authentication failed");

	private final int code;
	private final String message;

	RestApiCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
