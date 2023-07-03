package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.AssignLeaveManagement;

public interface AssignLeaveManagementRepo extends  JpaRepository<AssignLeaveManagement , Integer> {
	
	 @Query("select monthNumber  from AssignLeaveManagement where isActive=1 and emp_id=:empid")
	 public int getMonthValue(String empid);
	 
	 
	 
	 @Query("select noOfDaysMonth from AssignLeaveManagement where isActive=1 and emp_id=:empid")
	 public int getNoForDaysMonth(String empid);
	 

	 @Query("from AssignLeaveManagement where isActive=1 and emp_id=:empid ")
	 public AssignLeaveManagement getdetails(String empid);
	
	 @Query("select emp_id from AssignLeaveManagement where  isActive=1 ")
	 public List<String> getEmpIds();
}
