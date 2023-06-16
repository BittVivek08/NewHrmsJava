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
import com.hrms.entity.EmployeeEducationDetails;
import com.hrms.entity.ExperinceEntity;
import com.hrms.service.EmployeePersonalInfoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/personal")
public class EmployeePersonalInfoController {
	
	@Autowired
	private EmployeePersonalInfoService employeepersonal;
	
	
	@PostMapping("/saveExperiance/{empId}")
	public ExperianceDetails saveEmpExperiance(@PathVariable String empId, @RequestBody ExperinceEntity experianceentity) {
		log.info("entered into saveEmpExperiance method in controller");
		ExperianceDetails expe=employeepersonal.saveEmployeeExperianceData(empId,experianceentity);
		
		return expe;
		
	}
	@GetMapping("/getEmpdetails/{id}")
	public ExperinceEntity getEmpExperiance(@PathVariable int id) {
		log.info("entered into getEmpExperaince method in controller class");
		ExperinceEntity  experiance =employeepersonal.getExperiancedetails(id);
		return experiance;
		
	}
	
	@PutMapping("/updateExperiance/{empId}")
	public ExperianceDetails updateEmpExpiriance(@RequestBody ExperinceEntity experiance,@PathVariable("empId") String empId) {
		log.info("entered into updateEmpExperiance method in controller class");
		ExperianceDetails exp =employeepersonal.updateExperiancedetails(experiance,empId);
		return exp;
		
		
	}
	
	// employee education details
	
	@PostMapping("/employeeEducation/{empId}")
	public EmployeeEducationBean saveEmpEducationData(@PathVariable String empId, @RequestBody EmployeeEducationDetails data) {
		log.info("entered into saveEmpEductionData in controller class");
		EmployeeEducationBean bean=employeepersonal.saveEmployeeEducation(empId, data);
		return bean;
		
		
		
	}
	
	
	@GetMapping("/getEmpEducationData/{id}")
	public EmployeeEducationDetails getEmpEducation(@PathVariable int id) {
		log.info("entered into getEmpEducation method in controller class");
		EmployeeEducationDetails  data =employeepersonal.getEducationdetails(id);
		return data;
		
	}
	
	@PutMapping("/updateaempaeducation")
	public EmployeeEducationBean updateEmpEdu(@RequestBody EmployeeEducationDetails emp) {
		log.info("entered into updateEmpEdu method in controller class");
		EmployeeEducationBean  educationbean  =employeepersonal.updateEmpEducationdetails(emp);
		
		return educationbean;
		
	}
	

}
