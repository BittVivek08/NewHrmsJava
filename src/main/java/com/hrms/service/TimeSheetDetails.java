package com.hrms.service;

import java.util.List;

import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.SaveTimeSheet;
import com.hrms.response.bean.ProjectListResponse;
import com.hrms.response.bean.TimeSheetRequestRepDate;
import com.hrms.response.bean.TimeSheetResponse;

public interface TimeSheetDetails {

	public TimeSheetResponse saveTimeSheett(List<SaveTimeSheet> savetimesheetList);

	public TimeSheetResponse UpdateTimeSheett(SaveTimeSheet savetimesheet, int id);

	public List<SaveTimeSheet> getTimeSheetByDate(TimeSheetRequestRepDate date);

	public List<SaveTimeSheet> getTimeSheetByStartDateEndDate(TimeSheetRequestRepDate date);

	public List<EmployeeDetails> getTimeSheetByRpId(String repId);

	public ProjectListResponse getProjectList();

	public List<SaveTimeSheet> getTimeSheetByRpIdEmpId(String repId, String empId);

	public List<SaveTimeSheet> getTimeSheetByMan(TimeSheetRequestRepDate timesheet);

	public List<SaveTimeSheet> getTimeSheett(String empId);

	public List<SaveTimeSheet> getTimeSheetByRpIdDate(TimeSheetRequestRepDate timesheet);

}