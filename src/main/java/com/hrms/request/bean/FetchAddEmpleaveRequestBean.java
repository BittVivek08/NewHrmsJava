package com.hrms.request.bean;

import lombok.Data;

@Data
public class FetchAddEmpleaveRequestBean {

	
	private int id;
	private String firstName;
	private String lastname;
	private String employeeId;
	private  int assignedLeaves;
	private int usedLeaves;
	private int leaveBalance;
	private int allottedYear;
}
