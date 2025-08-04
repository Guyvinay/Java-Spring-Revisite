package com.masaischool.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.masaischool.entity.Customer;
import com.masaischool.exception.CustomerException;
import com.masaischool.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Customer saveCustomer(Customer customer) {
		//plain text password is coverted to encoded one
		//this encoded value will be assigned as new password
		//this encoded value will be stored in the database
		customer.setPassword(passwordEncoder.encode(customer.getPassword())); 
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerByEmail(String email) throws CustomerException {
		Optional<Customer> optional = customerRepository.findByEmail(email);
		return optional.orElseThrow(() -> new CustomerException("No customer found for the given email " + email));
	}

	@Override
	public List<Customer> getAllCustomers() throws CustomerException {
		List<Customer> listCustomer = customerRepository.findAll();
		if(listCustomer.isEmpty())
			throw new CustomerException("No customer found");
		return listCustomer;
	}

}
