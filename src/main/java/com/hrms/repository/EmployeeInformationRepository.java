package com.hrms.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.EmployeeInformation;

public interface EmployeeInformationRepository extends JpaRepository<EmployeeInformation, Integer>{
	
//	  public List<EmployeeInformation> findByEmpId(String empId);
	@Query("from EmployeeInformation where employeeDetails.empId=?1")
	Optional<EmployeeInformation> findByEmpId(String empId);


}
