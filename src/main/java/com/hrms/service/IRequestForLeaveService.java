package com.hrms.service;


import java.util.Date;
import java.util.List;

import com.hrms.beans.MailStatusResponse;
import com.hrms.entity.MyLeaveRequestEntity;
import com.hrms.request.bean.EmployeeLeaveTypeBean;
import com.hrms.request.bean.EmployeeLeaveTypeResponseBean;
import com.hrms.request.bean.LeaveDetailsFiltaring;
import com.hrms.request.bean.RequestForLeaveBinding;
import com.hrms.request.bean.UpdateEmployeeLeaveDetails;
import com.hrms.request.bean.UpdateLeaveRequest;
import com.hrms.response.bean.Common;
import com.hrms.response.bean.EmpLeaveResponseBean;
import com.hrms.response.bean.EmployeeLeaveResponse;
import com.hrms.response.bean.EntityResponse;
import com.hrms.response.bean.LeaveFilterResponse;
import com.hrms.response.bean.LeaveManagementOptionsResponseBean;
import com.hrms.response.bean.LeavesResponseBean;

public interface IRequestForLeaveService {

	public EntityResponse saveRequestForLeave(RequestForLeaveBinding details);
	
	public LeavesResponseBean getLeavesDetails(String user_id, String leavestatus, String view);
	
  public LeaveManagementOptionsResponseBean leaveManagementOptions();

	public EmployeeLeaveTypeResponseBean saveEmployeeLeaveData(EmployeeLeaveTypeBean leaveBean);
	
	//mailLeave
	public  MailStatusResponse mailsend(UpdateEmployeeLeaveDetails updateBean,String eid );

	
	
	
	// 1.update for Employee leaveRequest Summary
	public EmpLeaveResponseBean updateAllLeaveSummary(UpdateLeaveRequest updateBean);
	
	
    public LeavesResponseBean getLeaveDetailsForManager(int managerid,String leavestatus,String emp_id);
    
    public List<MyLeaveRequestEntity> getLeaveHistoryByConditions(int year,int month,String status);
    
    public String assignLeave();
	
    
}
