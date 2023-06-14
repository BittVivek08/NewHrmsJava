package com.hrms.request.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class LeaveRequestUpdateDataBean {
	
	private String fromDate;
	
	private String toDate;
	
	private String leaveType;
	
	private String reson;

}
