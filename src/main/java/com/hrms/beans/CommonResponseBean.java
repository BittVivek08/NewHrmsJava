package com.hrms.beans;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CommonResponseBean {
	
	private String msg;
	
	private boolean status;
	
	private Object list;
	
	private Object privillegesList;
	
	

}
