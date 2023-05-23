package com.hrms.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.LeaveRequestEntity;
import com.hrms.response.bean.CommonResponseBean;
import com.hrms.response.bean.EmployeeLeaveResponse;
import com.hrms.response.bean.LeavesResponseBean;

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
	
	@Query("From LeaveRequestEntity where isActive=1")
	List<LeaveRequestEntity> listOfAllLeaves();
	
	
	@Query("From LeaveRequestEntity  where emp_id=:emp_id")
	List<LeaveRequestEntity> findByEmp_id(String emp_id);
	
	@Query("from LeaveRequestEntity where isActive=1 and emp_id=:emp_id ")
	public List<LeaveRequestEntity> fetchAppliedLeaveRequest(String emp_id);
	
	
	// Get Employee Details Whose On The Particulars Date leave
//	@Query("SELECT l.userName, l.emp_id, l.fromDate, l.toDate, l.reason, l.reportingManagerId  FROM LeaveRequestEntity l  WHERE l.repMangId = :repId  AND l.fromDate = :date  AND l.leaveStatus = 'Approved'")
//   public  List<LeaveRequestEntity> getLeaveDataByRepIdDate(int repId, Date date);
	
	
	@Query("from LeaveRequestEntity where isActive=1 and year=:year")
	public List<LeaveRequestEntity> getLeavesByYear(String year);
	
	@Query("from LeaveRequestEntity where isActive=1 and id=:id ")
	public List<LeaveRequestEntity> totalLeaveTaken(int id);
	
	
}


