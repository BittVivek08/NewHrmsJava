package com.hrms.service;

import java.util.List;

import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.EntityBeanResponseCommon;
import com.hrms.beans.ProjechtRequiredFetchDetails;
//import com.hrms.beans.ProjectResponseBean;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.ProjectDetailsEntity;
import com.hrms.request.bean.ManagerRoleReuestBean;
import com.hrms.response.bean.ManagerListResonseBean;
import com.hrms.response.bean.ProjecDetailsResponsebean;

public interface ProjectDetailsService {
	
	 //oldHrms
	public EntityBeanResponseCommon saveProjectDetails(ProjectDetailsEntity porjentity);
	
	 //oldHrms
	public EntityBeanResponseCommon updateProjectDetails(int pid,ProjectDetailsEntity entity);
	
	 //oldHrms
	public ProjecDetailsResponsebean getAllProjectsByClientId(int id);
	
	public  ProjecDetailsResponsebean getAllProjects();
	
	public ManagerListResonseBean getAllManager(ManagerRoleReuestBean reqBean);
	
	 // public ProjectRespoonseBean getListOfProjectDetailsByid(int client);
	
	 //oldHrms
	//public List<ProjectDetailsEntity> getProjectListByClienyId(int cid);
	
  // public EntityBeanResponse postProjectDetails(ProjectDetailsEntity entity);
}
