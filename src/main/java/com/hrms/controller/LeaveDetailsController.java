package com.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.binding.LeaveDetailsBinding;
import com.hrms.helper.EntityResponse;
import com.hrms.service.ILeaveDetailsService;

@RestController
@RequestMapping("/leaveDetails")
public class LeaveDetailsController {
	
	@Autowired
	private ILeaveDetailsService leaveService;

	@PostMapping("/save")
	public ResponseEntity<EntityResponse> saveLeaveDetails(@RequestBody LeaveDetailsBinding details){
		return new ResponseEntity<EntityResponse>(leaveService.saveLeaveDetails(details),HttpStatus.OK);
	}
	
}
