package com.hrms.controller;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.LocationResponseBean;
import com.hrms.beans.Tbl_StatesEntityBean;
import com.hrms.entity.Tbl_StatesEntity;
import com.hrms.service.Tbl_StatesEntityService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/states")
@RestController
public class Tbl_StatesEntityController {
	
	@Autowired
	private Tbl_StatesEntityService statesentityservice;
	
	
	
	@PostMapping("/savestates")
	public Tbl_StatesEntityBean savestatesdetails(@RequestBody Tbl_StatesEntity statesentity)
	{
		log.info("entered into savestatesdetails  method in controller");
		
		
		return statesentityservice.savestatesdetails(statesentity);
		
	}
	
	@GetMapping("/getstates/{id}")
	public Tbl_StatesEntity getById(@PathVariable("id") int id)
	{
		log.info("entered into getstates based on id method in controller");
		return statesentityservice.getById(id);
		
	}
	@GetMapping("/getallstatesdetails")
	public ResponseEntity<List<Tbl_StatesEntity>> getallstatesdetails()
	 {
		log.info("entered into getstatesdetails method in controller");
		List<Tbl_StatesEntity> details= statesentityservice.getallstatesdetails();
		
		log.info("Successfully fetched the statesdetails in controller");
		return new ResponseEntity<>(details, HttpStatus.OK);

		
	}
	
	@PutMapping("/Updatestatesdetails/{id}")
	public Tbl_StatesEntity updatestatesdetails(@PathVariable("id") int id,@RequestBody Tbl_StatesEntity entity) {
		log.info("entered into updatestates based on id method in controller");

		Tbl_StatesEntity updatedetails = statesentityservice.updatestatesdetails(id, entity);
		return updatedetails;

		
	}

	@DeleteMapping("/delete/{id}")
	public Tbl_StatesEntityBean deleteById(@PathVariable("id") int id) {
		log.info("entered into delete states based on id method in controller");
		return statesentityservice.deleteById(id);
	}
	 
	@GetMapping("/getStateList")
	public LocationResponseBean getStateBasedOnCountry(@QueryParam(value = "countryId") int countryId) {
		log.info("Entered into fetching State Based on CountryId");
		
		return statesentityservice.getStateBasedOnCountry(countryId);
	}
	
	
		
	}
	
	
	

