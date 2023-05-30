package com.hrms.repository;


import java.text.AttributedString;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.EmployeeLeaveTypeEntity;

import io.swagger.v3.oas.annotations.Parameter;

public interface EmployeeLeaveTypeRepository extends JpaRepository<EmployeeLeaveTypeEntity, Integer> {


	@Query("select id from com.hrms.entity.EmployeeLeaveTypeEntity t where t.leaveType=:leaveType and year=:year")
	public Integer getId(@Param("leaveType") String leaveType, @Param("year") int year );
	
	@Query("from EmployeeLeaveTypeEntity where year=:year")
	public List<EmployeeLeaveTypeEntity> getLeavesBasedOnYear(int year);

	 @Query ("SELECT e.noOfDays FROM EmployeeLeaveTypeEntity e WHERE e.year = YEAR(CURRENT_DATE()) AND leaveType=:leaveType")
	 public float getNoOfDays(@Param(value="leaveType")  String leaveType);
	
	///@Query ("SELECT COALESCE(SUM(l.noOfDays) FROM ")
	
	 
	 
	// @Query("select noOfDays from com.hrms.entity.EmployeeLeaveTypeEntity t where t.leaveType =:leaveType and year=:year")
	//public int getNoOfDaysByType(@Param("leaveType") String leaveType, @Param("year") int year);
		
	 
	
//	 @Query("SELECT (SELECT SUM(m.noOfDays) FROM EmployeeLeaveTypeEntity m WHERE m.year = YEAR(CURRENT_DATE())) - " +
//	            "(SELECT COALESCE(SUM(l.noOfDays), 0) FROM EmployeeLeaveRequestSummaryEntity l WHERE l.emp_id = :emp_id AND " +
//	            "l.leaveStatus = 'Approved' AND YEAR(l.createddate) = YEAR(CURRENT_DATE())) AS total FROM EmployeeLeaveTypeEntity m")
//	public int calculateTotal(@Param("emp_id") String emp_id);
//	
	
	
	
}
























