package com.hrms.service;

import java.util.List;
import com.hrms.entity.WorkFlow;

public interface WorkFlowService {

	public List<WorkFlow> getAllRequests();
	
	public List<WorkFlow> getData(String empid,String rmId,String status);
}