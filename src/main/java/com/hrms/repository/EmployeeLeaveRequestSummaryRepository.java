package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.EmployeeLeaveRequestSummaryEntity;

public interface EmployeeLeaveRequestSummaryRepository extends JpaRepository<EmployeeLeaveRequestSummaryEntity, Integer> {

	
//@Query("SELECT COALESCE(SUM(l.noOfDays), 0) FROM EmployeeLeaveRequestSummaryEntity l WHERE l.emp_id = :emp_id AND " +
//           "l.leaveStatus = 'Approved' AND leaveType=:leaveType")
//public float getNoOfDaysApproved (@Param("emp_id") String emp_id,@Param(value="leaveType") String leaveType);


// @Query("SELECT COALESCE(SUM(ls.noOfDays),0) FROM EmployeeLeaveRequestSummaryEntity ls WHERE ls.leaveStatus = 'approver' AND ls.leaveType = :leaveType AND ls.emp_id = :emp_id")
// public float getNoOfDaysApproved(@Param("emp_id") String empid , @Param("leaveType") String leaveType);
	
	 @Query("SELECT COALESCE(SUM(ls.noOfDays),0) FROM EmployeeLeaveRequestSummaryEntity ls WHERE ls.emp_id = :emp_id AND ls.leaveType = :leaveType AND ls.leaveStatus = 'approved'")
	public float getNoOfDaysApproved(@Param("emp_id") String emp_id, @Param("leaveType") String leaveType);
	
//
//	@Query("SELECT SUM(no_of_days) AS total_days"
//			+ "FROM main_leaverequest_summary"
//			+ "WHERE leave_status = :'approved'"
//			+ "  AND leaveType = :'leaveType'"
//			+ "  AND emp_id = :emp_id ")
//	public float getNoOfDaysApproved(@Param("emp_id") String empid , @Param("leaveType") String leaveType);
//	
}
