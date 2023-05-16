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



@RequestMapping("/countries")
@RestController
public class Tbl_CountriesEntityController {
	@Autowired
	private Tbl_CountriesEntityService countriesenitityservice;
	
	@PostMapping("/savecountries")
	public Tbl_CountriesEntityBean savecountriesdetails(@RequestBody Tbl_CountriesEntity countriesentity)
	{
		return countriesenitityservice.savecountriesdetails(countriesentity);
	}

	
	
	@GetMapping("/getcountriesdetails/{id}")
	public Tbl_CountriesEntity getById(@PathVariable("id") int id) {
		return countriesenitityservice.getById(id);
	}

	@GetMapping("/getcountriesdetails")
	public ResponseEntity<List<Tbl_CountriesEntity>> getcountriesDetails() {
		List<Tbl_CountriesEntity> details = countriesenitityservice.getcountriesDetails();
		return new ResponseEntity<>(details, HttpStatus.OK);

	}  

	@PutMapping("/Updatecountries/{id}")
	public Tbl_CountriesEntity updatecountriesdetails(@PathVariable("id") int id,@RequestBody Tbl_CountriesEntity entity) {

		Tbl_CountriesEntity updatedetails = countriesenitityservice.updatecountriedetails(id, entity);
		return updatedetails;

		
	}

	@DeleteMapping("/delete/{id}")
	public Tbl_CountriesEntityBean deleteById(@PathVariable("id") int id) {
		return countriesenitityservice.deleteById(id);
	}
}


	
	
	



	
	


