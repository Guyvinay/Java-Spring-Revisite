package com.security.config;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RequestValidationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest hreq = (HttpServletRequest)request;
		HttpServletResponse hres =(HttpServletResponse)response;
		
		String header = hreq.getHeader("Allow");
		if(header==null||header.equals("test")) {
			hres.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			throw new BadCredentialsException("Header should contain a key Allow and value should not be test");
		}
		filterChain.doFilter(hreq, hres);
	}

}
