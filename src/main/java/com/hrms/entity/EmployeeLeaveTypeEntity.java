package com.hrms.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data   
@Entity
@Table(name = "main_employeeleavetypes")
public class EmployeeLeaveTypeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int leaveTypeId;

	@Column(name = "leavetype")
	private String leaveType;

	@Column(name = "numberofdays")
	private float noOfDays;

	@Column(name = "year")
	private int year;

	@Column(name = "isactive")
	private byte isActive;

	@Column(name = "createddate")
	private Timestamp createdDate;

	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

	private String createdBy;

	private String modifiedBy;
	
	@Column(name = "noofdaysmonth")
	private int noOfDaysMonth;

}
