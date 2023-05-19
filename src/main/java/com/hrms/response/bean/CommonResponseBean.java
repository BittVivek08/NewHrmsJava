package com.hrms.response.bean;

import lombok.Data;

@Data
public class CommonResponseBean {
	private String message;
	private boolean status;
	private Object list;
	private Object privilleges;
	
	
}
