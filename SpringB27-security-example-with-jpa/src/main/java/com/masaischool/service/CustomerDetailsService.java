package com.masaischool.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.masaischool.entity.Customer;
import com.masaischool.exception.CustomerException;
import com.masaischool.repository.CustomerRepository;

//DaoAutheticationProvider ---> UserDetailsService class for authentication processing
@Service
public class CustomerDetailsService implements UserDetailsService {
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//for Customer, email is the username
		Optional<Customer> optional = customerRepository.findByEmail(username);
		if(!optional.isPresent())
			throw new CustomerException("No customer for given email " + username);
		
		//you are here means Customer for given mail id is here
		Customer customer = optional.get();
		//return new User(customer.getEmail(), customer.getPassword(), null);
		return new CustomerDetails(customer);
	}

}
