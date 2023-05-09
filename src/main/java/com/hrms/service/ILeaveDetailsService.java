package com.hrms.service;

import com.hrms.request.bean.LeaveDetailsBinding;
import com.hrms.response.bean.EntityResponse;

public interface ILeaveDetailsService {

	public EntityResponse saveLeaveDetails(LeaveDetailsBinding details);
}
