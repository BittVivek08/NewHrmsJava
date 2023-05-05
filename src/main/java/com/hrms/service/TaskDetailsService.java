package com.hrms.service;



import java.util.List;

import com.hrms.beans.EmployeeTaskDetailsBean;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.EntityBeanResponseCommon;
import com.hrms.beans.TasksDetailsResponseBean;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.TaskDetailsEntity;

public interface TaskDetailsService {
	
	public EntityBeanResponseCommon saveTaskDeatils(TaskDetailsEntity entity);
	
	public List<TasksDetailsResponseBean> getTaskByProjectId(int id);
	
	//duplicate
	//public List<TaskDetailsEntity> getTaskByProjectId(int id);
	
	public EntityBeanResponseCommon deleteTaskById(int id);
	
	public EntityBeanResponseCommon updateTaskByTaskId(int id, TaskDetailsEntity entity);
	
	
	//EmployeeAndTask
	
	//public TaskDetailsEntity saveTaskStoEmp(TaskDetailsEntity entity);
	
	
	
	//EmployeAndTaskRelationOperations
	
  public EntityBeanResponseCommon addListOfTaskToEmp(TaskDetailsEntity emp);
	
  public List<TaskDetailsEntity> getListTasksByEmpid(String id);
	
  public List<EmployeeTaskDetailsBean> getEmpListOfTasksByEmpId(String eid);
	
	
	
	
	
	
	
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
