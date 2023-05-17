package com.hrms.request.bean;

import lombok.Data;

@Data
public class EmployeeLeaveTypeBean {

	private int id;
	private String leaveType;
	private int noOfDays;
	private int year;
	private int userId;
	private String leaveCode;
	private int leaveTypeId;
	private int createdBy;
	private int modifiedBy;
	private int noOfDaysMonth;
}
