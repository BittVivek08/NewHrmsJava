package com.hrms.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.EmployeeInformation;

public interface EmployeeInformationRepository extends JpaRepository<EmployeeInformation, Integer>{
	
//	  public List<EmployeeInformation> findByEmpId(String empId);


}
