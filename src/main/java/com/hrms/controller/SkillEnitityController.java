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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.SkillEnitityBean;
import com.hrms.entity.SkillEnitity;
import com.hrms.service.SkillEnitityService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/skills")
@CrossOrigin
public class SkillEnitityController {
	@Autowired
	private SkillEnitityService skillenitityservice;
	
	
	@PostMapping("/addSkills/{empId}")
	public SkillEnitityBean saveskills(@PathVariable("empId") String Id,@RequestBody SkillEnitity entity)
	{
		return skillenitityservice.saveskills(Id,entity) ;
		
		
	}}
//	@GetMapping("/getskills/{id}")
//	public ResponseEntity<SkillEnitity> findskillsById(@PathVariable Integer id){
//		SkillEnitity skillentity = skillenitityservice.getskillsById(id);
//		return new ResponseEntity<>(skillentity,HttpStatus.OK);
//	}
//	
//	@GetMapping("/getskills")
//	public ResponseEntity<List<SkillEnitity>> getAllskilldetails() {
//		List<SkillEnitity> allskilldetails = skillenitityservice.getAllskilldetals();
//		return new ResponseEntity<>(allskilldetails, HttpStatus.OK);
//	}
//	
//	@PutMapping("/Updateskilldetalis")
//	public SkillEnitityBean updateskilldetails(@RequestBody SkillEnitity skillentity) {
//		
//		return skillenitityservice.updateskilldetails( skillentity);
//	}
//
//	
//	
//
//}
