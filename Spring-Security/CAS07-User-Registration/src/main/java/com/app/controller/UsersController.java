package com.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.modal.UserLoginRequest;
import com.app.modal.Users;
import com.app.service.JwtUtils;
import com.app.service.UsersService;

import jakarta.validation.Valid;

/*
//login
{
   "email":"vinay@gmail.com",
   "password":"Vinay@1234"
}


{
    "status":"active",
    "role":"ADMIN",
    "password":"User@1234",
    "mobile":"1234567890",
    "email":"userone@gmail.com",
    "last_name":" One ",
    "first_name":"User"
}
*/

@RestController
@RequestMapping(value = "/users")
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping(value = "/register")
	public ResponseEntity<Map<String, Object>> saveUser(@Valid @RequestBody Users user){
		return new ResponseEntity<Map<String, Object>>(usersService.registerUser(user),HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/getAllUsers")
	public ResponseEntity<List<Users>> getAllUsers(){
		return new ResponseEntity<List<Users>>(usersService.getAllUsers(),HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/getByEmail/{email}")
	public ResponseEntity<Users> getUserByEmai(@PathVariable("email")String email){
		return new ResponseEntity<Users>(usersService.getUserDetails(email),HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value="/details")
	public ResponseEntity<?> getUserByJwtToke(@RequestHeader("Authorization")String token){
		String jwtToken = token.replace("Bearer ", "");
		String username = jwtUtils.extractUserName(jwtToken);
		Users user = usersService.getUserDetails(username);
		return ResponseEntity.ok(user);
	}
	
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserLoginRequest request){
		Map<String,Object> login = usersService.login(request);
		if(login!=null) return ResponseEntity.ok(login);
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password.");
	}
	
}
