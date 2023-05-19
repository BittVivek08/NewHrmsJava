package com.hrms.serviceImpl;

import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.entity.LeaveRequestEntity;
import com.hrms.entity.Privileges;
import com.hrms.repository.LeaveRequestRepository;
import com.hrms.repository.MyLeaveRequestRepository;
import com.hrms.repository.PrivilegesRepo;
import com.hrms.request.bean.LeaveRequestBean;
import com.hrms.response.bean.CommonResponseBean;
import com.hrms.response.bean.LeaveResponseBean;
import com.hrms.service.IHrmsSelfService;

import ch.qos.logback.classic.Logger;

@Service
public class HrmsSelfServiceImpl implements IHrmsSelfService {
	
	private static final Logger logger=(Logger) LoggerFactory.getLogger(HrmsSelfServiceImpl.class);
	
	@Autowired
	private MyLeaveRequestRepository myLeaveRepo;
	
	@Autowired
	private LeaveRequestRepository leaveRequestRepo;
	
	@Autowired
	private PrivilegesRepo privilegeRepo;

	@Override
	public Response saveLeaveRequest(LeaveRequestBean leaverequestBean, int roleId, int menuId) {
		
		return null;
	}

	// selfService->Leaves->MyLeaves->Delete
		public LeaveResponseBean deleteMyLeave(int id) {
			logger.info("entered into deleteMyLeaveDetails method of business class");
			LeaveResponseBean responseBean = new LeaveResponseBean();
			try {
				myLeaveRepo.deleteById(id);
				responseBean.setMessage("Deleted Succesfully::");
				responseBean.setStatus(true);
				//return Response.status(Response.Status.OK).entity(responseBean).build();
				return responseBean;
			} catch (Exception e) {
				logger.info("catch block of deleteMyLeaveDetails of business class::" + e);
				responseBean.setMessage("something went wrong ::");
				responseBean.setStatus(false);
			//	return Response.status(Response.Status.OK).entity(responseBean).build();
				return responseBean;
			}
		}
	@Override
	public Response fetchAppliedLeaveData(int userId, int roleId, int menuId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response employeetotalleave(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponseBean getHistoryOfAppliedLeaveDetails(String emp_id, int roleId, int menuId) {
		logger.info("entered into getHistoryOfAppliedLeaveDetails method of business class");
		CommonResponseBean response = new CommonResponseBean();
		List<LeaveRequestEntity> fetchAppliedLeaveRequest = leaveRequestRepo.fetchAppliedLeaveRequest(emp_id);
				
		List<Privileges> listOfPrivilleges = privilegeRepo.getPrivileges(roleId, menuId);
		if (!fetchAppliedLeaveRequest.isEmpty()) {
			response.setMessage("History of applied leave Details Retrieved Successfully.");
			response.setStatus(true);
			response.setList(fetchAppliedLeaveRequest);
			response.setPrivilleges(listOfPrivilleges);
		} else {
			response.setMessage("Failed to Retrieved applied  Leaves History .");
			response.setStatus(false);
		}
		return response;		
		//return null;
	}

}
