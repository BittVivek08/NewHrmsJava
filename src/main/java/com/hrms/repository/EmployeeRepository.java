package com.hrms.repository;

import java.util.List;
import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.EmployeeDetails;

public interface EmployeeRepository extends JpaRepository<EmployeeDetails, String>{

	Optional<EmployeeDetails> findByEmailAndPassword(String email, String password);
	
	EmployeeDetails findByEmail(String email);

	Optional<EmployeeDetails> findById(Integer id);

	public EmployeeDetails findByEmpId(String empid);
	

	EmployeeDetails findByFirstName(String firstName);
    
	
//	List<EmployeeDetails> getDetailByRepId(int repId);
	
	
}
