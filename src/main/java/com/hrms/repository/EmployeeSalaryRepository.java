package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.EmployeeInformation;
import com.hrms.entity.EmployeeSalaryDetails;

public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalaryDetails, Integer>{

	
	// @JoinColumn(name = "emp_id",referencedColumnName = "emp_id")   
}
