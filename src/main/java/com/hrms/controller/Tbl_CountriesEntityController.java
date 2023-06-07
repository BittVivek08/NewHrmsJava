package com.hrms.controller;

import java.util.List;

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

import com.hrms.beans.Tbl_CountriesEntityBean;
import com.hrms.entity.Tbl_CountriesEntity;
import com.hrms.service.Tbl_CountriesEntityService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/countries")
@RestController
public class Tbl_CountriesEntityController {
	@Autowired
	private Tbl_CountriesEntityService countriesenitityservice;
	
	@PostMapping("/savecountries")
	public Tbl_CountriesEntityBean savecountriesdetails(@RequestBody Tbl_CountriesEntity countriesentity)
	{
		log.info("entered into savecountriesdetails  method in controller");
		return countriesenitityservice.savecountriesdetails(countriesentity);
	}

	
	
	@GetMapping("/getcountriesdetails/{id}")
	public Tbl_CountriesEntity getById(@PathVariable("id") int id) {
		log.info("entered into getcountriesdetails  based on id in controller");
		return countriesenitityservice.getById(id);
	}

	@GetMapping("/getcountriesdetails")
	public ResponseEntity<List<Tbl_CountriesEntity>> getcountriesDetails() {
		log.info("entered into getcountriesdetails  method in controller");
		List<Tbl_CountriesEntity> details = countriesenitityservice.getcountriesDetails();
		log.info("successfully fetched countriesdetails in controlller");
		
		return new ResponseEntity<>(details, HttpStatus.OK);

	}  

	@PutMapping("/Updatecountries/{id}")
	public Tbl_CountriesEntity updatecountriesdetails(@PathVariable("id") int id,@RequestBody Tbl_CountriesEntity entity) {
		log.info("entered into updatecountriesdetails  method in controller");

		Tbl_CountriesEntity updatedetails = countriesenitityservice.updatecountriedetails(id, entity);
		log.info("Successfully updatecountriesdetails  method in controller");
		return updatedetails;

		
	}

	@DeleteMapping("/delete/{id}")
	public Tbl_CountriesEntityBean deleteById(@PathVariable("id") int id) {
		log.info("entered into deletecountriesdetails based on id  method in controller");
		return countriesenitityservice.deleteById(id);
	}
}


	
	
	



	
	


