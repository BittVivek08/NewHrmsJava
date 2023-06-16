package com.hrms.controller;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.EntityBeanResponseCommon;
//import com.hrms.beans.ProjectResponseBean;
import com.hrms.entity.ProjectDetailsEntity;
import com.hrms.request.bean.ManagerRoleReuestBean;
import com.hrms.response.bean.ManagerListResonseBean;
import com.hrms.response.bean.ProjecDetailsResponsebean;
import com.hrms.service.ProjectDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/projectdetails")
public class ProjectDetailsController {

	@Autowired
	private ProjectDetailsService service;

	@PostMapping("/saveProjectDetails")
	public EntityBeanResponseCommon saveProjectDetails(@RequestBody ProjectDetailsEntity entity) {
		log.info("Entered save project details in controller ");
		EntityBeanResponseCommon saveProjectDetails = service.saveProjectDetails(entity);
		log.info("successfully  saved project details in controller ");
		return saveProjectDetails;	
	}
	@GetMapping("/getProjectsByClientId/{id}")
	public ProjecDetailsResponsebean getProjectsBYClientId(@PathVariable("id")  int id){
		log.info("Entered fetch list of  project details by client id  in controller ");
		ProjecDetailsResponsebean projectListByClienyId = service.getAllProjectsByClientId(id);
		log.info("successfully  fetched list of  project details by client id  in controller ");
		return projectListByClienyId;
	}
	@GetMapping("/projects")
	public ProjecDetailsResponsebean fetchAllProjects(){
		ProjecDetailsResponsebean allProjects = service.getAllProjects();
		return allProjects;
	}
	@PutMapping("/updateProjectById/{id}")
	public EntityBeanResponseCommon updateProject(@PathVariable("id") int id,@RequestBody ProjectDetailsEntity entity) {
		log.info("Entered update project details  in controller ");
		EntityBeanResponseCommon updateProjectDetails = service.updateProjectDetails(id, entity);
		log.info("successfully  update project details  in controller ");
		return updateProjectDetails;	
	}
	@GetMapping("/getAllManagersList")
	public ManagerListResonseBean getAllMangers(@RequestBody ManagerRoleReuestBean reqBean){
		ManagerListResonseBean listOfManagers= service.getAllManager(reqBean);
		return listOfManagers;	
	}
	@GetMapping("/getAllManagers")
	public ManagerListResonseBean getAllMangersByRole(@QueryParam(value = "empRole") String managerRole){
		ManagerListResonseBean listOfManagers= service.getAllManager(managerRole);
		return listOfManagers;	
	}	
}
