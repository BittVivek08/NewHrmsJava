package com.hrms.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.MyLeaveRequestEntity;

public interface MyLeaveRequestRepository extends JpaRepository<MyLeaveRequestEntity, Integer> {

	
	
	//@Query( "select leavetypeid from main_leaverequest where from_date>='" + fromDate + "' and to_date<='"
		//		+ toDate + "' and user_id=" + userId + "")
	
	 @Query("SELECT lr.leaveTypeId FROM MyLeaveRequestEntity lr WHERE lr.fromDate >= :fromDate AND lr.toDate <= :toDate AND lr.emp_id = :emp_id")
	    List<Long> findLeaveTypeIdByDatesAndUserId(@Param("fromDate") LocalDate date, @Param("toDate") LocalDate date2, @Param("emp_id") String emp_id);
	
}
