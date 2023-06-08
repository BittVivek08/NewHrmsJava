package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.EmployeeEducationDetails;


public interface EmployeeEducationRepo extends JpaRepository<EmployeeEducationDetails, Integer>{

}
