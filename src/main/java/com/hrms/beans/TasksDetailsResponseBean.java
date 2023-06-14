package com.hrms.beans;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class TasksDetailsResponseBean {
	
	
	private int  taskid;
	
	private String taskname;;
	
	private int is_active;
	
	private LocalDateTime createddate;
	
	private LocalDateTime modifiedDate;

	private String createdBy;
	
	private String modifiedBy;
	
	

}
