package com.hrms.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.request.bean.RequestForLeaveBinding;
import com.hrms.response.bean.EntityResponse;
import com.hrms.service.IRequestForLeaveService;

@RestController
@RequestMapping("/requestLeave")
public class RequestForLeaveController {

	private Logger logger=LoggerFactory.getLogger(RequestForLeaveController.class);
	
	@Autowired
	private IRequestForLeaveService reqLeaveService;
	
	
     	// My Leaves , Employee Leaves		
		@GetMapping("/getLeaveDetails")
		public Response leaveDetails(@QueryParam(value = "user_id") String user_id,
				@QueryParam(value = "leavestatus") String leavestatus, @QueryParam(value = "view") String view) {
			logger.info("entered into leaveDetails method of service class...");
			return (Response) reqLeaveService.getLeavesDetails(user_id, leavestatus, view);
		}

	
	
	@PostMapping("/save")
	public ResponseEntity<EntityResponse> saveRequestForLeave(@RequestBody RequestForLeaveBinding details){
		return new ResponseEntity<EntityResponse>(reqLeaveService.saveRequestForLeave(details),HttpStatus.OK);
	}
}
