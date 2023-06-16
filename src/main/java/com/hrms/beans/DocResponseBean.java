package com.hrms.beans;


import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class DocResponseBean {
	
	private String message;
	private Object listOfDocuments;
	private boolean status;

}
