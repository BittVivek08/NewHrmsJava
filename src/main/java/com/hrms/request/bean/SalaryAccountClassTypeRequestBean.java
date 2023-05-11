package com.hrms.request.bean;

import java.util.Date;

import lombok.Data;

@Data
public class SalaryAccountClassTypeRequestBean {

	private String accountClassType;

	private String discription;

	private int isActive;

	private Date createdDate;

	private Date modifiedDate;

}
