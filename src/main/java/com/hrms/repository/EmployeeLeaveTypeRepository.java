package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.EmployeeLeaveTypeEntity;

public interface EmployeeLeaveTypeRepository extends JpaRepository<EmployeeLeaveTypeEntity, Integer> {

}
