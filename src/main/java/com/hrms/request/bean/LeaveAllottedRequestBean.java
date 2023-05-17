package com.hrms.request.bean;

import java.sql.Date;

import lombok.Data;

@Data
public class LeaveAllottedRequestBean {

	
	private int id;
	private int userId;
	private int assignedleaves;
	private int totalLeaves;
	private int year;
	private int createdBy;
	private int modifiedBy;
	private Date createdDate;
	private Date modifiedDate;
}
