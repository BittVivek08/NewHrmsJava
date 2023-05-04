package com.hrms.beans;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProjectResponseBean {
	
	private int projectId;
	
	private String ProjectName;
	
	private String status;
	
	private String description;
	
	private int clientid;
	
	private String currencyName;
	
	private String projectType;
	
    private Boolean leadAppove;
    
    private int estimatedhours;
    
    private Date StartDate;
    
    private Date EndDate;
    
    private Boolean is_active;
    
	
	
	
	

}
