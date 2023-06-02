package com.hrms.response.bean;

import lombok.Data;

@Data
public class TimeSheetApprovalStatusResponse 
{   
	private String empId;
	private String status;	
	private String reason;
	private Integer approverRoleId;
	private String approverRole;
	private Integer calWeek;
	private Integer year;
	private Integer month;
	private Double totalHrs;
}