package com.hrms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.WorkFlow;
import com.hrms.repository.WorkFlowRepository;
import com.hrms.service.WorkFlowService;





@Service
public class WorkFlowServiceImpl implements WorkFlowService{

	
	@Autowired
	private WorkFlowRepository workFlowRepo;
	
	
	@Override
	public List<WorkFlow> getAllRequests() {
		
		return workFlowRepo.findAll();
		
	}

}
