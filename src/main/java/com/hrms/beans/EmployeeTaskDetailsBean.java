package com.hrms.beans;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class EmployeeTaskDetailsBean {
	
	private int Taskid;
	
	private String taskname;
	
	private int is_active;
	
	private  Date createdDate;
	
	private Date modifiedDate;
	
	private int projectId;
	
	private String ProjectName;
	

}
