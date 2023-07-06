package com.hrms.entity;

import java.sql.Timestamp;
import java.time.Month;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class AssignLeaveManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String emp_id;
	
	@Column(name = "leavetype")
	private String leaveType;

	@Column(name = "totalNum")
	private float noOfDays;

	@Column(name = "year")
	private int year;

	@Column(name = "isactive")
	private int isActive;

	@Column(name = "createddate")
	private Timestamp createdDate;

	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

	private String createdBy;

	private String modifiedBy;
	
	private int monthNumber;
	
	@Column(name = "noofdaysmonth")
	private int noOfDaysMonth;
	
	
}
