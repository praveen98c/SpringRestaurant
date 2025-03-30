package com.home.restaurant.security.jwt.interfaces;

public interface JwtProvider {
	public String generateToken(String name);
}
