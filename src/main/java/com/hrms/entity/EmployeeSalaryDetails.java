package com.hrms.entity;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
@Table(name = "main_empsalarydetails")
public class EmployeeSalaryDetails implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String empName;
	
	private String currency;
	
	private String salary;
	
	private String bankName;
	
	private String accountHolderName;

	private String accountType;

	private String accountNumber;
	
	private String ifscCode;
	
	private Integer isActive;

	
	 @OneToOne
	  @JoinColumn(name = "emp_id",referencedColumnName = "emp_id") 
	  private EmployeeDetails employeeDetails;
	 
}
