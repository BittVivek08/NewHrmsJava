package com.hrms.service;


import javax.ws.rs.core.Response;

import com.hrms.request.bean.LeaveRequestBean;
import com.hrms.request.bean.LeaveRequestUpdateDataBean;
import com.hrms.response.bean.CommonResponseBean;
import com.hrms.response.bean.LeaveResponseBean;
import com.hrms.response.bean.leaveReuestUpdateResponseBean;

public interface IHrmsSelfService {

	public CommonResponseBean saveLeaveRequest(LeaveRequestBean leaverequestBean,String empId,String leaveType);
	
	
	public LeaveResponseBean deleteMyLeave(int id);
	
	public Response fetchAppliedLeaveData(int userId,  int roleId, int menuId);
	
	public Response employeetotalleave(int id);
	
	// history of applied leave Details.
	public CommonResponseBean getHistoryOfAppliedLeaveDetails(String userId, int roleId, int menuId);
	
	//get total leaves taken 
	public CommonResponseBean totalLeaveTaken(int id);
	
	public float getAvailableDays(String emp_id, String leaveType);


//	public Response saveLeaveRequest(LeaveRequestBean leaverequestBean, int roleId, int menuId);
	
	public leaveReuestUpdateResponseBean updateLeavRequest(LeaveRequestUpdateDataBean bean,int id);

}
