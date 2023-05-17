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

@RestController
@RequestMapping("/projectdetails")
public class ProjectDetailsController {

	@Autowired
	private ProjectDetailsService service;
	
	 //oldHrms
	//saveProject
	@PostMapping("/saveProjectDetails")
	public EntityBeanResponseCommon saveProjectDetails(@RequestBody ProjectDetailsEntity entity) {
		
		EntityBeanResponseCommon saveProjectDetails = this.service.saveProjectDetails(entity);
		
		return saveProjectDetails;
		
	}
	
	 //oldHrms
	//getProjectDeatilsByClientId
	@GetMapping("/getProjectsByClientId/{id}")
	public List<ProjectResponseBean> getProjectsBYClientId(@PathVariable("id")  int id){
		
		List<ProjectResponseBean> projectListByClienyId = this.service.getAllProjects(id);
	
		return projectListByClienyId;
		
	}
	
	 
	 //oldHrms
	//updateProjectDetailsByProjectId
	@PutMapping("/updateProjectById/{id}")
	public EntityBeanResponseCommon updateProject(@PathVariable("id") int id,@RequestBody ProjectDetailsEntity entity) {
		
		EntityBeanResponseCommon updateProjectDetails = this.service.updateProjectDetails(id, entity);
		

		return updateProjectDetails;
		
	}
	
	
	
	
	

	/*
	 * //postIndiffWay
	 * 
	 * @PostMapping("/postProject") public EntityBeanResponse
	 * postProjectDetails(@RequestBody ProjectDetailsEntity entity) {
	 * 
	 * EntityBeanResponse postProjectDetails =
	 * this.service.postProjectDetails(entity);
	 * 
	 * 
	 * return postProjectDetails;
	 * 
	 * }
	 */
	
	
	
	
	
	/*
	 * //getProject
	 * 
	 * @GetMapping("/getprojects/{id}") public ProjectRespoonseBean
	 * getProjectDetails(@PathVariable("id") int id) {
	 * 
	 * 
	 * ProjectRespoonseBean listOfProjectDetailsByid =
	 * this.service.getListOfProjectDetailsByid(id);
	 * 
	 * return listOfProjectDetailsByid;
	 * 
	 * }
	 */
	
	
	
	
	
}
