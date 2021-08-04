package com.sticast.validation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
@Data
public class CrmUser {

	@NotNull(message = "Please insert your username")
	@Size(min = 4, message = "Username must be at least 4 characters")
	private String username;

	@NotNull(message = "Please insert your password")
	@Size(min = 4, message = "Password must be at least 4 characters")
	private String password;
	
	@NotNull(message = "Please repeat your password")
	private String matchingPassword;

	@NotNull(message = "Please insert an email")
	@Email(message = "Please insert a valid email")
	private String email;
}