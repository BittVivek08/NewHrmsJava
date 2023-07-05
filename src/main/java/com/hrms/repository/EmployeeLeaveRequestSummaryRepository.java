package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.EmployeeLeaveRequestSummaryEntity;
import com.hrms.request.bean.LeaveDetailsFiltaring;

public interface EmployeeLeaveRequestSummaryRepository extends JpaRepository<EmployeeLeaveRequestSummaryEntity, Integer> {




	@Query("SELECT COALESCE(SUM(ls.noOfDays),0) FROM EmployeeLeaveRequestSummaryEntity ls WHERE ls.emp_id = :emp_id AND ls.leaveType = :leaveType AND ls.leaveStatus = 'approved'")
	public float getNoOfDaysApproved(@Param("emp_id") String emp_id, @Param("leaveType") String leaveType);
	
	@Query("SELECT COALESCE(SUM(ls.noOfDays),0) FROM EmployeeLeaveRequestSummaryEntity ls WHERE ls.emp_id = :emp_id AND ls.leaveType = :leaveType AND ls.leaveStatus = 'pending'")
	public float getNoOfDaysPending(@Param("emp_id") String emp_id, @Param("leaveType") String leaveType);

	
	
	// My Leave, Employee Leave, Employee Leave Summary
	@Query("From EmployeeLeaveRequestSummaryEntity where isActive=1 and reportingManagerId=:emp_id and leaveStatus=:leavestatus")
	List<EmployeeLeaveRequestSummaryEntity> listOfLeavesByUid(String emp_id,String leavestatus);

	// My Leave, Employee Leave, Employee Leave Summary
	@Query("From EmployeeLeaveRequestSummaryEntity  where isActive=1 and leaveStatus=:leavestatus")
	List<EmployeeLeaveRequestSummaryEntity> listOfLeavesByLeavestatus(String leavestatus);

	@Query("From EmployeeLeaveRequestSummaryEntity where isActive=1")
	List<EmployeeLeaveRequestSummaryEntity> listOfAllLeaves();


	@Query("From EmployeeLeaveRequestSummaryEntity  where emp_id=:emp_id")
	List<EmployeeLeaveRequestSummaryEntity> findByEmp_id(String emp_id);

	@Query("from EmployeeLeaveRequestSummaryEntity where isActive=1 and emp_id=:emp_id ")
	public List<EmployeeLeaveRequestSummaryEntity> fetchAppliedLeaveRequest(String emp_id);
       
	

	@Query("from EmployeeLeaveRequestSummaryEntity where isActive=1 and year=:year")
	public List<EmployeeLeaveRequestSummaryEntity> getLeavesByYear(String year);

	@Query("from EmployeeLeaveRequestSummaryEntity where isActive=1 and id=:id ")
	public List<EmployeeLeaveRequestSummaryEntity> totalLeaveTaken(int id);

	public EmployeeLeaveRequestSummaryEntity findById(int id);

	//get leave datails based on conditons
	@Query("from EmployeeLeaveRequestSummaryEntity where isActive=1 and id=:id")
	public List<LeaveDetailsFiltaring> getById(int id);

	@Query("from EmployeeLeaveRequestSummaryEntity where isActive=1 and leavestatus=:leaveStatus")
	public List<LeaveDetailsFiltaring> findByLeaveStatusCondition(String leaveStatus);

	
	public List<EmployeeLeaveRequestSummaryEntity> findByReportingManagerIdAndLeaveStatus(int managerId, String leaveStatus);

	@Query("from EmployeeLeaveRequestSummaryEntity  where reportingManagerId=:mid and leave_status=:leavestatus")
	public List<EmployeeLeaveRequestSummaryEntity> findByRepManId(int mid,String leavestatus);

	@Query("from EmployeeLeaveRequestSummaryEntity where emp_id=emp_id")
	public List<EmployeeLeaveRequestSummaryEntity> findByEmpId(@Param(value ="emp_id")String emp_id);

	@Query("from EmployeeLeaveRequestSummaryEntity where leave_status=leavestatus")
	public List<EmployeeLeaveRequestSummaryEntity> findByLeaveStatus(String leavestatus);


	//public EmployeeLeaveRequestSummaryEntity findByLeaveReqId(int id);

}
