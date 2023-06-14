package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.entity.WorkFlow;
import com.hrms.service.WorkFlowService;

@RestController
@RequestMapping("workflow")
public class WorkFlowController {

	@Autowired
	private WorkFlowService workFlowService;
	
	
	@GetMapping("/getEmployeeDetails")
	public ResponseEntity<List<WorkFlow>> getAllRequest() {
		List<WorkFlow> getAllRequest = workFlowService.getAllRequests();
		return new ResponseEntity<>(getAllRequest, HttpStatus.OK);
	}
	
}
