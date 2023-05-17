package com.hrms.service;

import java.util.List;




import com.hrms.request.bean.RequestForLeaveBinding;
import com.hrms.response.bean.EntityResponse;

public interface IRequestForLeaveService {

	public EntityResponse saveRequestForLeave(RequestForLeaveBinding details);
	
//	public List<LeaveRequestEntity> getLeavesDetails(String user_id, String leavestatus, String view);
	
}
