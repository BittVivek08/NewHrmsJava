package com.hrms.request.bean;

import java.sql.Date;

import lombok.Data;

@Data
public class EmployeeLeaveRequestSummary {

	
	private int id;
	private int leaveRequestId;
	private int userId;
	private String userName;
	private int departmentId;
	private String departmentName;
	private int businessUnitId;
	private String businessUnitName;
	private String reason;
	private String approverComments;
	private int leaveTypeId;
	private String leaveTypeName;
	private int leaveDay;
	private Date fromDate;
	private Date toDate;
//	public enum leaveStatus{pendingForApproval,Approved,Rejected,Cancel;}
	private int reportingManagerId;
	private String reportingManagerName;
	private int hrId;
	private String hrName;
	private float noOfDays;
	private float appliedLeavesCount;
	private int isSatHoliday;
}
