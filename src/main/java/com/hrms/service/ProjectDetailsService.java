package com.hrms.service;

import com.hrms.beans.EntityBeanResponseCommon;
import com.hrms.entity.ProjectDetailsEntity;
import com.hrms.request.bean.ManagerRoleReuestBean;
import com.hrms.response.bean.ManagerListResonseBean;
import com.hrms.response.bean.ProjecDetailsResponsebean;

public interface ProjectDetailsService {
	public EntityBeanResponseCommon saveProjectDetails(ProjectDetailsEntity porjentity);
	public EntityBeanResponseCommon updateProjectDetails(int pid,ProjectDetailsEntity entity);
	public ProjecDetailsResponsebean getAllProjectsByClientId(int id);
	public  ProjecDetailsResponsebean getAllProjects();
	public ManagerListResonseBean getAllManager(ManagerRoleReuestBean reqBean);
	public ManagerListResonseBean getAllManager(String mangerRole);
}
