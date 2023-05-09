package com.hrms.response.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class EmployeeSalaryResponseBean {
	
	private String message;
	
	private boolean status;

}
