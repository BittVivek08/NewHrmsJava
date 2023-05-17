package com.hrms.service;



import java.util.List;

import com.hrms.beans.EmployeeTaskDetailsBean;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.EntityBeanResponseCommon;
import com.hrms.beans.TasksDetailsResponseBean;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.TaskDetailsEntity;

public interface TaskDetailsService {
	//OldHrms
	public EntityBeanResponseCommon saveTaskDeatils(TaskDetailsEntity entity);
	
	//OldHrms
	public List<TasksDetailsResponseBean> getTaskByProjectId(int id);
	
	//duplicate
	//public List<TaskDetailsEntity> getTaskByProjectId(int id);
	
	//OldHrms
	public EntityBeanResponseCommon deleteTaskById(int id);

	//OldHrms
	public EntityBeanResponseCommon updateTaskByTaskId(int id, TaskDetailsEntity entity);
	
	
	//EmployeeAndTask
	
	//public TaskDetailsEntity saveTaskStoEmp(TaskDetailsEntity entity);
	
	
	
	//EmployeAndTaskRelationOperations
//OldHrms	
 // public EntityBeanResponseCommon addListOfTaskToEmp(TaskDetailsEntity emp);
	
	
//newHrms
	
	public EntityBeanResponseCommon addListOfTaskToEmployee(String eid,int taskid);
	
	//OldHrms	
  //public List<TaskDetailsEntity> getListTasksByEmpid(String id);
	
	//OldHrms
  //public List<EmployeeTaskDetailsBean> getEmpListOfTasksByEmpId(String eid);
	
	
	
	
	
	
	
	/*
	 * public EntityBeanResponse saveTaskDetails(TaskDetailsEntity entity);
	 * 
	 * public EntityBeanResponse updateTask(int id,TaskDetailsEntity entity);
	 * 
	 * public EntityBeanResponse deleteTakById(int id);
	 * 
	 * public TasksDetailsResponseBean getAllTasks();
	 */
	
	
	
	
	
	
	
	
	

}
