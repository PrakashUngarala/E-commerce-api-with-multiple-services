package com.nit.JwtUtil;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String SECRET = "secret-key-for-userdetails-token-1234567890741852";
	
	
	public Claims validateToken(String token) {
		
		return Jwts.parserBuilder()
		.setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
		.build()
		.parseClaimsJws(token)
		.getBody();
	}
}
