package com.hrms.request.bean;

import java.util.Date;


import lombok.Data;

@Data
public class EmployeeSalaryRequestBean {

	private Integer currencyId;

	private String salary;

	private String bankName;

	private String accountHolderName;

	private Date accountHoldingSince;

	private Integer accountClassTypeId;

	private Date effectiveStartDate;

	private Date effectiveEndDate;

	private String accountNumber;

	private String ifscCode;

	private Date createdDate;

	private Date modifiedDate;

	private Integer isActive;

	private String empId;
}
