package com.hrms.controller;

import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.EmployeeDto;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.entity.RefreshToken;
import com.hrms.repository.EmployeeRepository;
import com.hrms.request.bean.AuthenticationRequest;
import com.hrms.request.bean.RefreshTokenRequest;
import com.hrms.service.EmployeeDetailsService;
import com.hrms.serviceImpl.RefreshTokenService;
import com.hrms.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private EmployeeDetailsService empService;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	RefreshTokenService refreshTokenService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	EmployeeRepository empRepo;

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authRequest) {
		log.info("In AuthenticationTestController authenticate method ");
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
		emp.setJwtToken(jwtUtil.generateToken(user,emp.getEmpId()));
		RefreshToken refreshToken =  refreshTokenService.createRefreshToken(authRequest.getEmail());
		emp.setRefreshToken(refreshToken.getToken());
		response.setEmployeeDto(emp);
		response.setMsg("Login Success");
		response.setStatus(true);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/refreshToken")
	public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
		try {
			EntityBeanResponse response = new EntityBeanResponse();
			Optional<RefreshToken> refreshToken = refreshTokenService.findByToken(request.getToken());
			
			if(refreshToken.isPresent() && refreshToken != null) {
				RefreshToken refreshToken1 = refreshTokenService.verifyExpiration(refreshToken.get());
				final UserDetails user = userDetailsService.loadUserByUsername(refreshToken1.getEmployeeDetails().getEmail());
				String accessToken = jwtUtil.generateToken(user,refreshToken1.getEmployeeDetails().getEmpId());
				EmployeeDto emp = empService.loginViaJWT(refreshToken1.getEmployeeDetails().getEmail());
				emp.setJwtToken(accessToken);
				response.setEmployeeDto(emp);
				response.setMsg("Login Success");
				response.setStatus(true);
				return ResponseEntity.ok(response);
			}
			return ResponseEntity.ok("Refresh Token has expired");
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping("/test")
	public String test() {
		return "hello";
	}
	@PostMapping("/test")
	public String test1() {
		return "hello";
	}

}
