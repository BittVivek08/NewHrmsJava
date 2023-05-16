package com.hrms.request.bean;

import java.util.Date;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class PersonalMaritalStatusBean {
	
	private int id;
	private String maritalCode;
	private String maritalStatusName;
	private Date createdDate;
	private Date modifiedDate;
	private int isActive;


}
