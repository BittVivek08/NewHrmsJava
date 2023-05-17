package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.LeaveRequestEntity;

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
}
