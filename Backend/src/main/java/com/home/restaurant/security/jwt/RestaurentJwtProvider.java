package com.home.restaurant.security.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.home.restaurant.security.jwt.interfaces.JwtProvider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class RestaurentJwtProvider implements JwtProvider {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private long jwtExpiration;

	private Key getSigningKey() {
		byte[] keyBytes = Base64.getDecoder().decode(jwtSecret);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	@Override
	public String generateToken(String username) {
		return Jwts.builder().subject(username).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + jwtExpiration))
				.signWith(getSigningKey()).compact();
	}
}
