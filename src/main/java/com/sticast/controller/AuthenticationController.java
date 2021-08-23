package com.sticast.controller;

import com.sticast.configurations.springsecurity.JwtTokenUtil;
import com.sticast.entity.JwtResponse;
import com.sticast.validation.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.sticast.entity.User;
import com.sticast.service.UserService;
import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    private Logger logger = Logger.getLogger(getClass().getName());

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody User user) {

        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        //TODO Modificare. Usare authenticate.getPrincipal() facendo il cast
        User theUser = userService.findByUsername(user.getUsername());

        logger.info("User " + theUser.getUsername() + " successfully logged in");
        JwtResponse response = new JwtResponse();
        response.setUserId(theUser.getId());
        response.setStatus(200);
        response.setUsername(theUser.getUsername());
        response.setUserBudget(theUser.getBudget());
        response.setToken(jwtTokenUtil.generateAccessToken(theUser));
        response.setExpirationTime(100000);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody CrmUser crmUser) {
        userService.save(crmUser);
        //TODO far tornare a userService.save un User invece di caricarlo dal database con userService.findByUsername
        User theUser = userService.findByUsername(crmUser.getUsername());
        JwtResponse response = new JwtResponse();
        response.setUserId(theUser.getId());
        response.setUsername(theUser.getUsername());
        response.setUserBudget(theUser.getBudget());
        response.setToken(jwtTokenUtil.generateAccessToken(theUser));
        response.setExpirationTime(100000);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}