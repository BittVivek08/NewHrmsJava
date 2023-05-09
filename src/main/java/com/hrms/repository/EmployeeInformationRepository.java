package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.EmployeeInformation;
import com.hrms.entity.EmployeeSalaryDetails;

public interface EmployeeInformationRepository extends JpaRepository<EmployeeInformation, Integer>{
	
//	  public List<EmployeeInformation> findByEmpId(String empId);


}
