package com.hrms.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.MyLeaveRequestEntity;

public interface MyLeaveRequestRepository extends JpaRepository<MyLeaveRequestEntity, Integer> {
	
	@Query("from MyLeaveRequestEntity l where l.emp_id=:eid")
	public MyLeaveRequestEntity findByEmpid(@Param("eid") String eid);
	
	@Query("select email from MyLeaveRequestEntity m where m.emp_id=:eid")
	public String mailIdofEmp(@Param("eid") String eid);
	
	
//	@Query("select * from MyLeaveRequestEntity l where l.emp_id=:eid")
//	public MyLeaveRequestEntity findByEmp_id(@Param("eid") String eid);
	
	
	//@Query( "select leavetypeid from main_leaverequest where from_date>='" + fromDate + "' and to_date<='"
		//		+ toDate + "' and user_id=" + userId + "")
	
	// @Query("SELECT lr.leaveType FROM MyLeaveRequestEntity lr WHERE lr.fromDate >= :fromDate AND lr.toDate <= :toDate AND lr.emp_id = :emp_id")
	//    List<MyLeaveRequestEntity> findLeaveTypeIdByDatesAndUserId(@Param("fromDate") LocalDate date, @Param("toDate") LocalDate date2, @Param("emp_id") String emp_id);
	
	 
	
	 @Query("SELECT CASE WHEN COUNT(l) > 0 THEN TRUE ELSE FALSE END " +
	           "FROM MyLeaveRequestEntity l " +
	           "WHERE l.emp_id = :emp_id " +
	           "AND l.toDate <= :fromDate " +
	           "AND l.fromDate >= :toDate ")
	    boolean checkMatchingDates(@Param("emp_id")String emp_id,@Param("toDate") LocalDate startDate,@Param("fromDate") LocalDate endDate);
    @Query("From MyLeaveRequestEntity where emp_id=?1")
	public List<MyLeaveRequestEntity> findleaveStatus(String empId);
    
    
    //get leaves details by year
    @Query("from MyLeaveRequestEntity where YEAR(fromDate)=:year")
    public List<MyLeaveRequestEntity> getLeavaesByYear(int year); 
    
    @Query("from MyLeaveRequestEntity where MONTH(fromDate)=:month")
    public List<MyLeaveRequestEntity> getLeavesByMonth(int month);
	 
    @Query("from MyLeaveRequestEntity where leaveStatus=:status")
    public List<MyLeaveRequestEntity> getLeavesByStatus(String status);
} 


