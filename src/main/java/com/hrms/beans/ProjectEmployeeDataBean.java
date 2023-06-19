package com.hrms.beans;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProjectEmployeeDataBean {
     
	private int projectid;
	
	private String empid;
	
	private String startdate;
	
	private String enddate;
	
	private int billable;
	
	
		
}
