package com.app.service;

import java.util.List;
import java.util.Map;

import com.app.modal.UserLoginRequest;
import com.app.modal.Users;

public interface UsersService {

	public Map<String, Object> registerUser(Users user);
	public List<Users> getAllUsers();
	public Users getUserDetails(String email);
	public Map<String, Object> login(UserLoginRequest request);
}
