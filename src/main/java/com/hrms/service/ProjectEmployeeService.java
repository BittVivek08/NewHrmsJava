package com.hrms.service;

import java.util.List;

import com.hrms.beans.ProjectEmployeeDataBean;
import com.hrms.beans.ProjectEmployeeFetchResponse;
import com.hrms.beans.ProjectEmployeeResponseBean;
//import com.hrms.beans.ProjectResponseBean;
import com.hrms.entity.ProjectEmployeeEntity;
import com.hrms.response.bean.ProjectEmployeeFetchBeans;

public interface ProjectEmployeeService {
	
	public ProjectEmployeeResponseBean saveProjectEmployee(ProjectEmployeeDataBean Databean);
	
	public ProjectEmployeeFetchResponse getAllDetails();

	public ProjectEmployeeResponseBean updateProjEmpDetails(int id,ProjectEmployeeEntity entity);
	
	public ProjectEmployeeFetchBeans getAllDetailsByOrgId(String oid);
	
}
