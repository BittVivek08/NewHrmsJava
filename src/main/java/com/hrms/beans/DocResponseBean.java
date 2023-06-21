package com.hrms.beans;


import java.util.List;

import org.springframework.stereotype.Component;

import com.hrms.entity.EmployeeDocumentsEntity;

import lombok.Data;

@Data
@Component
public class DocResponseBean {
	
	private String message;
	private List<String> listOfDocuments;
	private boolean status;

}
