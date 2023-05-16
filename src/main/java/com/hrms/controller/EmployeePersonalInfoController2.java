package com.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.EmployeeEducationBean;
import com.hrms.beans.ExperianceDetails;
import com.hrms.entity.EmpEducationDetailsEntity;
import com.hrms.entity.ExperinceEntity;
import com.hrms.service.EmployeePersonalInfoService2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/personal")
public class EmployeePersonalInfoController2 {
	
	@Autowired
	private EmployeePersonalInfoService2 employeepersonal;
	
	
	@PostMapping("/saveExperiance/{empId}")
	public ExperianceDetails saveEmpExperiance(@PathVariable String empId, @RequestBody ExperinceEntity experianceentity) {
		
		ExperianceDetails expe=employeepersonal.saveEmployeeExperianceData(empId,experianceentity);
		
		return expe;
		
	}
	@GetMapping("/getEmpdetails/{id}")
	public ExperinceEntity getEmpExperiance(@PathVariable int id) {
		ExperinceEntity  experiance =employeepersonal.getExperiancedetails(id);
		return experiance;
		
	}
	
	@PutMapping("/updateExperiance")
	public ExperianceDetails updateEmpExpiriance(@RequestBody ExperinceEntity experiance) {
		
		ExperianceDetails exp =employeepersonal.updateExperiancedetails(experiance);
		return exp;
		
		
	}
	
	// employee education details
	
	@PostMapping("/employeeEducation/{empId}")
	public EmployeeEducationBean saveEmpEducationData(@PathVariable String empId, @RequestBody EmpEducationDetailsEntity data) {
		
		EmployeeEducationBean bean=employeepersonal.saveEmployeeEducation(empId, data);
		return bean;
		
		
		
	}
	
	
	@GetMapping("/getEmpEducationData/{id}")
	public EmpEducationDetailsEntity getEmpEducation(@PathVariable int id) {
		EmpEducationDetailsEntity  data =employeepersonal.getEducationdetails(id);
		return data;
		
	}
	
	@PutMapping("/updateaempaeducation")
	public EmployeeEducationBean updateEmpEdu(@RequestBody EmpEducationDetailsEntity emp) {
		
		EmployeeEducationBean  educationbean  =employeepersonal.updateEmpEducationdetails(emp);
		
		return educationbean;
		
	}
	

}
