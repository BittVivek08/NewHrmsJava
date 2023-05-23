package com.hrms.response.bean;

import java.util.List;

import com.hrms.entity.EmployeeDetails;

import lombok.Data;

@Data
public class EmployeesForReportingManagerResponse  
{
	boolean status;
	String message;	
	int reportingMangerId;
	List<EmployeeDetails> empDetailsList;
}