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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/business")
@CrossOrigin(origins = "http://localhost:4200")
public class BusinessunitController {

	@Autowired
	private BusinessunitServicelmpl BusinessunitService;
	
	@PostMapping("/savebusinessdetails")
	public Businessbean saveBusinessDetails(@RequestBody Businessunit businessunit) {
		log.info("BusinessunitController , savebusinessdetails method");
		return BusinessunitService.saveBusinessDetails(businessunit);
	}

	@GetMapping("/getbusinessdetails/{bid}")
	public Businessunit getByBId(@PathVariable("bid") int bid) {
		log.info("entered into getbusinessdetails based on bid method in controller");
		return BusinessunitService.getByBid(bid);
	}

	@GetMapping("/getbusinessdetails")
	public ResponseEntity<List<Businessunit>> getbusinessDetails() {
		log.info("entered into getbusinessdetails method in controller");
		List<Businessunit> details = BusinessunitService.getAllbusinessdetails();
		log.info("successfully fetched businessdetails in controlller");
		return new ResponseEntity<>(details, HttpStatus.OK);

	}

	@PutMapping("/Updatebusinessdetails/{bid}")
	public ResponseEntity<Businessunit> updatebusinessdetails(@PathVariable("bid") int bid,
			@RequestBody Businessunit entity) {
		log.info("entered into updatebusinessdetails based on bid method in controller");

		Businessunit updatedetails = BusinessunitService.updatebusinessdetails(bid, entity);
		log.info("Successfully updated busniessdetails in controller");

		return ResponseEntity.ok(updatedetails);

	}

	@DeleteMapping("/delete/{bid}")
	public ResponseEntity<Businessbean> deleteById(@PathVariable("bid") int bid) {
		log.info("entered into delete details based on bid method in controller");
		 Businessbean deleteByBid = BusinessunitService.deleteByBid(bid);	
		 return new ResponseEntity<>(deleteByBid,HttpStatus.BAD_REQUEST);
	}
}