package com.hrms.serviceImpl;

import javax.ws.rs.core.Response;

import com.hrms.request.bean.LeaveRequestBean;
import com.hrms.service.IHrmsSelfService;

public class HrmsSelfService implements IHrmsSelfService {

	@Override
	public Response saveLeaveRequest(LeaveRequestBean leaverequestBean, int roleId, int menuId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteMyLeave(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response fetchAppliedLeaveData(int userId, int roleId, int menuId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response employeetotalleave(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
