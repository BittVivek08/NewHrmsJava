package com.hrms.service;


import java.util.ArrayList;
import java.util.List;

import com.hrms.beans.EntityBeanResponse;
import com.hrms.entity.SaveTimeSheet;



public interface TimeSheetDetails {
//	public void saveTimeSheet(SaveTimesheetRequestBean saveBean);
//	public void getMonthlyDataInTimeSheet(int userid, String monthStartDate, String monthEndDate);
//	public SavingTimeSheet getTimeSheet(Integer id);
//	public void deleteUser(int id);
	public SaveTimeSheet saveTimeSheett(SaveTimeSheet hrmss);
	public SaveTimeSheet getTimeSheetDetail(int id);
	public void deleteTimeSheet(int id);
	public SaveTimeSheet getTimeSheetDetails(int month, int year, int week,int id);
	public List<Object[]> getTimeSheetDetailsByMonth(int month,int empId,int year);
//	public TimeSheetApprovalStatus timeSheetApproval(TimeSheetApprovalStatus timeSheetApprovalStatus,int empid);
}