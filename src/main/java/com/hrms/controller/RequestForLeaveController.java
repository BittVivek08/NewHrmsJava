package com.hrms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.hrms.beans.MailStatusResponse;

import com.hrms.request.bean.EmployeeLeaveTypeBean;
import com.hrms.request.bean.EmployeeLeaveTypeResponseBean;
import com.hrms.request.bean.LeaveDetailsFiltaring;
import com.hrms.request.bean.RequestForLeaveBinding;

import com.hrms.request.bean.UpdateEmployeeLeaveDetails;
import com.hrms.request.bean.UpdateLeaveRequest;
import com.hrms.response.bean.Common;
import com.hrms.response.bean.EmpLeaveResponseBean;
import com.hrms.response.bean.EmployeeLeaveResponse;
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
	
	
	
	// 1. update Employee leave Summary details.
		@PutMapping("/UpdateEmployeeLeave")
		public EmpLeaveResponseBean updateEmployeeLeaveSummaryDetails(UpdateLeaveRequest updateBean) {
			return reqLeaveService.updateAllLeaveSummary(updateBean);
		}


	//Update Apply Leave Requeste
	@PutMapping("/updateEmpLeaveStatMail/{eid}")
	public MailStatusResponse updateLeaveReqOfEmp(@RequestBody UpdateEmployeeLeaveDetails bean,@PathVariable("eid") String eid) {
		
		this.logger.info("Entered mail send and update leava status in controller");

		MailStatusResponse mailsend = this.reqLeaveService.mailsend(bean, eid);
		

		this.logger.info("successfully  mail sent and updated leava status in controller");

		return mailsend;

	}
	
	@GetMapping("/getLeavesBasedOnYear/{year}")
	public Common getLeavesBasedOnYear(@PathVariable("year") int year) {
		return reqLeaveService.getLeavesBasedOnYear(year);
	}
	
	

	//get leaves based on month
	@GetMapping("/getLeaveDetailsByMonth/{id}/{view}/{month}/{leaveStatus}")
	public LeavesResponseBean getLeaveDetailsByMonth(@PathVariable(value = "id") int id, @PathVariable(value = "view") String view,
			@PathVariable(value = "month") int month,@PathVariable(value = "leaveStatus") String leavestatus) {
		logger.info("entered into leaveDetails method of service class...");
		return reqLeaveService.getLeavesByMonth(view, id, month,leavestatus);
	}   
	
	   // Get Employee Details Whose On The Particulars Date leave
		@GetMapping("/getLeaveDataByReqIdDate/{leaveReqId}/{date}")
		public List<EmployeeLeaveResponse> getLeaveDataByReqIdDate(@PathVariable("leaveReqId") int leaveReqId, @PathVariable("date") String date) {
			logger.info("entered into getEmployeeLeavedDetails method of service class...");
			return reqLeaveService.getLeaveDataByReqIdDate(leaveReqId, date);
		}
		
	//get leave details based on manager id and status
		@GetMapping("/getLeaveDetailsBasedOnManagerId/{reportingManagerId}/{leaveStatus}/{emp_id}")
		public LeavesResponseBean getLeaveDetailsBasedOnManagerId(@PathVariable(value = "reportingManagerId") int managerId,
				@PathVariable(value = "leaveStatus") String leavestatus,@PathVariable(value = "emp_id") String emp_id ) {
			
			logger.info("entered into getLeaveDetailsBasedOnManagerId method of service class...");
			return reqLeaveService.getLeaveDetailsForManager( managerId, leavestatus, emp_id);
			
			
		}
		
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++		
		
		
		
		
		
		
			
}
