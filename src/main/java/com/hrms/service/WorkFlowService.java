package com.hrms.service;

import java.util.List;
import com.hrms.entity.WorkFlow;
import com.hrms.request.bean.WorkFlowUpdateReqBean;
import com.hrms.response.bean.leaveReuestUpdateResponseBean;

public interface WorkFlowService {

	public List<WorkFlow> getAllRequests();
	
	public List<WorkFlow> getData(String empid,String rmId,String status);
	
	public leaveReuestUpdateResponseBean updateReqStatus(WorkFlowUpdateReqBean workreq);
}