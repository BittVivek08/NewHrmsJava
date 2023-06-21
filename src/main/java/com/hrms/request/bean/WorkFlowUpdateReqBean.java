package com.hrms.request.bean;

import lombok.Data;

@Data
public class WorkFlowUpdateReqBean {

	private String empid;
	
	private String ApprovalManagerId;
	
	private int reqId;
	
	private String feature;
	
	private String comment;
	
	private String status;
	
	
}
