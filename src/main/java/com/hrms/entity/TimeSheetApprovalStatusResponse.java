package com.hrms.entity;

import lombok.Data;

@Data
public class TimeSheetApprovalStatusResponse 
{   
	private int userId;
	private String status;	
	private String reason;
	private int approverRoleId;
	private String approverRole;
	private short calWeek;
	private int year;
	private short month;
	private Double totalHrs;
}