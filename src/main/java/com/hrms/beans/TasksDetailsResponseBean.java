package com.hrms.beans;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.hrms.entity.ProjectDetailsEntity;

import lombok.Data;

@Data
@Component
public class TasksDetailsResponseBean {
	
	
	private int  taskid;
	
	private String taskname;;
	
	private int is_active;
	
	private Date createddate;
	
	private Date modifiedDate;
	
	private int projectId;
	
	

}
