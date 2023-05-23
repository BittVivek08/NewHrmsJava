package com.hrms.controller;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.request.bean.EmployeeLeaveTypeBean;
import com.hrms.request.bean.EmployeeLeaveTypeResponseBean;
import com.hrms.request.bean.RequestForLeaveBinding;
import com.hrms.response.bean.Common;
import com.hrms.response.bean.EntityResponse;
import com.hrms.response.bean.LeaveManagementOptionsResponseBean;
import com.hrms.response.bean.LeavesResponseBean;
import com.hrms.service.IRequestForLeaveService;

@RestController
@RequestMapping("/requestLeave")
public class RequestForLeaveController {

	private Logger logger = LoggerFactory.getLogger(RequestForLeaveController.class);

	@Autowired
	private IRequestForLeaveService reqLeaveService;
	
	
     	// My Leaves , Employee Leaves		
		@GetMapping("/getLeaveDetails/{user_id}/{leavestatus}/{view}")
		public LeavesResponseBean leaveDetails(@PathVariable(value = "user_id") String user_id,
				@PathVariable(value = "leavestatus") String leavestatus, @PathVariable(value = "view") String view) {
			logger.info("entered into leaveDetails method of service class...");	
			return reqLeaveService.getLeavesDetails(user_id, leavestatus, view);
		}

	@PostMapping("/save")
	public ResponseEntity<EntityResponse> saveRequestForLeave(@RequestBody RequestForLeaveBinding details) {
		return new ResponseEntity<EntityResponse>(reqLeaveService.saveRequestForLeave(details), HttpStatus.OK);
	}

	@PostMapping("/addNoOFDaysForEachUserID")
	public EmployeeLeaveTypeResponseBean saveEmployeeLeave(@RequestBody EmployeeLeaveTypeBean bean) {
		// return crud.saveEmployeeLeaveData(bean);
		return reqLeaveService.saveEmployeeLeaveData(bean);

	}
	
	
	
	// Leave Management Option
		@GetMapping("/getLeaveManagementOptions")
		public LeaveManagementOptionsResponseBean leaveManagementOptions() {
			logger.info("entered into leaveManagementOptions method of service class...");
			return reqLeaveService.leaveManagementOptions();
		}
		
	
	//get leave based on year
		@GetMapping("/getLeavesBasedOnYear/{year}")
		public Common getLeavesBasedOnYear(@PathVariable("year") int year) {
			return reqLeaveService.getLeavesBasedOnYear(year);
		}

}
