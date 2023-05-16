package com.hrms.request.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class PersonalNationalityBean {
	
	private int id;
	private String nationalityCode;
	private Date createdDate;
	private int createdBy;
	private Date modifiedDate;
	private int isActive;
}
