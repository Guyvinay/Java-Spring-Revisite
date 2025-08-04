package com.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.security.entity.Authority;
import com.security.entity.Customer;
import com.security.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

//@Component
//@Slf4j
public class MyAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String userName = authentication.getName();
		String passWord = authentication.getCredentials().toString();
		
		Optional<Customer> optional = customerRepository.findByEmail(userName);
		if( optional.isPresent() ) {
			Customer customer = optional.get();
			if(passwordEncoder.matches(passWord, customer.getPassword())){
//				log.info("Inside My Provider Manager, Validated Creds.");
				List<GrantedAuthority> auth = new ArrayList<>();
//				List<Authority> authorities = customer.getAuthorities();
//				for(Authority au:authorities) {
//					auth.add(new SimpleGrantedAuthority(au.getName()));
//				}
				auth.add(new SimpleGrantedAuthority(customer.getRole()));
				return new UsernamePasswordAuthenticationToken(userName, passWord, auth);
			}
			else 
				throw new BadCredentialsException("Invalid Creds");
		}
		else 
			throw new BadCredentialsException("No User registerd with this details");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
