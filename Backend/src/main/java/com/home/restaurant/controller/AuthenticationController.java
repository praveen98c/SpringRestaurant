package com.home.restaurant.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;

import com.home.restaurant.constants.RestApiCode;
import com.home.restaurant.constants.RestApiPaths;
import com.home.restaurant.dto.request.AuthRequest;
import com.home.restaurant.dto.response.ApiResponse;
import com.home.restaurant.dto.response.AuthResponse;
import com.home.restaurant.exceptions.UserAlreadyExistsException;
import com.home.restaurant.security.jwt.interfaces.JwtProvider;
import com.home.restaurant.service.interfaces.UserService;

@RestController
@RequestMapping(RestApiPaths.Auth.BASE)
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	private final UserService restaurentUserService;
	private final JwtProvider jwtProvider;

	public AuthenticationController(AuthenticationManager authenticationManager, UserService restaurentUserService,
			JwtProvider jwtProvider) {
		this.authenticationManager = authenticationManager;
		this.restaurentUserService = restaurentUserService;
		this.jwtProvider = jwtProvider;
	}

	@PostMapping(RestApiPaths.Auth.LOGIN)
	public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody AuthRequest authRequest) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			String token = jwtProvider.generateToken(authRequest.getUsername());
			AuthResponse authResponse = new AuthResponse(token);
			ApiResponse<AuthResponse> apiResponse = new ApiResponse<AuthResponse>(RestApiCode.LOGIN_SUCCESS,
					authResponse);
			return ResponseEntity.ok(apiResponse);
		} catch (AuthenticationException e) {
			ApiResponse<AuthResponse> response = new ApiResponse<AuthResponse>(RestApiCode.AUTH_FAILED);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}

	@PostMapping(RestApiPaths.Auth.REGISTER)
	public ResponseEntity<ApiResponse<Void>> register(@RequestBody AuthRequest authRequest) {
		try {
			restaurentUserService.registerUser(authRequest.getName(), authRequest.getUsername(),
					authRequest.getPassword());
			ApiResponse<Void> apiResponse = new ApiResponse<Void>(RestApiCode.REGISTER_SUCCESS);
			return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
		} catch (UserAlreadyExistsException e) {
			ApiResponse<Void> apiResponse = new ApiResponse<Void>(RestApiCode.USER_ALREADY_EXISTS);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
		}
	}
}