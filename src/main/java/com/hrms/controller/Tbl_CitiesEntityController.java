package com.hrms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hrms.beans.Tbl_CitiesEntityBean;
import com.hrms.entity.Tbl_CitiesEntity;
import com.hrms.service.Tbl_CitiesEntityService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/citiesentity")
@RestController
public class Tbl_CitiesEntityController {
	
	
	
	@Autowired
	private Tbl_CitiesEntityService citiesentityservice;
	
	@PostMapping("/savecitiesdetails")
	public Tbl_CitiesEntityBean savecitiesdetails(@RequestBody Tbl_CitiesEntity citiesentity) {
		log.info("entered into savecitiesdetails  method in controller");
		
		
		return citiesentityservice.savecitiesDetails(citiesentity); 
	}

	@GetMapping("/getcitiesdetails/{id}")
	public Tbl_CitiesEntity getById(@PathVariable("id") int id) {
		log.info("entered into getcitiesdetails based on id method in controller");
		return citiesentityservice.getById(id);
	}

	@GetMapping("/getcitiesdetails")
	public ResponseEntity<List<Tbl_CitiesEntity>> getcitiesDetails() {
		log.info("entered into getcitiessdetails  method in controller");
		List<Tbl_CitiesEntity> details = citiesentityservice.getcitiesDetails();
		log.info("successfully fetched citiesdetails in controller");
		return new ResponseEntity<>(details, HttpStatus.OK);

	}

	@PutMapping("/Updatecitiesdetails/{id}")
	public Tbl_CitiesEntity updatecitiesdetails(@PathVariable("id") int id,
			@RequestBody Tbl_CitiesEntity entity) {
		log.info("entered into updatecitiesdetails based on id method in controller");

		Tbl_CitiesEntity updatedetails = citiesentityservice.updatecitiesdetails(id, entity);
		return updatedetails;

		
	}
}

//	@DeleteMapping("/delete/{id}")
//	public Tbl_CitiesEntityBean deleteById(@PathVariable("id") int id) {
//		return citiesentityservice.deleteById(id);
//	}
//}
//

	
	
	


