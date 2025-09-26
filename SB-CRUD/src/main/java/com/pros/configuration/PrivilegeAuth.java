package com.pros.configuration;

import com.pros.annotation.Privilege;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Arrays;

@Service
public class PrivilegeAuth {

	static String[] userPrivileges = {"read_data","write_data","admin_access"};

	public boolean hasAuthorized(String[] privileges, String required_privilege, Method method){
		Privilege privilegeAnnotation =  method.getAnnotation(Privilege.class);
		if(privilegeAnnotation!=null) {
			String[] requiredPrivileges = privilegeAnnotation.privileges();
			for (String privilege : requiredPrivileges) {
				if (privilege.equals(required_privilege))
					return true;
			}
		}
		return false;
	}
	public boolean hasAnyAuthorized(String[] privileges, String required_privilege){
		return Arrays.stream(privileges).anyMatch(privilege->privilege.equals(required_privilege));
		// return Arrays.asList(privileges).contains(required_privilege);
	}


}
