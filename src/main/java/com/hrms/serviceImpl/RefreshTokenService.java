package com.hrms.serviceImpl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.RefreshToken;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.RefreshTokenRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RefreshTokenService {

	@Autowired
	RefreshTokenRepository refreshTokenRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	public RefreshToken createRefreshToken(String email) {
		
		RefreshToken refreshToken = new RefreshToken();
		EmployeeDetails employeeDetails = employeeRepository.findByEmail(email);
		
		refreshToken.setEmployeeDetails(employeeDetails);
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken.setExpiryDate(Instant.now().plusMillis(600000));
		
		return refreshTokenRepository.save(refreshToken);
	}
	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}
	
	public RefreshToken verifyExpiration(RefreshToken refreshToken) {
		if(refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(refreshToken);
			log.error(refreshToken.getToken() + " Refresh Token has expired ");
			return null;
		}
		return refreshToken;
	}
}
