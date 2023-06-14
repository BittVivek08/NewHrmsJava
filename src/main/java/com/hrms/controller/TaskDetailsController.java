package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.EmployeeTaskDetailsBean;
import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.EntityBeanResponseCommon;
import com.hrms.beans.TasksDetailsResponseBean;
import com.hrms.entity.TaskDetailsEntity;
import com.hrms.request.bean.TaskListResponseBean;
import com.hrms.service.TaskDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/task")
public class TaskDetailsController {


	@Autowired
	private TaskDetailsService service;

	//OldHrms
	@PostMapping("/savetask")
	public EntityBeanResponseCommon saveTask(@RequestBody TaskDetailsEntity entity) {
		this.log.info("Entered save task details in controller ");
		
		EntityBeanResponseCommon saveTaskDeatils = this.service.saveTaskDeatils(entity);
		
		this.log.info("successfully  saved task details in controller ");
		
		return saveTaskDeatils;
	}
	
	
	//OldHrms
	@GetMapping("/getListOftasks")
	public TaskListResponseBean getAllTasks(){
		this.log.info("Entered fetch listn of  task details in controller ");
		
		TaskListResponseBean listOfTasks = this.service.getTaskByProjectId();
		this.log.info("successfully  fetched listn of  task details in controller ");
		return listOfTasks;	
	}
	
	
	//OldHrms
	@DeleteMapping("/deletetaskById/{id}")
	public EntityBeanResponseCommon deleteTaskById(@PathVariable("id") int id) {
		this.log.info("Entered delete task details in controller ");
		
		EntityBeanResponseCommon deleteTaskById = this.service.deleteTaskById(id);
		
		this.log.info("successfully  delete task details in controller ");
		return deleteTaskById;	
	}
	
	//OldHrms
	@PutMapping("/updateTask/{id}")
	public EntityBeanResponseCommon updateTaskById(@PathVariable("id") int id,@RequestBody TaskDetailsEntity entity) {
		this.log.info("Entered update task details in controller ");
		
		
		EntityBeanResponseCommon updateTask = this.service.updateTaskByTaskId(id, entity);
		this.log.info("successsfully update task details in controller ");
		return updateTask;
		
	}
	
}
