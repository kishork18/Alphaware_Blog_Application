package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.JWTAuthResponse;
import com.blog.dto.LoginDTO;
import com.blog.entity.User;
import com.blog.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthService authServ;
	@Autowired
	private BCryptPasswordEncoder passencode;

	@PostMapping("/register")
	public ResponseEntity<User> registerUserHandler(@RequestBody User user) {
		String hashpass = passencode.encode(user.getPassword());
		user.setPassword(hashpass);
		User us = authServ.registerUser(user);
		return new ResponseEntity<User>(us, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO logindto) {
		String token = authServ.login(logindto);
		JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		return new ResponseEntity<JWTAuthResponse>(jwtAuthResponse, HttpStatus.ACCEPTED);
	}
}
