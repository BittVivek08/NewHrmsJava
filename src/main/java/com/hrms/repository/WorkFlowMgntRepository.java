package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.WorkFlowMngt;

public interface WorkFlowMgntRepository extends JpaRepository<WorkFlowMngt, Integer>{

	@Query("select w.type from WorkFlowMngt w where feature= 'leave' ")
	public String getType();
	
	@Query("select w.managerLevel from WorkFlowMngt w where feature= 'leave' ")
	public int getManagerLeavel();
}
