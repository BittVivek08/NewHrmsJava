package com.hrms.request.bean;

import java.sql.Date;

import lombok.Data;

@Data
public class LeaveRequestBean {

	
	private int id;
	private int userId;
	private int leaveTypeId;
	private String leaveType;
	private String leaveStatus;
	private Integer reportingManagerId;
	private String reportingManager;
	private Integer hrId;
	private int appliedLeavesCount;
	private float availableLeaves;
	private String reason;
	private Date fromDate;
	private Date toDate;
	private String leaveFor;
	private int days;
	private String avilableCasualLeaves;
	private String avilableSickLeaves;
	private String email;
	private String name;
}
