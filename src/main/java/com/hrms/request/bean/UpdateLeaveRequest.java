package com.hrms.request.bean;

import java.util.List;

import lombok.Data;


@Data
public class UpdateLeaveRequest {

	
	   private List<LeaveRequestBean> leaveBean;
	    private Integer modifiedBy;

}
