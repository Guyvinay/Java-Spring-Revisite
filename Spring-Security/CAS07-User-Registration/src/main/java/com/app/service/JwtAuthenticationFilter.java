package com.app.service;

import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {


	@Autowired
    private Key secretKey;
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = extractToken(request);
		
		if(token!=null) {
			Authentication authentication = getAuthentication(token);
			if(authentication!=null) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		
		filterChain.doFilter(request, response);
	}
	private String extractToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}
		return null;
	}
	
	private Authentication getAuthentication(String token) {
	System.out.println("SigningKey: "+secretKey);
		 Claims claims = Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token)
				.getBody()
				;
		 String username = claims.getSubject();
		 String userRole = claims.get("role", String.class);
		 List<GrantedAuthority> authorities = Arrays.asList(
				 new SimpleGrantedAuthority("ROLE_"+userRole)
				 );
		return new UsernamePasswordAuthenticationToken(username,null, authorities);
	}
	
//	private String validateToken(String token) {
//	System.out.println(jwtKey);
//		Jws<Claims> claims = Jwts.parserBuilder()
//				.setSigningKey(secretKey)
//				.build()
//				.parseClaimsJws(token);
//		return claims.getBody().getSubject();
//	}

}
