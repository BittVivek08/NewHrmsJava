package com.hrms.serviceImpl;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hrms.entity.EmployeeDetails;
import com.hrms.repository.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		EmployeeDetails employeeDetails = employeeRepository.findByEmail(username);
		return new User(employeeDetails.getEmail(),employeeDetails.getPassword(),new ArrayList<>());
	}

}
