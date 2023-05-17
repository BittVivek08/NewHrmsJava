package com.hrms.service;

import java.util.List;

import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.EntityBeanResponseCommon;
import com.hrms.beans.ProjectResponseBean;
import com.hrms.entity.ProjectDetailsEntity;

public interface ProjectDetailsService {
	
	 //oldHrms
	public EntityBeanResponseCommon saveProjectDetails(ProjectDetailsEntity porjentity);
	
    // public ProjectRespoonseBean getListOfProjectDetailsByid(int client);
	
	 //oldHrms
	//public List<ProjectDetailsEntity> getProjectListByClienyId(int cid);
	
	 //oldHrms
	public EntityBeanResponseCommon updateProjectDetails(int pid,ProjectDetailsEntity entity);
	
	 //oldHrms
	public List<ProjectResponseBean> getAllProjects(int id);
	
	
	
	
  // public EntityBeanResponse postProjectDetails(ProjectDetailsEntity entity);
}
