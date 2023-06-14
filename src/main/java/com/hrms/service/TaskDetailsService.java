package com.hrms.service;



import java.util.List;

import com.hrms.beans.EmployeeTaskDetailsBean;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.EntityBeanResponseCommon;
import com.hrms.beans.TasksDetailsResponseBean;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.TaskDetailsEntity;
import com.hrms.request.bean.TaskListResponseBean;

public interface TaskDetailsService {
	//OldHrms
	public EntityBeanResponseCommon saveTaskDeatils(TaskDetailsEntity entity);
	
	//OldHrms
	public TaskListResponseBean getTaskByProjectId();
	
	
	//OldHrms
	public EntityBeanResponseCommon deleteTaskById(int id);

	//OldHrms
	public EntityBeanResponseCommon updateTaskByTaskId(int id, TaskDetailsEntity entity);
	
}
