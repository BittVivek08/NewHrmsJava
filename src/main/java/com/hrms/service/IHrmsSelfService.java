package com.hrms.service;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.hrms.request.bean.LeaveRequestBean;

public interface IHrmsSelfService {

	public Response saveLeaveRequest(LeaveRequestBean leaverequestBean, @QueryParam(value = "roleId") int roleId,
			@QueryParam(value = "menuId") int menuId);
	
	
	public Response deleteMyLeave(@QueryParam(value = "id") int id);
	
	public Response fetchAppliedLeaveData(@QueryParam(value = "userId") int userId, @QueryParam("roleId") int roleId,
			@QueryParam("menuId") int menuId);
	
	public Response employeetotalleave(@QueryParam("id") int id);
}
