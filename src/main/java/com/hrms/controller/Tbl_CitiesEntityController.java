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
import com.hrms.beans.Tbl_CitiesEntityBean;
import com.hrms.entity.Tbl_CitiesEntity;
import com.hrms.service.Tbl_CitiesEntityService;


@RequestMapping("/citiesentity")
@RestController
public class Tbl_CitiesEntityController {
	
	
	
	@Autowired
	private Tbl_CitiesEntityService citiesentityservice;
	
	@PostMapping("/savecitiesdetails")
	public Tbl_CitiesEntityBean savecitiesdetails(@RequestBody Tbl_CitiesEntity citiesentity) {
		
		return citiesentityservice.savecitiesDetails(citiesentity); 
	}

	@GetMapping("/getcitiesdetails/{id}")
	public Tbl_CitiesEntity getById(@PathVariable("id") int id) {
		return citiesentityservice.getById(id);
	}

	@GetMapping("/getcitiesdetails")
	public ResponseEntity<List<Tbl_CitiesEntity>> getcitiesDetails() {
		List<Tbl_CitiesEntity> details = citiesentityservice.getcitiesDetails();
		return new ResponseEntity<>(details, HttpStatus.OK);

	}

	@PutMapping("/Updatecitiesdetails/{id}")
	public Tbl_CitiesEntity updatecitiesdetails(@PathVariable("id") int id,
			@RequestBody Tbl_CitiesEntity entity) {

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

	
	
	


