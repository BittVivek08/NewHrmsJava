package com.hrms.request.bean;

import lombok.Data;

@Data
public class WorkFlowUpdateReqBean {

	private String empid;
	
	private String reportingManagerId;
	
	private int reqId;
	
	private String feature;
}
