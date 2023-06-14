package com.hrms.request.bean;



import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Data;

@Data
@Component
public class LeaveRequestBean {
	private String reason;
	private String fromDate;
	private String toDate;
	//private int days;
//	private float avilableLeaves;	
}
