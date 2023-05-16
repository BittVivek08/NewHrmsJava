package com.hrms.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "main_empsalarydetails")
public class EmployeeSalaryDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@OneToOne(cascade=CascadeType.ALL )
	@JoinColumn(name="emp_id",referencedColumnName = "emp_id")
	private EmployeeDetails employeeDetails;

	@Column(name = "currencyid")
	private Integer currencyId;

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

	@Column(name = "accountnumber")
	private String accountNumber;
	
	@Column(name = "ifsccode")
	private String ifscCode;
	
	@Column(name = "effective_start_date")
	private Date effectiveStartDate;
	
	@Column(name = "effective_end_date")
	private Date effectiveEndDate;

	@Column(name = "createddate")
	private Date createdDate;

	@Column(name = "modifieddate")
	private Date modifiedDate;

	@Column(name = "isactive")
	private Integer isActive;

}
