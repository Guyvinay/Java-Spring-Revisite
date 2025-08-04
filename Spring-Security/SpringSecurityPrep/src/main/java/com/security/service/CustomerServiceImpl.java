package com.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.entity.Authority;
import com.security.entity.Customer;
import com.security.exception.CustomerException;
import com.security.repository.CustomerRepository;

import jakarta.websocket.server.ServerEndpoint;
@Service
public class CustomerServiceImpl  implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		List<Authority> list = customer.getAuthorities();
		for(Authority auth:list) {
			auth.setCustomer(customer);
		}
		customer.setRole("ROLE_"+customer.getRole().toUpperCase());
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerDetailsByEmail(String email)throws CustomerException {
		
		 Customer customer = customerRepository.findByEmail(email).orElseThrow(
				 () -> new CustomerException("Customer Not found with Email: "+email)
				 );
		 return customer;
	}

	@Override
	public List<Customer> getAllCustomerDetails()throws CustomerException {
		
		List<Customer> customers= customerRepository.findAll();
		
		if(customers.isEmpty())
			throw new CustomerException("No Customer find");
		
		return customers;
		
	}

}
