package com.pros.controller;

import com.pros.configuration.PrivilegeAuth;
import com.pros.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

@RestController(value = "privilege")
public class PrivilegeController {

	static String[] userPrivileges = {"read_data","write_data","admin_access"};

	@Autowired
	private DataService dataService;

	@Autowired
	private PrivilegeAuth privilegeAuth;

	@GetMapping("/unAuth")
	public String fetchUnAuthData() throws NoSuchMethodException {
			return dataService.fetchData();
	}

	@GetMapping("/data")
	public String fetchData() throws NoSuchMethodException {
		Method method = DataService.class.getMethod("fetchData");
		if (privilegeAuth.hasAuthorized(userPrivileges, "read_data", method))
			return dataService.fetchData();
		return "You are not authorized to fetch data.";
	}

	@GetMapping("/write")
	public String writeData() throws NoSuchMethodException {
		Method method = DataService.class.getMethod("writeData");
		if (privilegeAuth.hasAuthorized(userPrivileges, "write_data", method))
			return dataService.writeData();
		return "You are not authorized to write data.";
		}

}
