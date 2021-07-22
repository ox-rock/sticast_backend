package com.sticast.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.sticast.validation.FieldMatch;
import com.sticast.validation.ValidEmail;
import lombok.Data;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
@Data
public class CrmUser {

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String username;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String matchingPassword;

	@ValidEmail
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String email;
}