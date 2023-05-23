package com.hrms.request.bean;

import java.sql.Timestamp;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class JobTitleBean {
	
	private int id;
	private String jobTitleCode;
	private String jobTitleName;
	private String jobDescription;
	private String minExpReq;
	private String comments;
	private String jobPayGradeCode;
	private String jobPayFrequency;
	private int createdOrModifiedBy;
	private Timestamp createdDate;
	private Timestamp modifiedDate;
	private int isActive;


}
