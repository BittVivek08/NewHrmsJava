 package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.SkillEnitityBean;
import com.hrms.entity.SkillEnitity;
import com.hrms.service.SkillEnitityService;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/skills")
@CrossOrigin
public class SkillEnitityController {
	@Autowired
	private SkillEnitityService skillenitityservice;
	
	
	@PostMapping("/addSkills/{empId}")
	public SkillEnitityBean saveskills(@PathVariable String empId,@RequestBody SkillEnitity entity)
	{
		log.info("entered into saveskills method in controller");
		return skillenitityservice.saveskills(empId,entity) ;
		
	}
	@GetMapping("/getskilldetails")
	public ResponseEntity<List<SkillEnitity>> getskillDetails() {
		log.info("entered into getskills method in controller");
		List<SkillEnitity> details = skillenitityservice.getskillDetails();
		log.info("Successfully getskills method in controller");
		return new ResponseEntity<>(details, HttpStatus.OK);

	}
	
	@PutMapping("/Updateskilldetails/{id}")
	public SkillEnitityBean updateskilldetails(@PathVariable int id, @RequestBody SkillEnitity entity) {
		log.info("entered into updateskill details method in controller");

		SkillEnitityBean updatedetails = skillenitityservice.updateskilldetails(id, entity);
		log.info("Successfully updated skill details in controller");
		return updatedetails;

		
	}
	
	}
