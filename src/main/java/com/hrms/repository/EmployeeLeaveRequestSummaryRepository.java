package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.EmployeeLeaveRequestSummaryEntity;

public interface EmployeeLeaveRequestSummaryRepository extends JpaRepository<EmployeeLeaveRequestSummaryEntity, Integer> {

	
@Query("SELECT COALESCE(SUM(l.noOfDays), 0) FROM EmployeeLeaveRequestSummaryEntity l WHERE l.emp_id = :emp_id AND " +
           "l.leaveStatus = 'Approved' AND YEAR(l.createddate) = YEAR(CURRENT_DATE())")
public float getNoOfDaysApproved (@Param("emp_id") String emp_id);
}
