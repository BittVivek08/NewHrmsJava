package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.WorkFlow;

public interface WorkFlowRepository extends JpaRepository<WorkFlow, Integer> {

	List<WorkFlow> findByEmpid(String empid);
	
	List<WorkFlow> findByApprovalManagerIdAndStatus(String approvalManagerId,String status);
	
	List<WorkFlow> findByStatus(String status);
	
	List<WorkFlow> findAll();
	
	List<WorkFlow> findByEmpidAndApprovalManagerIdAndStatus(String empid,String approvalManagerId,String status);
	
	List<WorkFlow> findByEmpidAndStatus(String empid, String status);
	
}
