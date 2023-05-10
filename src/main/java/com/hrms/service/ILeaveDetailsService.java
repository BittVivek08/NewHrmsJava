package com.hrms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.hrms.beans.EntityBeanResponse;
import com.hrms.entity.LeaveDetails;
import com.hrms.request.bean.LeaveDetailsBinding;
import com.hrms.response.bean.EntityResponse;

public interface ILeaveDetailsService {

	public EntityResponse saveLeaveDetails(LeaveDetailsBinding details);
	
	public List<LeaveDetails> getAllLeaveDetails();
	
	public LeaveDetails  getLeaveDetailsById( Integer id);
	
	public EntityBeanResponse updateLeave( LeaveDetails details);
	
	
	
	
}
