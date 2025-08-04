//package com.masai.service;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.masai.entity.Customer;
//import com.masai.exception.CustomerException;
//import com.masai.repository.CustomerRepository;
//
//@Service
//public class CustomerDetailsService implements UserDetailsService{
//
//	@Autowired
//	private CustomerRepository customerRepository;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		Optional<Customer> optional = customerRepository.findByEmail(username);
//		
//		if(!optional.isPresent()) throw new CustomerException("Customer not found for"+username);
//		
//		Customer  customer = optional.get();
//		return new CustomerDetails(customer);
////		return new User(customer.getEmail() , customer.getPassword()  , null);
//	}
//
//}
