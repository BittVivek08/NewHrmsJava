package com.hrms.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.RequestForLeave;

public interface IRequestForLeaveRepository extends JpaRepository<RequestForLeave, Integer> {

	@Query("select min(avalCasualLeave) from com.hrms.entity.RequestForLeave t where t.emp_id=:emp_id")
	public Integer minCasualLeave(String emp_id);
	
	@Query("select min(avalSickLeave) from com.hrms.entity.RequestForLeave t where t.emp_id=:emp_id")
	public Integer minSickLeave(String  emp_id);

	@Query("select min(avalSickLeave) from com.hrms.entity.RequestForLeave t where t.mappingId=:mappingId")
	public Integer minSickLeave(int mappingId);
    @Query("select startDate from RequestForLeave where startDate=?1")
	public LocalDate findByStartDate(LocalDate date);

	
	@Query("select details From RequestForLeave where emp_id=?1")
    public String findByEmpId(String emp);
}
