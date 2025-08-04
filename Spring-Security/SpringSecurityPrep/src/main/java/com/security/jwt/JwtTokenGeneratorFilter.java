package com.security.jwt;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

	Logger log = LoggerFactory.getLogger(JwtTokenGeneratorFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("Generating JWT Token....");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication!=null) {
			
			log.info("Authorities "+authentication.getAuthorities());
			
			SecretKey secretKey = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
			
			String jwtToken = Jwts
					              .builder()
					                       .setIssuer("VINAY")
					                       .setSubject("JWT Token")
					                       .claim("username", authentication.getName())
					                       .claim("authorities", populateAuthorities(authentication.getAuthorities()))
					                       .setIssuedAt(new Date())
					                       .setExpiration(new Date(new Date().getTime()+ 30000000))
					                       .signWith(secretKey)
					                       .compact();
			response.setHeader(SecurityConstants.JWT_HEADER, jwtToken);
		}
		
		filterChain.doFilter(request, response);

	}
	
	private String populateAuthorities(Collection<? extends GrantedAuthority>collection) {
		Set<String> authorities = new HashSet<>();
		for(GrantedAuthority auths:collection) {
			authorities.add(auths.getAuthority());
		}
		return String.join(",", authorities);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		
		return !request.getServletPath().equals("/signIn");
	}
	
	

}
