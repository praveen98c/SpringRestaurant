package com.home.restaurant.security.jwt.interfaces;

public interface JwtProvider {
	public String generateToken(String name);
	public String extractUsername(String token);
	public boolean validateToken(String token);
}
