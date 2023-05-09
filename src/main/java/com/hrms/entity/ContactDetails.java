package com.hrms.entity;

import javax.persistence.CascadeType;
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
@Table(name="Contact_Details")
public class ContactDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne(cascade=CascadeType.ALL )
	@JoinColumn(name="emp_id",referencedColumnName = "emp_id")
	private EmployeeDetails employeedetails;
	
	private String status1;
	
	private String country1;
	
	private String state1;
	
	private String street1;
	
	private String effectiveDate1;
	
	private String pincode1;
	
	private String city1;
	
	private String status2;
	
	private String country2;
	
	private String state2;
	
	private String street2;
	
	private String effectiveDate2;
	
	private String pincode2;
	
	private String city2;

	
		
	}

