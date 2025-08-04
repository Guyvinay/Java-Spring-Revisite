package com.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.entity.Customer;
import com.security.repository.CustomerRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Customer> optional = customerRepository.findByEmail(username);
		
		if(optional.isPresent()) {
			
			Customer customer = optional.get();
			
			List<GrantedAuthority> grantedAuth = new ArrayList<>();
			
			grantedAuth.add(new SimpleGrantedAuthority(customer.getRole()));
			System.out.println("Hii");
			return new User(customer.getEmail(),customer.getPassword(),grantedAuth);
//			return new CustomerUserDetails(customer);
		}else {
			throw new BadCredentialsException("User Details not found with this username: "+username);
		}
	}

}
