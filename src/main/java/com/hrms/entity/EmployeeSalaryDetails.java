package com.hrms.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "main_empsalarydetails")
public class EmployeeSalaryDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "currencyid")
	private Integer currencyId;

	@Column(name = "salarytype")
	private Integer salaryTypeId;

	@Column(name = "salary")
	private String salary;

	@Column(name = "bankname")
	private String bankName;

	@Column(name = "accountholder_name")
	private String accountHolderName; 

	@Column(name = "accountholding")
	private Date accountHoldingSince;

	@Column(name = "accountclasstypeid")
	private Integer accountClassTypeId;

	@Column(name = "bankaccountid")
	private Integer bankAccountId;

	@Column(name = "accountnumber")
	private String accountNumber;

	@Column(name = "createdby", updatable = false)
	private Integer createdBy;

	@Column(name = "modifiedby")
	private Integer modifiedBy;

	@Column(name = "createddate", updatable = false)
	private Timestamp createdDate;

	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

	@Column(name = "isactive")
	private Integer isActive;

}
