package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.Tbl_StatesEntityBean;
import com.hrms.entity.Tbl_CountriesEntity;
import com.hrms.entity.Tbl_StatesEntity;
import com.hrms.service.Tbl_StatesEntityService;



@RequestMapping("/states")
@RestController
public class Tbl_StatesEntityController {
	@Autowired
	private Tbl_StatesEntityService statesentityservice;
	
	
	
	@PostMapping("/savestates")
	public Tbl_StatesEntityBean savestatesdetails(@RequestBody Tbl_StatesEntity statesentity)
	{
		
		
		return statesentityservice.savestatesdetails(statesentity);
		
	}
	
	@GetMapping("/getstates/{id}")
	public Tbl_StatesEntity getById(@PathVariable("id") int id)
	{
		return statesentityservice.getById(id);
		
	}
	@GetMapping("getallstatesdetails")
	public ResponseEntity<List<Tbl_StatesEntity>> getallstatesdetails()
	 {
		List<Tbl_StatesEntity> details= statesentityservice.getallstatesdetails();
		return new ResponseEntity<>(details, HttpStatus.OK);

		
	}
	
	
	
		
	}
	
	
	

