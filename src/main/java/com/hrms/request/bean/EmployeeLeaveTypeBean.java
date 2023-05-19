package com.hrms.request.bean;

import lombok.Data;

@Data
public class EmployeeLeaveTypeBean {

	private int id;
	private String leaveType;
	private int noOfDays;
	private int year;
	private String emp_id;
	private String leaveCode;
	private int leaveTypeId;
	private int noOfDaysMonth;
}
