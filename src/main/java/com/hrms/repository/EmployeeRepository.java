package com.hrms.repository;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.EmployeeDetails;

public interface EmployeeRepository extends JpaRepository<EmployeeDetails, String>{

	Optional<EmployeeDetails> findByEmailAndPassword(String email, String password);
	
	EmployeeDetails findByEmail(String email);

	Optional<EmployeeDetails> findById(Integer id);

	public EmployeeDetails findByEmpId(String empid);
	

	EmployeeDetails findByFirstName(String firstName);
	
	EmployeeDetails findByEmpId(String empId);
	
}
