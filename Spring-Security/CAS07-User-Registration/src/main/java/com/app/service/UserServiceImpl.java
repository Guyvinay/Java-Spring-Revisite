package com.app.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.exceptions.UserNotFound;
import com.app.modal.UserLoginRequest;
import com.app.modal.Users;
import com.app.repository.UsersRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class UserServiceImpl implements UsersService {

	@Autowired
    private Key secretKey;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Map<String, Object>  registerUser(Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_"+user.getRole().toUpperCase());
		 Users save = usersRepository.save(user);
		 
		 Map<String, Object> responseBody = new HashMap<>();
		    responseBody.put("status", 200);
			responseBody.put("message", "Logged in Successfully");
			responseBody.put("userData", save);
			
			return responseBody;
	}

	@Override
	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}

	@Override
	public Users getUserDetails(String email) {
		return usersRepository.findByEmail(email)
				.orElseThrow( ()->
				new UserNotFound("User Not Found"));
		
	}

	@Override
	public Map<String, Object> login(UserLoginRequest request) {

		String email = request.getEmail();
		String password = request.getPassword();
		Users user = usersRepository.findByEmail(email)
		.orElseThrow( ()->
		new UserNotFound("User Not Found"));
		
		if(passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			String token = generateToken(user);
			System.out.println(token);
			
			Map<String, Object> responseBody = new HashMap<>();
			Map<String, Object> data = new HashMap<>();

			responseBody.put("status", 200);
			responseBody.put("message", "Logged in Successfully");
			data.put("UserDetails", user);
			data.put("token", token);
            responseBody.put("data", data);
            return responseBody;
		}
		else {
			throw new UserNotFound("Wrong Creadentials");
		}
	}
	
	public String generateToken(Users user) {
		Date now = new Date();
		System.out.println(now);
		Date valid = new Date(System.currentTimeMillis() + 30L * 24 * 3600 * 1000);
		System.out.println(valid);
		String token =  Jwts
				           .builder()
				           .setSubject(user.getEmail())
				           .claim("role", user.getRole())
				           .setIssuedAt(now)
				           .setExpiration(valid)
				           .signWith(secretKey)
				           .compact();
		return token;
	}

}
