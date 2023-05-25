package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
	
	@Query("from Department where businessId=:businessId")
	public List<Department> listOfDuBasedOnBu(int businessId);

}
