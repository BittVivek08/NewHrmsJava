package com.hrms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.EmployeeEducationDetails;

public interface EmployeeEducationDetailsRepository  extends JpaRepository<EmployeeEducationDetails, Integer>{
	
	@Query("from EmployeeEducationDetails where employeeDetails.empId=?1")
	Optional<EmployeeEducationDetails> findByEmpId(String empId);

}
