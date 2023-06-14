package com.hrms.response.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hrms.entity.EmployeeDetails;

import lombok.Data;

@Data
@Component
public class ManagerListResonseBean {
	
	private String message;
	
	private Boolean status;
	
	private List<EmployeeDetails> listOfdetails;
	

}
