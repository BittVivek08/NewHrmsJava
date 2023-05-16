package com.hrms.request.bean;

import java.util.Date;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class PersonalGenderBean {
	
	private int id;
	private String genderCode;
	private String genderName;
    private Date createdDate;
    private Date modifiedDate;
    private int isActive;
}
