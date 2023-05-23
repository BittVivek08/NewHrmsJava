package com.hrms.controller;



import javax.ws.rs.QueryParam;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.entity.LeaveRequestEntity;
import com.hrms.response.bean.CommonResponseBean;
import com.hrms.response.bean.LeaveResponseBean;
import com.hrms.serviceImpl.HrmsSelfServiceImpl;
import ch.qos.logback.classic.Logger;


@RestController
@RequestMapping("self")
public class HrmsSelfServiceController {
	
	private static final Logger logger=(Logger) LoggerFactory.getLogger(HrmsSelfServiceController.class);
	
	@Autowired
	private HrmsSelfServiceImpl hrmsSelfService;
	
	
	    // delete my leave details.
		@DeleteMapping("/DeleteMyLeave")
		public LeaveResponseBean deleteMyLeave(@QueryParam(value = "id") int id) {
			logger.info("Entered into HrmsSelfServiceController in deleteMyLeave() ");
			logger.error("Existed from HrmsSelfServiceController deleteMyLeave() ");
			return hrmsSelfService.deleteMyLeave(id);
		}
		
		// Applied Leave History Data.
		@GetMapping("/appliedLeavesHistory/{emp_id}/{roleId}/{menuId}")
		public  CommonResponseBean fetchAppliedLeaveData(@PathVariable(value = "emp_id") String emp_id, @PathVariable("roleId") int roleId,
				@PathVariable("menuId") int menuId) {
			logger.info("Entered into fetchAppliedLeaveData() ");
			logger.error("Existed from fetchAppliedLeaveData() ");
			return hrmsSelfService.getHistoryOfAppliedLeaveDetails(emp_id, roleId, menuId);
		}
		
		
		//get total leave by id
		@GetMapping("/employeetotalleave/{id}")		
		public CommonResponseBean employeetotalleave(@PathVariable("id") int id) {
			return hrmsSelfService.totalLeaveTaken(id);
		}

}
