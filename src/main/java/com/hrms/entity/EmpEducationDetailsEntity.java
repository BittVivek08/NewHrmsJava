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

@Entity
@Table(name="main_empeducationdetails")
@Data
public class EmpEducationDetailsEntity {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@OneToOne(cascade=CascadeType.ALL )
	@JoinColumn(name="emp_id",referencedColumnName = "emp_id")
	private EmployeeDetails employeedetails;

	@Column(name = "educationlevel")
	private int educationLevel;

	@Column(name = "institution_name")
	private String institutionName;

	@Column(name = "course")
	private String course;

	@Column(name = "from_date")
	private Date fromDate;

	@Column(name = "to_date")
	private Date toDate;

	@Column(name = "percentage")
	private double percentage;

	
	@Column(name = "createddate")
	private Date createdDate;

	
	@Column(name = "modifieddate")
	private Date modifiedDate;

	
	@Column(name = "isactive")
	private int isactive;

	@Column(name = "educationLevelName")
	private String educationLevelName;

}
