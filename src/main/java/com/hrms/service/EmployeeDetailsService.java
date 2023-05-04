package com.hrms.service;

import java.util.List;

import com.hrms.beans.EmpBirthResponse;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.LoginDto;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.EmployeeSalaryDetails;


public interface EmployeeDetailsService {
	
	public EntityBeanResponse saveEmpDetails(EmployeeDetails employeeDetails);
	
	public List<EmployeeDetails> getAllEmpDetails();
	
	public EmployeeDetails getEmpById(Integer id);
	
	public EntityBeanResponse updateEmpDetails(EmployeeDetails employeeDetails);
	
	public List<EmpBirthResponse> findBirthdayDetails();

	public EntityBeanResponse loginEmployee(LoginDto loginDto);
	
	public EntityBeanResponse saveSalaryDetails(EmployeeSalaryDetails empSalaryDetails);
	
	public EntityBeanResponse updateSalaryDetails(EmployeeSalaryDetails empSalaryDetails);
	
	public List<EmployeeSalaryDetails> getSalaryByEmpName(String empName);
	

	
	
	


}
