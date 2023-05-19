package com.hrms.service;


import com.hrms.request.bean.RequestForLeaveBinding;
import com.hrms.response.bean.EntityResponse;
import com.hrms.response.bean.LeaveManagementOptionsResponseBean;
import com.hrms.response.bean.LeavesResponseBean;

public interface IRequestForLeaveService {

	public EntityResponse saveRequestForLeave(RequestForLeaveBinding details);
	
	public LeavesResponseBean getLeavesDetails(String user_id, String leavestatus, String view);
	
	public LeaveManagementOptionsResponseBean leaveManagementOptions();
}
