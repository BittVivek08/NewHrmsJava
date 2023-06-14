package com.hrms.response.bean;

import java.math.BigInteger;

import java.util.Date;
import java.util.List;

import com.hrms.entity.EmployeeLeaveRequestSummaryEntity;


import lombok.Data;

@Data
public class EmployeeLeaveResponse {
	private String employeeName;
	private int userId;
	private String employeeId;
	private String leaveFromDate;
	private String leaveToDate;
	private String reason;
	private String reportingManager;
	private List<EmployeeLeaveRequestSummaryEntity> list;
	
	

}
