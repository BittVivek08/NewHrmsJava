package com.hrms.request.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class EmployeeLeaveTypeResponseBean {

	
	private String messsage;
	private boolean status;
	private Object leaveTypelist;
}






