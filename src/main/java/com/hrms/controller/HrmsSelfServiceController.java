package com.hrms.controller;


import javax.ws.rs.QueryParam;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.request.bean.LeaveResponseBean;
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

}
