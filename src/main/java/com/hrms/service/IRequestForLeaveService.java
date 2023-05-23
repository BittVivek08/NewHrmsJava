package com.hrms.service;


import com.hrms.beans.EmailDetails;
import com.hrms.beans.MailStatusResponse;
import com.hrms.request.bean.EmployeeLeaveTypeBean;
import com.hrms.request.bean.EmployeeLeaveTypeResponseBean;
import com.hrms.request.bean.RequestForLeaveBinding;
import com.hrms.request.bean.UpdateEmployeeLeaveDetails;
import com.hrms.response.bean.EntityResponse;
import com.hrms.response.bean.LeaveManagementOptionsResponseBean;
import com.hrms.response.bean.LeavesResponseBean;
import com.mysql.cj.jdbc.result.UpdatableResultSet;

public interface IRequestForLeaveService {

	public EntityResponse saveRequestForLeave(RequestForLeaveBinding details);
	
	public LeavesResponseBean getLeavesDetails(String user_id, String leavestatus, String view);
	
	public LeaveManagementOptionsResponseBean leaveManagementOptions();

	public EmployeeLeaveTypeResponseBean saveEmployeeLeaveData(EmployeeLeaveTypeBean leaveBean);
	
	//mailLeave
	public  MailStatusResponse mailsend(UpdateEmployeeLeaveDetails updateBean,String eid );
	
}
