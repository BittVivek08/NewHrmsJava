package com.hrms.service;

import com.hrms.binding.RequestForLeaveBinding;
import com.hrms.helper.EntityResponse;

public interface IRequestForLeaveService {

	public EntityResponse saveRequestForLeave(RequestForLeaveBinding details);
	
}
