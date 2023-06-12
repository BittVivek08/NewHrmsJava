package com.hrms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.hrms.entity.EmployeeSalaryDetails;

public interface EmployeeSalaryDetailsRepo extends JpaRepository<EmployeeSalaryDetails, Integer>{
	
	@Query("from EmployeeSalaryDetails where employeeDetails.empId=?1")
	List<EmployeeSalaryDetails> findByEmpId(String empId);
	
	@Query("from EmployeeSalaryDetails where employeeDetails.empId=?1")
	public EmployeeSalaryDetails SalByEmpId(String empId);
	
	

}
