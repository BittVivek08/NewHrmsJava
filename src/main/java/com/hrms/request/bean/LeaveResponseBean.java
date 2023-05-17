package com.hrms.request.bean;

import lombok.Data;

@Data
public class LeaveResponseBean {

	
	public String message;
	public boolean status;
	public Object addEmployeeLeaveLimit;
	public Object privilegesList;
}
