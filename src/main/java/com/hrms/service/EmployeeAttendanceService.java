package com.hrms.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.hrms.beans.EmployeeAttendanceRequest;
import com.hrms.beans.EmployeeAttendancebean;
import com.hrms.entity.EmployeeAttendance;


public interface EmployeeAttendanceService {
		
//	public EmployeeAttendancebean saveAttendanceDetails(EmployeeAttendance employeeattend);
	
	public boolean checkIfCheckedInToday(String empId);
	
	public void saveCheckInTime(String empId ,String ipAddress,String workFrom);
	
	public void saveCheckOutTime(String empId);
	
//	public EmployeeAttendance findByEmpId(int empId);
	
	public List<EmployeeAttendance> getEmployeeWeeklyAttendance(String empId, String startDate, String endDate);
		
	
}
