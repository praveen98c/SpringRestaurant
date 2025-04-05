package com.home.restaurant.constants;

public enum RestApiCode {

	// Success codes
	REGISTER_SUCCESS(1001, "User registered successfully"), 
	LOGIN_SUCCESS(1002, "Login successful"),
	FOOD_ITEMS_BY_MENU_RETRIEVE_SUCCESS(1100, "Successfully retrieved food items of menu"),
	MENU_ITEMS_BY_RESTAURANT_RETRIEVE_SUCCESS(1200, "Successfully retrieved menus of restaurant"),
	RESTAURANT_REGRIEVE_SUCCESS(1300, "Successfully retrieved restaurant"),

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
