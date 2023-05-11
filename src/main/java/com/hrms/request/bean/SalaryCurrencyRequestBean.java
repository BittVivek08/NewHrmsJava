package com.hrms.request.bean;


import java.util.Date;

import lombok.Data;

@Data
public class SalaryCurrencyRequestBean {
	private String currencyName;
	private String currencyCode;
	private String discription;
	private int isActive;
	private Date createdDate;
	private Date modifiedDate;

}
