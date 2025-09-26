package com.pros.service;

import com.pros.annotation.Privilege;
import org.springframework.stereotype.Service;

@Service
public class DataService {

	@Privilege(privileges = {"read_data"}, message = "Unauthorized")
	public String fetchData() {
		return "Data fetched";
	}

	@Privilege(privileges = {"write_data"}, message = "Unauthorized")
	public String writeData() {
		return "Data written";
	}

	@Privilege(privileges = {"admin_access"}, message = "naaah")
	public String adminOperation(){
		return "Performing Admin Operations!!!!";
	}

}
