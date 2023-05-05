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
import com.hrms.service.TaskDetailsService;

@RestController
@RequestMapping("/task")
public class TaskDetailsController {


	@Autowired
	private TaskDetailsService service;

	@PostMapping("/savetask")
	public EntityBeanResponseCommon saveTask(@RequestBody TaskDetailsEntity entity) {
		
		EntityBeanResponseCommon saveTaskDeatils = this.service.saveTaskDeatils(entity);
		
		return saveTaskDeatils;
		
		
	}
	
	@GetMapping("/gettasks/{id}")
	public List<TasksDetailsResponseBean> getAllTasks(@PathVariable("id") int id){
		
		List<TasksDetailsResponseBean> taskByProjectId = this.service.getTaskByProjectId(id);
		return taskByProjectId;	
	}
	
	@DeleteMapping("/deletetaskById/{id}")
	public EntityBeanResponseCommon deleteTaskById(@PathVariable("id") int id) {
		
		EntityBeanResponseCommon deleteTaskById = this.service.deleteTaskById(id);
		return deleteTaskById;
		
	}
	
	@PutMapping("/updateTask/{id}")
	public EntityBeanResponseCommon updateTaskById(@PathVariable("id") int id,@RequestBody TaskDetailsEntity entity) {
		
		
		EntityBeanResponseCommon updateTask = this.service.updateTaskByTaskId(id, entity);
		
		return updateTask;
		
	}
	
	
	
	
	//TaskEmployee
	
	
	@PostMapping("/saveListTaskToEmp")
	public EntityBeanResponseCommon saveTaskToEmp(@RequestBody TaskDetailsEntity task) {
		
		EntityBeanResponseCommon addListOfTaskToEmp = this.service.addListOfTaskToEmp(task);
		return addListOfTaskToEmp;
		
	}
	
	
	@GetMapping("getListTaskSOfEmp/{sid}")
	public List<TaskDetailsEntity> listOfTasksOfEmp(@PathVariable("sid") String id){
		List<TaskDetailsEntity> listTasksByEmpid = this.service.getListTasksByEmpid(id);
		return listTasksByEmpid;
		
	}
	
	
	@GetMapping("getTaskOfEmpByEmpId/{empid}")
	public List<EmployeeTaskDetailsBean> getListOfTaskOfEmpByEmpid(@PathVariable("empid") String eid){
		
		List<EmployeeTaskDetailsBean> empListOfTasksByEmpId = this.service.getEmpListOfTasksByEmpId(eid);
		
		
		return empListOfTasksByEmpId;
		
	}
	
	
	
	
	
	
	//getTasksByProjectId

	/*
	 * @GetMapping("/gettasks/{id}") public List<TaskDetailsEntity>
	 * getAllTasks(@PathVariable("id") int id){
	 * 
	 * List<TaskDetailsEntity> taskByProjectId =
	 * this.service.getTaskByProjectId(id); return taskByProjectId; }
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//@PostMapping("/saveTask")
	//public EntityBeanResponse saveTaskdetails(@RequestBody TaskDetailsEntity entity) {

		//EntityBeanResponse saveTaskDetails = this.service.saveTaskDetails(entity);

		//return saveTaskDetails;

	//}

	//@PutMapping("/updateTask/{id}")
	//public EntityBeanResponse updateTaskDetails(@PathVariable("id") int id,@RequestBody TaskDetailsEntity entity) {

		//EntityBeanResponse updateTaskDetails = this.service.updateTask(id, entity);

		//return updateTaskDetails;

	//}

	//@DeleteMapping("/delete/{id}")
	//public EntityBeanResponse deleteTaskByid(@PathVariable("id") int id) {
	//	EntityBeanResponse deleteTakById = this.service.deleteTakById(id);
		//return deleteTakById;

	//}


	//@GetMapping("/getalltasks")
	//public TasksDetailsResponseBean GetAllTasks() {

		//TasksDetailsResponseBean allTasks = this.service.getAllTasks();

		//return allTasks;

//	}
}
