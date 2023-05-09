package com.hrms.beans;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ContactBean {
	private String message;
	
	private Boolean status;

}
