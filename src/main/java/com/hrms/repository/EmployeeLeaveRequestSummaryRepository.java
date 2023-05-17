package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.EmployeeLeaveRequestSummaryEntity;

public interface EmployeeLeaveRequestSummaryRepository extends JpaRepository<EmployeeLeaveRequestSummaryEntity, Integer> {

}
