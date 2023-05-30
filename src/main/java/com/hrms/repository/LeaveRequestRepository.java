package com.hrms.repository;


import java.util.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.LeaveRequestEntity;
import com.hrms.request.bean.LeaveDetailsFiltaring;

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
	
	
	
//	   @Query("from ")
//	   public  List<LeaveRequestEntity> getLeaveDataByRepIdDate(int repId, Date date);
   
	// Get Employee Details Whose On The Particulars Date leave
	@Query("FROM LeaveRequestEntity where isActive=1 and leaveStatus = 'Approved'")
    List<LeaveRequestEntity> getLeaveDataByRepIdDate( String date);
	
	@Query("SELECT m.userName, m.user_id, m.fromDate, m.toDate, m.reason, m.reportingManagerId FROM LeaveRequestEntity m WHERE m.reportingManagerId = :reqId AND m.fromDate = :date AND m.leaveStatus = 'Approved'")
	List<LeaveRequestEntity> findApprovedLeaveRequests(int reqId, String date);
	
	
	@Query("from LeaveRequestEntity where isActive=1 and year=:year")
	public List<LeaveRequestEntity> getLeavesByYear(String year);
	
	@Query("from LeaveRequestEntity where isActive=1 and id=:id ")
	public List<LeaveRequestEntity> totalLeaveTaken(int id);
	
	
	//get leave datails based on conditons
	@Query("from LeaveRequestEntity where isActive=1 and id=:id")
     public List<LeaveDetailsFiltaring> findById(int id);
	
	@Query("from LeaveRequestEntity where isActive=1 and leavestatus=:leaveStatus")
	public List<LeaveDetailsFiltaring> findByLeaveStatusCondition(String leaveStatus);
	
	
	//get leave based on month
	@Query("from LeaveRequestEntity where isActive=1 and user_id=:id and month(fromDate)=:month")
	public List<LeaveRequestEntity> getLeaveByMonth(int id,int month);
	
	@Query("from LeaveRequestEntity where isActive=1 and user_id=:id and month(fromDate)=:month and leaveStatus=:leavestatus")
	public List<LeaveRequestEntity> getLeaveByMonthAndStatus(int id,int month,String leavestatus);
    
	
	//get leave details by manager id
	//@Query("from LeaveRequestEntity where isActive=1 and reportingManagerId=:managerId and leaveStatus=:leavestatus")
	//public List<LeaveRequestEntity> getLeaveDetailsByManagerId(int managerId,String leavestatus);

       public List<LeaveRequestEntity> findByReportingManagerIdAndLeaveStatus(int managerId, String leaveStatus);
       
       @Query("from LeaveRequestEntity where reportingManagerId=:mid and leave_status=:leavestatus")
       public List<LeaveRequestEntity> findByRepManId(int mid,String leavestatus);

}


