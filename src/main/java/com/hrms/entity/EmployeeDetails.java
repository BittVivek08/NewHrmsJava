package com.hrms.entity;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "main_employees")
public class EmployeeDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name="emp_id", unique = true)
	private String empId;

	private String firstName;

	private String lastName;

	@Column(name = "dob")
	private String dateOfBirth;
	
	@Column(name = "emp_role_id")
	private int empRoleId;

	private String empRole;
	
	private String designation;
	
	private String reportingManagerName;

	private String qualification;

	private long mobileNumber;

	private String email;

	private String password;

	private String gender;

	private String image;
	
	/*
	 * private String createdOn;
	 * 
	 * private String lastUpdatedOn;
	 */
	
}



