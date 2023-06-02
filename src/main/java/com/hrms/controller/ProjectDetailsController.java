package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.EntityBeanResponseCommon;
import com.hrms.beans.ProjectResponseBean;
import com.hrms.entity.ProjectDetailsEntity;
import com.hrms.service.ProjectDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/projectdetails")
public class ProjectDetailsController {

	@Autowired
	private ProjectDetailsService service;
	
	 //oldHrms
	//saveProject
	@PostMapping("/saveProjectDetails")
	public EntityBeanResponseCommon saveProjectDetails(@RequestBody ProjectDetailsEntity entity) {
		this.log.info("Entered save project details in controller ");
		
		EntityBeanResponseCommon saveProjectDetails = this.service.saveProjectDetails(entity);
		
		this.log.info("successfully  saved project details in controller ");
		
		return saveProjectDetails;
		
	}
	
	 //oldHrms
	//getProjectDeatilsByClientId
	@GetMapping("/getProjectsByClientId/{id}")
	public List<ProjectResponseBean> getProjectsBYClientId(@PathVariable("id")  int id){
		this.log.info("Entered fetch list of  project details by client id  in controller ");
		
		List<ProjectResponseBean> projectListByClienyId = this.service.getAllProjects(id);
		
		this.log.info("successfully  fetched list of  project details by client id  in controller ");
		
	
		return projectListByClienyId;
		
	}
	
	 
	 //oldHrms
	//updateProjectDetailsByProjectId
	@PutMapping("/updateProjectById/{id}")
	public EntityBeanResponseCommon updateProject(@PathVariable("id") int id,@RequestBody ProjectDetailsEntity entity) {
		this.log.info("Entered update project details  in controller ");
		
		EntityBeanResponseCommon updateProjectDetails = this.service.updateProjectDetails(id, entity);
		
		this.log.info("successfully  update project details  in controller ");
		return updateProjectDetails;
		
	}
	
}
