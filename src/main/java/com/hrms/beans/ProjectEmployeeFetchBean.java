package com.hrms.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProjectEmployeeFetchBean {

	private int id;
	
	private String employeeId;
	
	private int projectId;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private String createdby;
	
	private String modifiedby;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime modifiedDate;
	
	private int billable;
}
