package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.EmpEducationDetailsEntity;

public interface EmployeeEducationRepo extends JpaRepository<EmpEducationDetailsEntity, Integer>{

}
