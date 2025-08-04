package com.app.service;

import java.security.Key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
@Component
public class JwtUtils {

	@Autowired
	private Key jwtKey;
	
	
	public String extractUserName(String token) {
		System.out.println(jwtKey);
		Jws<Claims> claims = Jwts.parserBuilder()
				.setSigningKey(jwtKey)
				.build()
				.parseClaimsJws(token);
		System.out.println(claims);
		return claims.getBody().getSubject();
	}
	
}
