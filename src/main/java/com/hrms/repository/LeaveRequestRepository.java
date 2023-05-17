package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.LeaveRequestEntity;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequestEntity, Integer> {

}
