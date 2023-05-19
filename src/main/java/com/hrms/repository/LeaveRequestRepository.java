package com.hrms.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.LeaveRequestEntity;
import com.hrms.response.bean.EmployeeLeaveResponse;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequestEntity, Integer> {

	
//	 // My Leave, Employee Leave, Employee Leave Summary
//	@Query("From LeaveRequestEntity where isActive=1 and emp_id=:emp_id")
//	List<LeaveRequestEntity> listOfLeavesByUid(String emp_id);
	
	// My Leave, Employee Leave, Employee Leave Summary
	@Query("From LeaveRequestEntity where isActive=1 and reportingManagerId=:emp_id and leaveStatus=:leavestatus")
	List<LeaveRequestEntity> listOfLeavesByUid(String emp_id,String leavestatus);
	
	// My Leave, Employee Leave, Employee Leave Summary
	@Query("From LeaveRequestEntity  where isActive=1 and leaveStatus=:leavestatus")
	List<LeaveRequestEntity> listOfLeavesByLeavestatus(String leavestatus);
	
	@Query("From LeaveRequestEntity  where emp_id=:emp_id")
	List<LeaveRequestEntity> findByEmp_id(String emp_id);
	
	@Query("from LeaveRequestEntity where isActive=1 and emp_id=:emp_id ")
	public List<LeaveRequestEntity> fetchAppliedLeaveRequest(String emp_id);
	
//	// Get Employee Details Whose On The Particulars Date leave
//	@Query("select user_name,user_id,from_date,to_date,reason,rep_mang_id from main_leaverequest_summary where from_date=:date and leavestatus='Approved'")
//	public List<EmployeeLeaveResponse> getLeaveDataByRepIdDate(int repId, Date date);
//	
	
}


