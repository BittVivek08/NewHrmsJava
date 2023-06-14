package com.hrms.controller;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.ProjectEmployeeDataBean;
import com.hrms.beans.ProjectEmployeeFetchResponse;
import com.hrms.beans.ProjectEmployeeResponseBean;
import com.hrms.entity.ProjectEmployeeEntity;
import com.hrms.service.ProjectDetailsService;
import com.hrms.service.ProjectEmployeeService;

@RestController
@RequestMapping("/ProjectEmployee")
public class ProjectEmployeeController {

	@Autowired
	private ProjectEmployeeService service;
	
	//save
	@PostMapping("/SaveProjectEmployee")
	public ProjectEmployeeResponseBean saveProjectEmpDetails(@RequestBody ProjectEmployeeDataBean bean) {
		
		ProjectEmployeeResponseBean saveProjectEmployee = this.service.saveProjectEmployee(bean);
		return saveProjectEmployee;
		
	}
	
	@GetMapping("/fetchAllDetails")
	public ProjectEmployeeFetchResponse fetchalldata() {
		
		ProjectEmployeeFetchResponse allDetails = this.service.getAllDetails();
		return allDetails;
		
	}
	
	@PutMapping("/update")
	public ProjectEmployeeResponseBean updatedetails(@RequestBody ProjectEmployeeEntity entity,@QueryParam (value="id") int id) {
		ProjectEmployeeResponseBean updateProjEmpDetails = this.service.updateProjEmpDetails(id, entity);
		return updateProjEmpDetails;
		
	}
	
	
}
