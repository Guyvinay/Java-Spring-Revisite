package com.masai.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.masai.entity.Customer;
import com.masai.exception.CustomerException;
import com.masai.repository.CustomerRepository;

@Component
public class CustomerAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private CustomerRepository cusRepo;
	
	@Autowired
	private PasswordEncoder passEnc;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		String name = auth.getName();
		String credentials = auth.getCredentials().toString();
		
		Optional<Customer> optional = cusRepo.findByEmail(name);
		
		if(!optional.isPresent()) throw new CustomerException("Customer not found");
		
		Customer  cus = optional.get();
		
		List<GrantedAuthority> auths = new ArrayList<>();
		
		auths.add(new SimpleGrantedAuthority(cus.getRole()));
		
		if(passEnc.matches(credentials, cus.getPassword())) {
			return new UsernamePasswordAuthenticationToken(name, credentials, auths);
		}
		
		throw new CustomerException("Invalid User");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		 return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
