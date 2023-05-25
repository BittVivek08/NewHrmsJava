package com.hrms.service;


import com.hrms.beans.MailStatusResponse;

import com.hrms.request.bean.EmployeeLeaveTypeBean;
import com.hrms.request.bean.EmployeeLeaveTypeResponseBean;
import com.hrms.request.bean.LeaveDetailsFiltaring;
import com.hrms.request.bean.RequestForLeaveBinding;
import com.hrms.request.bean.UpdateEmployeeLeaveDetails;
import com.hrms.response.bean.Common;
import com.hrms.response.bean.EntityResponse;
import com.hrms.response.bean.LeaveManagementOptionsResponseBean;
import com.hrms.response.bean.LeavesResponseBean;

public interface IRequestForLeaveService {

	public EntityResponse saveRequestForLeave(RequestForLeaveBinding details);
	
	public LeavesResponseBean getLeavesDetails(String user_id, String leavestatus, String view);
	
	public LeaveManagementOptionsResponseBean leaveManagementOptions();

	public EmployeeLeaveTypeResponseBean saveEmployeeLeaveData(EmployeeLeaveTypeBean leaveBean);
	
	//mailLeave
	public  MailStatusResponse mailsend(UpdateEmployeeLeaveDetails updateBean,String eid );

	public LeavesResponseBean getLeavesBasedOnCondition(LeaveDetailsFiltaring detailsFiltaring);
	
	
	//get leaves based on year
	public Common getLeavesBasedOnYear(int year);
	
	//get Leaves based on month
	public LeavesResponseBean getLeavesByMonth(String view, int id,int month,String leavestatus);
	
	
	
}
