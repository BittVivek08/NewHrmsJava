package com.hrms.beans;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Contactbean {
	
	private String message;
	
	private Boolean Status;

}
