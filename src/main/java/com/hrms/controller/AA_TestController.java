package com.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@CrossOrigin(origins = "http://localhost:4200")
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
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), encodedPassword));
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
		}
		final UserDetails user = userDetailsService.loadUserByUsername(authRequest.getEmail());
		EmployeeDto emp = empService.loginViaJWT(authRequest.getEmail());
		emp.setJwtToken(jwtUtil.generateToken(user));
		response.setEmployeeDto(emp);
		response.setMsg("Login Success");
		response.setStatus(true);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/test")
	public String test() {
		return "hello";
	}
}
