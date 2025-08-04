package com.security.service;

import java.util.List;

import com.security.entity.Customer;
import com.security.exception.CustomerException;

public interface CustomerService {

public Customer registerCustomer(Customer customer);
	
	public Customer getCustomerDetailsByEmail(String email)throws CustomerException;
	
	public List<Customer> getAllCustomerDetails()throws CustomerException;
	
}
