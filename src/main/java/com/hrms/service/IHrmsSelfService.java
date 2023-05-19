package com.hrms.service;


import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.http.ResponseEntity;

import com.hrms.entity.LeaveRequestEntity;
import com.hrms.request.bean.LeaveRequestBean;
import com.hrms.response.bean.CommonResponseBean;
import com.hrms.response.bean.LeaveResponseBean;

public interface IHrmsSelfService {

	public Response saveLeaveRequest(LeaveRequestBean leaverequestBean, int roleId, int menuId);
	
	
	public LeaveResponseBean deleteMyLeave(int id);
	
	public Response fetchAppliedLeaveData(int userId,  int roleId, int menuId);
	
	public Response employeetotalleave(int id);
	
	// history of applied leave Details.
	public CommonResponseBean getHistoryOfAppliedLeaveDetails(String userId, int roleId, int menuId);
}
