package com.hrms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hrms.beans.EntityBeanResponse;
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
	
	private EntityBeanResponse ebr;
	
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
		
		leaveRepo.save(leaveDetails);
		
		response.setMsg("Leave Details are save");
		response.setStatus(true);
		
		return response;
	}

	@Override
	public List<LeaveDetails> getAllLeaveDetails() {
		// TODO Auto-generated method stub
		return   leaveRepo.findAll();
	}

	@Override
	public LeaveDetails  getLeaveDetailsById(Integer id) {
		Optional<LeaveDetails> findById = leaveRepo.findById(id);

		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public EntityBeanResponse updateLeave(LeaveDetails details) {
		LeaveDetails update = leaveRepo.save(details);
		if (update != null) {
			ebr.setMsg("Employee Details Updated Successfully");
			ebr.setStatus(true);
		} else {
			ebr.setMsg("Employee Details Updation Failed");
			ebr.setStatus(false);
		}
		return ebr;
	}

}
