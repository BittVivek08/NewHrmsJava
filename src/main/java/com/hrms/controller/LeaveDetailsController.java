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

import com.hrms.beans.EntityBeanResponse;
import com.hrms.entity.LeaveDetails;
import com.hrms.request.bean.LeaveDetailsBinding;
import com.hrms.response.bean.EntityResponse;
import com.hrms.service.ILeaveDetailsService;

@RestController
@RequestMapping("/leaveDetails")
public class LeaveDetailsController {
	
	@Autowired
	private ILeaveDetailsService leaveService;
	
	private EntityBeanResponse ebr;

	@PostMapping("/save")
	public ResponseEntity<EntityResponse> saveLeaveDetails(@RequestBody LeaveDetailsBinding details){
		return new ResponseEntity<EntityResponse>(leaveService.saveLeaveDetails(details),HttpStatus.OK);
	}
	@GetMapping("/getLeaveDetails")
	public List<LeaveDetails> getAllLeaveDetails() {
		List<LeaveDetails> allLeaveDetails = leaveService.getAllLeaveDetails();
		return allLeaveDetails;
	}
	@GetMapping("/getLeaveDetailById/{id}")
	public LeaveDetails getLeaveDetailsById(@PathVariable Integer id) {
		LeaveDetails leaveDetailsById = leaveService.getLeaveDetailsById(id);
		return leaveDetailsById;
	}
	@PutMapping("/updateLeaveDetails")
	public EntityBeanResponse updateLeaveDetails(@RequestBody LeaveDetails details) {
		return leaveService.updateLeave(details);
	}
	
}
