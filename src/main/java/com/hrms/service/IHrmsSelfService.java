package com.hrms.service;


import javax.ws.rs.core.Response;

import com.hrms.request.bean.LeaveRequestBean;
import com.hrms.request.bean.LeaveResponseBean;

public interface IHrmsSelfService {

	public Response saveLeaveRequest(LeaveRequestBean leaverequestBean, int roleId, int menuId);
	
	
	public LeaveResponseBean deleteMyLeave(int id);
	
	public Response fetchAppliedLeaveData(int userId,  int roleId, int menuId);
	
	public Response employeetotalleave(int id);
}
