package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.EmployeeSalaryDetails;

public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalaryDetails, Integer>{

	
  public List<EmployeeSalaryDetails> findByEmpName(String empName);

}
