package com.hrms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class EmployeeInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne
	@JoinColumn(name = "emp_id",referencedColumnName = "emp_id") 
	private EmployeeDetails employeeDetails;

	private String birthCountry;

	private String language;

	private String maritalStatus;

	private String citizenship;

	private String country;

	private String maritalEffectiveDate;

	private String citizenshipEffectiveDate;

	private String nationalIdType;

	private String nationalId;

}
