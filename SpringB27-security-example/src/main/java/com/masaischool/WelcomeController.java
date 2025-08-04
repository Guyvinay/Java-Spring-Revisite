package com.masaischool;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
		
	
	@PostMapping("/welcomeP")
	public ResponseEntity<String> welcome(){
		
	 return new ResponseEntity<String>("Welcome to Masai App from WelcomeP",HttpStatus.
   ACCEPTED);
	}
		
	
	@GetMapping("/welcome")
	public ResponseEntity<String> welcomeP(){
		
			return new ResponseEntity<String>("Welcome to Masai App from Welcome",HttpStatus
     .ACCEPTED);
	}

	@GetMapping("/admin")
	public ResponseEntity<String> admin(){
		
		return new ResponseEntity<String>("Welcome to Masai App for Admin",HttpStatus.
					 ACCEPTED);
		}
	@GetMapping("/user")
	public ResponseEntity<String> user(){
		
		return new ResponseEntity<String>("Welcome to Masai App for User",HttpStatus.
					 ACCEPTED);
		}
	
}