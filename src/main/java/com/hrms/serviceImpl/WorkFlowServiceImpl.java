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

	
//	public List<WorkFlow> getAllRequests(String emp_id, String Status,) {
//        if (emp_id != null) {
//            // If the 'id' parameter is provided, retrieve data by id
//            return workFlowRepo.findByEmp_id(emp_id);
//        } else if (name != null && status != null) {
//            // If both 'name' and 'status' parameters are provided, retrieve data by name and status
//            return dataRepository.findByNameAndStatus(name, status);
//        } else if (status != null) {
//            // If only 'status' parameter is provided, retrieve data by status
//            return dataRepository.findByStatus(status);
//        } else {
//            // If no parameters are provided, retrieve all data
//            return dataRepository.findAll();
//        }
//	}
	
}
