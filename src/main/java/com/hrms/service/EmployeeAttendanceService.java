package com.hrms.service;

import java.util.List;
import com.hrms.beans.EmployeeAttendancebean;
import com.hrms.entity.EmployeeAttendance;
import com.hrms.entity.HolidayCalenderEntity;
import com.hrms.entity.RequestForLeave;


public interface EmployeeAttendanceService {
			
	public boolean checkIfCheckedInToday(String empId);
	
	public EmployeeAttendancebean saveCheckInTime(String empId ,String ipAddress,String workFrom);
	
	public void saveCheckOutTime(String empId);
	
	public List<EmployeeAttendance> getEmployeeWeeklyAttendance(String empId, String startDate, String endDate);

	public boolean findHolidayDetails();
	
	public boolean findWeekends();
	
	public List<HolidayCalenderEntity> findHolidaysByDateRange(String startDate, String endDate);
	
	public List<String> getWeekendsBetweenDates(String startDate, String endDate);
	
	public List<RequestForLeave> getLeaveRecords(String startDate, String endDate);
	
	public boolean getEmployeeOnLeaveToday(String empId);
	
	public EmployeeAttendancebean saveCheckInTimeForcely(String empId, String ipAddress, String workFrom);

	
}
