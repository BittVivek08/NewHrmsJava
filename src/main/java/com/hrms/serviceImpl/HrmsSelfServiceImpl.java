package com.hrms.serviceImpl;

import javax.ws.rs.core.Response;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.repository.MyLeaveRequestRepository;
import com.hrms.request.bean.LeaveRequestBean;
import com.hrms.request.bean.LeaveResponseBean;
import com.hrms.service.IHrmsSelfService;

import ch.qos.logback.classic.Logger;

@Service
public class HrmsSelfServiceImpl implements IHrmsSelfService {
	
	private static final Logger logger=(Logger) LoggerFactory.getLogger(HrmsSelfServiceImpl.class);
	
	@Autowired
	private MyLeaveRequestRepository myLeaveRepo;

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

}
