package com.hrms.service;

import java.util.List;

import com.hrms.beans.EmployeeAttendancebean;
import com.hrms.entity.EmployeeAttendance;

public interface EmployeeAttendanceService {
		
//	public EmployeeAttendancebean saveAttendanceDetails(EmployeeAttendance employeeattend);
	
	public boolean checkIfCheckedInToday(String empId);
	
	public EmployeeAttendancebean saveCheckInTime(String empId ,String ipAddress,String workFrom);
	
	public void saveCheckOutTime(String empId);
	
//	public HolidayCalendarEntity getHolidays (LocalDate date);
	
//	public EmployeeAttendance findByEmpId(int empId);
	
	public List<EmployeeAttendance> getEmployeeWeeklyAttendance(String empId, String startDate, String endDate);

	public boolean findHolidayDetails();
	
	public boolean findWeekends();
		
	
}
