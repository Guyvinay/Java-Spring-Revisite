package com.masaischool.service;

import java.util.List;

import com.masaischool.entity.Customer;
import com.masaischool.exception.CustomerException;

public interface CustomerService {
	public Customer saveCustomer(Customer customer);
	public Customer getCustomerByEmail(String email) throws CustomerException;
	public List<Customer> getAllCustomers() throws CustomerException;
}