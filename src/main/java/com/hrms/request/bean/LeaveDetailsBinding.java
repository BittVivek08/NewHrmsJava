package com.hrms.request.bean;

import lombok.Data;

@Data
public class LeaveDetailsBinding {

	private String empId;
	private String leaveType;
	private String totalLeave;
}
