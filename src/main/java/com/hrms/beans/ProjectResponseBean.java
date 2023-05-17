package com.hrms.beans;

import java.sql.Date;
import java.time.LocalDate;

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
	
	private int  currencyid;
	
	private String projectType;
	
    private Boolean leadAppove;
    
    private int estimatedhours;
    
    private LocalDate StartDate;
    
    private LocalDate EndDate;
    
    private Boolean is_active;
    
	
	
	
	

}
