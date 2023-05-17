package com.hrms.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hrms.beans.CurrentWeekDatesResponse;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.entity.SaveTimeSheet;
import com.hrms.responsebean.DateResponseTimeSheet;
import com.hrms.responsebean.TimeSheetResponse;
import com.hrms.responsebean.TimeSheetResponseForMonthYearWeek;

public interface TimeSheetDetails {
//	public void saveTimeSheet(SaveTimesheetRequestBean saveBean);
//	public void getMonthlyDataInTimeSheet(int userid, String monthStartDate, String monthEndDate);
//	public SavingTimeSheet getTimeSheet(Integer id);
//	public void deleteUser(int id);
	public TimeSheetResponse saveTimeSheett(SaveTimeSheet hrmss);
	public SaveTimeSheet getTimeSheetDetail(int id);
	public void deleteTimeSheet(int id);
	public SaveTimeSheet getTimeSheetDetails(int month, int year, int week,int id);
	public List<Object[]> getTimeSheetDetailsByMonth(int month,String empId,int year);
//	public TimeSheetApprovalStatus timeSheetApproval(TimeSheetApprovalStatus timeSheetApprovalStatus,int empid);
	public DateResponseTimeSheet dateResponse(int year, int calWeek);
	public CurrentWeekDatesResponse getWeekDates(LocalDate date);
}