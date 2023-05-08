package com.hrms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
@Data
@Entity
public class ContactDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="emp_Id",referencedColumnName ="empId" )
	private EmployeeDetails employeedetails;
	

	private String status1;
	
	private String country1;
	
	
	private String street1;
	
	
	private String sate1;
	
	
	private String city1;
	
	private String province1;
	
	@Column(name="PinCode1")
	private String pincode1;
	
	@Column(name="Effective_Date1")
	private String effectivedate1;

	private String status2;
		
	private String country2;
	
	
	private String street2;
	
	private String sate2;
	

	private String city2;
	
	
	private String province2;
	
	@Column(name="PinCode2")
	private String pincode2;
	
	@Column(name="Effective_Date2")
	private String effectivedate2;

}
