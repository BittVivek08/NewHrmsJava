package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.RequestForLeave;

public interface IRequestForLeaveRepository extends JpaRepository<RequestForLeave, Integer> {

	@Query("select min(avalCasualLeave) from com.hrms.entity.RequestForLeave t where t.mappingId=:mappingId")
	public Integer minCasualLeave(int  mappingId);
	
	@Query("select min(avalSickLeave) from com.hrms.entity.RequestForLeave t where t.mappingId=:mappingId")
	public Integer minSickLeave(int mappingId);
	

}
