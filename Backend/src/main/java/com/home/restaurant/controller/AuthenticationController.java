package com.home.restaurant.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;

import com.home.restaurant.constants.RestApiPaths;
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
			ApiResponse<AuthResponse> apiResponse = new ApiResponse<AuthResponse>(authResponse, "Login Successfull");
			return ResponseEntity.ok(apiResponse);
		} catch (AuthenticationException e) {
			ApiResponse<AuthResponse> response = new ApiResponse<AuthResponse>(null, "Login failed");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}

	@PostMapping(RestApiPaths.Auth.REGISTER)
	public ResponseEntity<String> register(@RequestBody AuthRequest authRequest) {
		try {
			restaurentUserService.registerUser(authRequest.getName(), authRequest.getUsername(),
					authRequest.getPassword());
			return ResponseEntity.status(HttpStatus.CREATED).body("User Created Successfully");
		} catch (UserAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User Already Present");
		}
	}
}

class AuthRequest {
	private String username;
	private String password;
	private String name;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class ApiResponse<T> {
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

class AuthResponse {

	private String token;

	public AuthResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}