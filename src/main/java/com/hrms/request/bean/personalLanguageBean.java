package com.hrms.request.bean;

import java.util.Date;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class personalLanguageBean {
	
	private int id;
	private String languageName;
	private String description;
	private Date createdDate;
	private Date modifiedDate;
	private int isActive;

}
