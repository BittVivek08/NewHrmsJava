package com.hrms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hrms.beans.Businessbean;
import com.hrms.entity.Businessunit;
import com.hrms.serviceImpl.BusinessunitServicelmpl;
import com.hrms.service.BusinessunitServicelmpl;

@RestController
@RequestMapping("/business")
@CrossOrigin
public class BusinessunitController {

	@Autowired
	private BusinessunitServicelmpl BusinessunitService;

	@PostMapping("/savebusinessdetails")
	public Businessbean saveBusinessDetails(@RequestBody Businessunit businessunit) {

		return BusinessunitService.saveBusinessDetails(businessunit);
	}
	
@GetMapping("/getbusinessdetails/{bid}")
public  Businessunit getByBId(@PathVariable("bid") int bid) {
	return BusinessunitService.getByBid(bid);
}



	@GetMapping("/getbusinessdetails")
	public ResponseEntity<List<Businessunit>> getbusinessDetails() {
		List<Businessunit> details = BusinessunitService.getAllbusinessdetails();
		return new ResponseEntity<>(details, HttpStatus.OK);

	}

	@PutMapping("/Updatebusinessdetails/{bid}")
	public ResponseEntity<Businessunit> updatebusinessdetails(@PathVariable("bid") int bid,
			@RequestBody Businessunit entity) {

		Businessunit updatedetails = BusinessunitService.updatebusinessdetails(bid, entity);

		return  ResponseEntity.ok(updatedetails);

	}


	@DeleteMapping("/delete/{bid}")
	public Businessbean deleteById(@PathVariable("bid") int bid) {
		return BusinessunitService.deleteByBid(bid);
	}
}

//@PutMapping("/Updatebusinessdetails/{id}")
//public ResponseEntity<Businessunit> updatebusinessdetails(@PathVariable("id") int id,
//		@RequestBody Businessunit entity) {
//
//	Businessunit updatedetails = BusinessunitService.updatebusinessdetails(id, entity);
//
//	return new ResponseEntity<>(updatedetails, HttpStatus.OK);
//
//}
//
//@DeleteMapping("/delete/{id}")
//public Businessbean deleteById(@PathVariable("id") int id) {
//	return BusinessunitService.deleteById(id);
//}
