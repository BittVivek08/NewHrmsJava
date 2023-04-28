package com.hrms.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="Department_Entity")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="Business_Id")
	private int businessId;
	
	@Column(name="Department_Name")
	private String depName;
	
	@Column(name="Department_Code")
	private String dipCode;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="StartDate")
	private String startDate;
	
	@Column(name="Country")
	private String country;
	
	@Column(name="State")
	private String state;
	
	@Column(name="City")
	private String city;
	
	@Column(name="Department_Head")
	private String depHead;
	
	@Column(name="BusinessUnit_Name")
	private String businessunitName;
	
}
