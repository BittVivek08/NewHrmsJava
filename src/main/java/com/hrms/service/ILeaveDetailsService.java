package com.hrms.service;

import com.hrms.binding.LeaveDetailsBinding;
import com.hrms.helper.EntityResponse;

public interface ILeaveDetailsService {

	public EntityResponse saveLeaveDetails(LeaveDetailsBinding details);
}
