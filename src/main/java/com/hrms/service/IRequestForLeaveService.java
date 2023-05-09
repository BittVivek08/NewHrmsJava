package com.hrms.service;

import com.hrms.request.bean.RequestForLeaveBinding;
import com.hrms.response.bean.EntityResponse;

public interface IRequestForLeaveService {

	public EntityResponse saveRequestForLeave(RequestForLeaveBinding details);
	
}
