package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.WorkFlowMngt;

public interface WorkFlowMgntRepository extends JpaRepository<WorkFlowMngt, Integer>{

	@Query("select w.type from WorkFlowMngt w where feature= ?1")
	public String getType(String feature);
	
	@Query("select w.managerLevel from WorkFlowMngt w where feature= ?1")
	public int getManagerLeavel(String feature);
}
