package com.app.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@NotBlank(message = "First Name cannot be blank")
	private String first_name;
	
	@NotBlank(message = "Last Name cannot be blank")
	private String last_name;
	
	@Email(message = "Email Must be in Valid Format")
	@NotBlank(message = "Email Name cannot be blank")
	private String email;
	
	@Pattern(regexp = "^[0-9]{10}" , message = "Mobile Number must be 10 digit")
	@NotBlank(message = "Mobile Number cannot be blank")
	private String mobile;
	
	@Size(min = 8, message = "Password must be of minimum 8 characters.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).*$", message = "Password requirements not met")
	@NotBlank(message = "Password cannot be blank")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@NotBlank(message = "Role cannot be blank")
	private String role;
	
	@NotBlank(message = "Status cannot be blank")
	private String status;
	
}
