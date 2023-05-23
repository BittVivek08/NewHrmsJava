package com.hrms.service;


import java.time.LocalDate;
import java.util.List;

import javax.ws.rs.QueryParam;

import com.hrms.beans.CurrentWeekDatesResponse;
import com.hrms.entity.SaveTimeSheet;
import com.hrms.response.bean.CommonTimeSheetbean;
import com.hrms.response.bean.DateResponseTimeSheet;
import com.hrms.response.bean.TimeSheetResponse;

public interface TimeSheetDetails {
//	public void saveTimeSheet(SaveTimesheetRequestBean saveBean);
//	public void getMonthlyDataInTimeSheet(int userid, String monthStartDate, String monthEndDate);
//	public SavingTimeSheet getTimeSheet(Integer id);
//	public void deleteUser(int id);
	public TimeSheetResponse saveTimeSheett(List<SaveTimeSheet>  hrmss);
	public SaveTimeSheet getTimeSheetDetail(int id);
	public void deleteTimeSheet(int id);
	public List<SaveTimeSheet> getTimeSheetDetails(int month, int year, int week,int id);
	public List<Object[]> getTimeSheetDetailsByMonth(int month,String empId,int year);
//	public TimeSheetApprovalStatus timeSheetApproval(TimeSheetApprovalStatus timeSheetApprovalStatus,int empid);
	public DateResponseTimeSheet dateResponse(int year, int calWeek);
	public CurrentWeekDatesResponse getWeekDates(LocalDate date);
	public CommonTimeSheetbean getEmployeesWhoDidNotAccessTimeSheet(@QueryParam("year") int year, @QueryParam("month") int month);
	public TimeSheetResponse getEmplTimeSheetDetailsByReportingManagerId(int repId, String status) ;
	
}