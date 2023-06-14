package com.hrms.beans;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hrms.entity.ProjectEmployeeEntity;

import lombok.Data;

@Data
@Component
public class ProjectEmployeeFetchResponse {
	
	private String message;
	
	private Boolean status;
	
	private List<ProjectEmployeeFetchBean> listProjectEmpDeatils;

}
