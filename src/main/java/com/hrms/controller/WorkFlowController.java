package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.entity.WorkFlow;
import com.hrms.request.bean.WorkFlowUpdateReqBean;
import com.hrms.response.bean.leaveReuestUpdateResponseBean;
import com.hrms.service.WorkFlowService;

@RestController
@RequestMapping("workflow")
public class WorkFlowController {

	@Autowired
	private WorkFlowService workFlowService;


	@GetMapping("/getRequestDetails")
	public ResponseEntity<List<WorkFlow>> getAllRequest() {
		List<WorkFlow> getAllRequest = workFlowService.getAllRequests();
		return new ResponseEntity<>(getAllRequest, HttpStatus.OK);
	}
	@GetMapping("/getRequestDetailsss")
	public ResponseEntity<List<WorkFlow>> getAllRequestSort() {
		List<WorkFlow> getAllRequest = workFlowService.getAllRequests();
		return new ResponseEntity<>(getAllRequest, HttpStatus.OK);
	}
	//	@GetMapping("/getRequestDetails")
	//	public ResponseEntity<List<WorkFlow>> getAllRequestSort() {
	//		List<WorkFlow> getAllRequest = workFlowService.getAllRequests();
	//		return new ResponseEntity<>(getAllRequest, HttpStatus.OK);
	//	}
	
	//public String updateStatus(@RequestBody)
	

	@GetMapping("/getRequestDetailsSort")
	public List<WorkFlow> getData(
			@RequestParam(required = false) String empid,
			@RequestParam(required = false) String rmid,
			@RequestParam(required = false) String status
			) {
		return workFlowService.getData(empid, rmid, status);
	}
	
	 @PutMapping("/updateReqStatus")
		public leaveReuestUpdateResponseBean updateLeaveRequest(@RequestBody WorkFlowUpdateReqBean workreq) {
			leaveReuestUpdateResponseBean updateLeavRequest = workFlowService.updateReqStatus(workreq);
			return updateLeavRequest;

		}

}














