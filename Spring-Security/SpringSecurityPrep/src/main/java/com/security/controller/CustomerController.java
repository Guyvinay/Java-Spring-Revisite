package com.security.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.entity.Customer;
import com.security.entity.LoginCreds;
import com.security.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@GetMapping("/hello")
	public String testHandler() {
		return "Welcome to Spring Security";
	}
	@PostMapping("/contact")
	public String postDemo1() {
		return "Not harmfull POST operation";
	}

	@PutMapping("/notice")
	public String putDemo1() {
		return "Not harmfull PUT operation";
	}

	@PostMapping("/writeUs")
	public String postDemo3() {
		return "It is harmfull POST operation";
	}

	@PostMapping("/customers")
	public ResponseEntity<Customer> saveCustomerHandler(@RequestBody Customer customer){
		
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		
		Customer registeredCustomer= customerService.registerCustomer(customer);
		
		return new ResponseEntity<>(registeredCustomer,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/customers/{email}")
	public ResponseEntity<Customer> getCustomerByEmailHandler(@PathVariable("email") String email){
		
		
		Customer customer= customerService.getCustomerDetailsByEmail(email);
		
		return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomerHandler(){
		
		
		List<Customer> customers= customerService.getAllCustomerDetails();
		
		return new ResponseEntity<>(customers,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/signIn")
	public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth){
//		System.out.println("Hii");
//		System.out.println(auth.getPrincipal()); // this Authentication object having Principle object details
//		log.info("Inside Sign-in route");
		 Customer customer= customerService.getCustomerDetailsByEmail(auth.getName());
		 
		 return new ResponseEntity<>(customer.getName()+" Logged In Successfully", HttpStatus.ACCEPTED);	
	}
	
	@PostMapping("/signIn")
	public ResponseEntity<LoginCreds> loginCustomerByPost(@RequestBody LoginCreds loginCreds ){
//		
		log.info("getting Login Details"+loginCreds);
		
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginCreds.getUsername(), loginCreds.getPassword())
				);
		log.info("getting Login Details"+authenticate);

		 Customer customer= customerService.getCustomerDetailsByEmail(authenticate.getName());
		 log.info("getting Login Details "+customer);
		 return new ResponseEntity<LoginCreds>(loginCreds, HttpStatus.ACCEPTED);	
	}
}