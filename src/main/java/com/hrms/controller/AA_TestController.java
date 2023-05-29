package com.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.entity.Businessunit;
import com.hrms.request.bean.AuthenticationRequest;
import com.hrms.response.bean.AuthenticationResponse;
import com.hrms.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@CrossOrigin
public class AA_TestController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JwtUtil jwtUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		}
		catch(Exception e) {
			throw new Exception ("Incorrect username",e);
		}
		final UserDetails user = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		return ResponseEntity.ok(new AuthenticationResponse(jwtUtil.generateToken(user)));
	}

	@GetMapping("/test")
	public String test() {
		return "hello";
	}
}
