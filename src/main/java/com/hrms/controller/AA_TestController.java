package com.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.EmployeeDto;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.entity.Businessunit;
import com.hrms.request.bean.AuthenticationRequest;
import com.hrms.response.bean.AuthenticationResponse;
import com.hrms.service.EmployeeDetailsService;
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
	private EmployeeDetailsService empService;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authRequest) {
		EntityBeanResponse response = new EntityBeanResponse();
		try {
			String encodedPassword = this.passwordEncoder.encode(authRequest.getPassword());		
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), encodedPassword));
		}
		catch(Exception e) {
			response.setEmployeeDto(null);
			response.setMsg("Login Failed !");
			response.setStatus(false);
			response.setJwtToken(null);
			return ResponseEntity.ok(response);
		}
		final UserDetails user = userDetailsService.loadUserByUsername(authRequest.getUserName());
		EmployeeDto emp = empService.loginViaJWT(authRequest.getUserName());
		response.setEmployeeDto(emp);
		response.setMsg("Login Success");
		response.setStatus(true);
		response.setJwtToken(jwtUtil.generateToken(user));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/test")
	public String test() {
		return "hello";
	}
}
