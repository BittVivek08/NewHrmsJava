package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.LeaveDetails;

public interface ILeaveDetailsRepository extends JpaRepository<LeaveDetails, Integer> {

	//@Query("select totalLeave from com.nt.entity.LeaveDetails t where t.leaveType=:leaveType")
//	public Integer getTotalLeave(@Param("leaveType") String leaveType);
	
	@Query("select totalLeave from com.hrms.entity.LeaveDetails t where t.leaveType=:leaveType")
	public Integer getTotalLeave(@Param("leaveType") String leaveType);
}
