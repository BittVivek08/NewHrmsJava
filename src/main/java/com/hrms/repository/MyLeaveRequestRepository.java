package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.MyLeaveRequestEntity;

public interface MyLeaveRequestRepository extends JpaRepository<MyLeaveRequestEntity, Integer> {

	
}
