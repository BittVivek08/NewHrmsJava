package com.hrms.request.bean;

import lombok.Data;

@Data
public class LeavesResponseBean {

	
	private String message;
	private boolean status;
	private int countPending;
	private int countAll;
	private int countApproved;
	private int countRejected;
	private int countCancel;
	private Object listOfLeaves;
	private int ablLeave;
}
