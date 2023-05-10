package com.hrms.request.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestForLeaveBinding {

	//private Integer id;
//	private int mappingId;
	private String emp_id;
	private String leaveType;
	private String startDate;
	private String endDate;
	private String reason;
	}
