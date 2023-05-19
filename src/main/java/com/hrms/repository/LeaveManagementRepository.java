package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.LeaveManagementEntity;

public interface LeaveManagementRepository extends JpaRepository<LeaveManagementEntity, Integer> {

	
	@Query("From LeaveManagementEntity where isActive=1")
	public List<LeaveManagementEntity> listOfLeaveManagementOptions();
}
