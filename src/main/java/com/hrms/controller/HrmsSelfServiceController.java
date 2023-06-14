package com.hrms.controller;

import javax.ws.rs.QueryParam;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.request.bean.LeaveRequestBean;

import com.hrms.request.bean.LeaveTypeRequest;
import com.hrms.request.bean.LeaveRequestUpdateDataBean;
import com.hrms.response.bean.CommonResponseBean;
import com.hrms.response.bean.LeaveResponseBean;
import com.hrms.response.bean.leaveReuestUpdateResponseBean;
import com.hrms.serviceImpl.HrmsSelfServiceImpl;

import ch.qos.logback.classic.Logger;


@RestController
@RequestMapping("self")
public class HrmsSelfServiceController {

	private static final Logger logger=(Logger) LoggerFactory.getLogger(HrmsSelfServiceController.class);

	@Autowired
	private HrmsSelfServiceImpl hrmsSelfService;


	// delete my leave details.
	@DeleteMapping("/leaveService")
	public LeaveResponseBean deleteMyLeave(@RequestParam int id) {
		logger.info("Entered into HrmsSelfServiceController in deleteMyLeave() ");
		logger.error("Existed from HrmsSelfServiceController deleteMyLeave() ");
		return hrmsSelfService.deleteMyLeave(id);
	}

	// Applied Leave History Data.
	@GetMapping("/leaveService")
	public  CommonResponseBean fetchAppliedLeaveData(@RequestParam String emp_id ,@RequestParam int roleId,
			@RequestParam int menuId) {
		logger.info("Entered into fetchAppliedLeaveData() ");
		logger.error("Existed from fetchAppliedLeaveData() ");
		return hrmsSelfService.getHistoryOfAppliedLeaveDetails(emp_id, roleId, menuId);
	}		


	//get total leave by id
	@GetMapping("/leaveServiceById")		
	public CommonResponseBean employeetotalleave(@RequestParam int id) {
		logger.info("Entered into HrmsSelfServiceController in employeetotalleave() ");
		return hrmsSelfService.totalLeaveTaken(id);
	}

	@PostMapping("/leaveService")
	public CommonResponseBean  saveLeaveRequest(@RequestBody LeaveRequestBean leaverequestBean ,
			@RequestParam(value ="emp_id") String emp_id ,@RequestParam(value="leaveType")String leaveType) {
		logger.info("Entered into getMyLeaveRequest() ");
		logger.error("Existed from getMyLeaveRequest() ");

		return hrmsSelfService.saveLeaveRequest(leaverequestBean,emp_id,leaveType);
	}

	@GetMapping ("/getAvailableDays")
	public float getAvailableDays(@RequestBody LeaveTypeRequest leaveTypeRequest) {
		logger.info("Entered into HrmsSelfServiceController in getAvailableDays() ");
		return hrmsSelfService.getAvailableDays(leaveTypeRequest.getEmpId(), leaveTypeRequest.getLeaveType());
	}

	//		@GetMapping ("/leaveServiceByLeaveType")
	//		public float getAvailableDays(@RequestParam(value = "emp_id") String empid,@RequestParam(value = "leaveType") String leaveType) {
	//			logger.info("Entered into HrmsSelfServiceController in getAvailableDays() ");
	//			return hrmsSelfService.getAvailableDays(empid, leaveType);
	//		}


	@PutMapping("/updateLeaveRequest")
	public leaveReuestUpdateResponseBean updateLeaveRequest(@RequestBody LeaveRequestUpdateDataBean bean,@QueryParam(value = "leaveReqId") int reqId) {
		leaveReuestUpdateResponseBean updateLeavRequest = this.hrmsSelfService.updateLeavRequest(bean, reqId);
		return updateLeavRequest;

	}
}
