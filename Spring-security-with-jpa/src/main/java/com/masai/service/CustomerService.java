package com.masai.service;

import java.util.List;

import com.masai.entity.Customer;
import com.masai.exception.CustomerException;

public interface CustomerService {
	public Customer saveCustomer(Customer customer);
	public Customer getCustomerByEmail(String email) throws CustomerException;
	public List<Customer> getAllCustomers() throws CustomerException;
}