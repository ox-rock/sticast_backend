package com.sticast.controller;

import com.sticast.configurations.springsecurity.JwtTokenUtil;
import com.sticast.validation.CrmUser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.sticast.entity.User;
import com.sticast.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;
	private final UserService userService;

	public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody User user) {
		try {
			Authentication authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

			//TODO Modificare. Usare authenticate.getPrincipal() facendo il cast
			User theUser = userService.findByUsername(user.getUsername());

			return ResponseEntity.ok()
					.header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(theUser))
					.body(""); //TODO Cosa dovrebbe tornare il body?
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody CrmUser crmUser) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}


}