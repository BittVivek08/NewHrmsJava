package com.hrms.beans;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MailStatusResponse {
	
	private boolean status;
	
	private String message;
	
	
	

}
