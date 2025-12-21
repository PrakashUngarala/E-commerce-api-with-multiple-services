package com.nit.jwtTokenGen;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtToekenGen {

	private final String SECRET = "secret-key-for-userdetails-token-1234567890741852";
	
	
	public String generateToken(String username) {
		Jwts.builder()
		.setSubject(username)
		.claim("role","CUSTOMER")
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis()))
		.signWith(Keys.hmacShaKeyFor(SECRET.getBytes()),SignatureAlgorithm.HS256)
		.compact();
		return "tokenEcom";
		
	}
}
