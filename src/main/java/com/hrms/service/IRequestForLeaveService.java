package com.hrms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.hrms.entity.LeaveRequestEntity;
import com.hrms.request.bean.LeaveResponseBean;
import com.hrms.request.bean.RequestForLeaveBinding;
import com.hrms.response.bean.EntityResponse;

public interface IRequestForLeaveService {

	public EntityResponse saveRequestForLeave(RequestForLeaveBinding details);
	
	public List<LeaveRequestEntity> getLeavesDetails(String user_id, String leavestatus, String view);
	
}
