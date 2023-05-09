package com.hrms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.LeaveDetails;
import com.hrms.service.ILeaveDetailsService;
import com.hrms.repository.EmployeeRepository;
import com.hrms.request.bean.LeaveDetailsBinding;
import com.hrms.response.bean.EntityResponse;
import com.hrms.repository.*;

@Service("leave")
public class LeaveDetailsServiceImpl implements ILeaveDetailsService {
	
	@Autowired(required = false)
	private ILeaveDetailsRepository leaveRepo;
	
	@Autowired
	private EntityResponse response;
	
	@Autowired(required = false)
	private EmployeeRepository employeeRepo;
	
	@Override
	public EntityResponse saveLeaveDetails(LeaveDetailsBinding details) {
		
		//convert leaveDetailsBinding obj to LeaveDetails obj
		LeaveDetails leaveDetails=new LeaveDetails();
		leaveDetails.setLeaveType(details.getLeaveType());
		leaveDetails.setTotalLeave(Integer.parseInt(details.getTotalLeave()));
	//	leaveDetails.setEmpId(Integer.valueOf(details.getEmpId()));
		
	//	EmployeeDetails empDetails=employeeRepo.findById(Integer.valueOf(details.getEmpId())).get();
//		leaveDetails.setDetails(empDetails);
		
		//save leaveDetails obj
		leaveRepo.save(leaveDetails);
		
		response.setMsg("Leave Details are save");
		response.setStatus(true);
		
		return response;
	}

}
