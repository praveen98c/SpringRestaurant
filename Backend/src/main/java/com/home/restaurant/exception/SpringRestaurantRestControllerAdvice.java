package com.home.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.home.restaurant.constants.RestApiCode;
import com.home.restaurant.dto.response.ApiResponse;

@RestControllerAdvice
public class SpringRestaurantRestControllerAdvice {

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ApiResponse> handleUserNotFound(AuthenticationException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(RestApiCode.AUTH_FAILED));
	}
	
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ApiResponse> userAlreadyExists(UserAlreadyExistsException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(RestApiCode.USER_ALREADY_EXISTS));
	}
}
