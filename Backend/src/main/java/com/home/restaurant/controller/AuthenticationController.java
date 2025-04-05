package com.home.restaurant.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

import com.home.restaurant.constants.RestApiPaths;
import com.home.restaurant.dto.request.AuthRequest;
import com.home.restaurant.dto.response.AuthResponse;
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
	public AuthResponse login(@RequestBody AuthRequest authRequest) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		String token = jwtProvider.generateToken(authRequest.getUsername());
		AuthResponse authResponse = new AuthResponse(token);
		return authResponse;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(RestApiPaths.Auth.REGISTER)
	public void register(@RequestBody AuthRequest authRequest) {
		restaurentUserService.registerUser(authRequest.getName(), authRequest.getUsername(), authRequest.getPassword());
	}
}