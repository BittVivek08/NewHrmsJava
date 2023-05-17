package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.LeaveManagementEntity;

public interface LeaveManagementRepository extends JpaRepository<LeaveManagementEntity, Integer> {

}
