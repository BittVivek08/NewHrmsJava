package com.hrms.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.EmployeeLeaveTypeEntity;

public interface EmployeeLeaveTypeRepository extends JpaRepository<EmployeeLeaveTypeEntity, Integer> {

	
	
//	@Query("from main_employeeleavetypes where leavetype=:leaveType and year=:year")
//	public List casualOrSickLeaveBasedOnYear(int year, String leaveType);

	@Query("select id from com.hrms.entity.EmployeeLeaveTypeEntity t where t.leaveType=:leaveType and year=:year")
	public Integer getId(@Param("leaveType") String leaveType, @Param("year") int year );
	
	
	@Query("from EmployeeLeaveTypeEntity where year=:year")
	public List<EmployeeLeaveTypeEntity> getLeavesBasedOnYear(int year);

//	 @Query("SELECT (SELECT SUM(m.noOfDays) FROM EmployeeLeaveTypeEntity m WHERE m.year = YEAR(CURRENT_DATE())) - " +
//	            "(SELECT COALESCE(SUM(l.noOfDays), 0) FROM EmployeeLeaveRequestSummaryEntity l WHERE l.emp_id = :emp_id AND " +
//	            "l.leaveStatus = 'Approved' AND YEAR(l.createddate) = YEAR(CURRENT_DATE())) AS total FROM EmployeeLeaveTypeEntity m")
//	public int calculateTotal(@Param("emp_id") String emp_id);
//	
	
	 @Query("SELECT " +
	            "(SELECT SUM(e.noOfDays) FROM EmployeeLeaveTypeEntity e WHERE e.year = YEAR(CURRENT_DATE())) - " +
	            "(SELECT COALESCE(SUM(l.noOfDays), 0) FROM EmployeeLeaveRequestSummaryEntity l WHERE l.emp_id = :emp_id AND " +
	            "l.leaveStatus = 'Approved' AND YEAR(l.createddate) = YEAR(CURRENT_DATE())) AS total " +
	            "FROM EmployeeLeaveTypeEntity e")
	   public double calculateTotalLeaveDays(@Param("emp_id") String emp_id);
}
























