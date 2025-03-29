package com.home.restaurant.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;

import com.home.restaurant.constants.RestApiPaths;
import com.home.restaurant.exceptions.UserAlreadyExistsException;
import com.home.restaurant.service.interfaces.UserService;

@RestController
@RequestMapping(RestApiPaths.Auth.BASE)
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	private final UserService restaurentUserService;

	public AuthenticationController(AuthenticationManager authenticationManager, UserService restaurentUserService) {
		this.authenticationManager = authenticationManager;
		this.restaurentUserService = restaurentUserService;
	}

	@PostMapping(RestApiPaths.Auth.LOGIN)
	public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			return ResponseEntity.status(HttpStatus.OK).body("Login Successfull");
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
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