package com.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.binding.RequestForLeaveBinding;
import com.hrms.helper.EntityResponse;
import com.hrms.service.IRequestForLeaveService;

@RestController
@RequestMapping("/requestLeave")
public class RequestForLeaveController {

	@Autowired
	private IRequestForLeaveService reqLeaveService;
	
	@PostMapping("/save")
	public ResponseEntity<EntityResponse> saveRequestForLeave(@RequestBody RequestForLeaveBinding details){
		return new ResponseEntity<EntityResponse>(reqLeaveService.saveRequestForLeave(details),HttpStatus.OK);
	}
}
