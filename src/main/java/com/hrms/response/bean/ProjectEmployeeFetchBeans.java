package com.hrms.response.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProjectEmployeeFetchBeans {
private String message;
	
	private boolean status;
	
	private List<ProjectEmployeeOrgFetchBean> listOfProjectEmpployees;
	
	
}
