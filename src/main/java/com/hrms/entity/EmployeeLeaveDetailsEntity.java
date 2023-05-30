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
@Table(name = "main_employeeleaves")
public class EmployeeLeaveDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "emp_leave_limit")
	private float noOfDays;

	@Column(name = "alloted_year")
	private int year;
     
	@Column(name="emp_id")
	private String  empId;
	
	@Column(name = "createddate")
	private Timestamp createdDate;

	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

	@Column(name = "used_casual_leaves")
	private float usedCasualLeaves;

	@Column(name = "used_sick_leaves")
	private float usedSickLeaves;

}
