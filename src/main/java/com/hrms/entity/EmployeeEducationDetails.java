 package com.hrms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
@Table(name="emp_education_Details")
public class EmployeeEducationDetails implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	@OneToOne
	@JoinColumn(name = "emp_id",referencedColumnName = "emp_id") 
	private EmployeeDetails employeeDetails;

	@Column(name = "master_degree")
	private String masterDegree;
	
	private double percentageofmasterDegree;

	@Column(name = "bachlors_degree")
	private String bachlorsDegree;
	
	private double percentageofbachlorsDegree;

	@Column(name = "diploma_Hssc")
	private String diploma_HsscDegree;
	
	private double percentageofdiploma_HsscDegree;

	@Column(name = "ssc")
	private String sscDegree;
	
	private double percentageofsscDegree;

	@Column(name = "other")
	private String other;
	
	private double percentageofother;

	@Column(name = "masterDegreeInstituteName")
	private String masterDegreeInstituteName;

	@Column(name = "bachlorsDegreeInstituteName")
	private String bachlorsDegreeInstituteName;

	@Column(name = "diplomaHsscDegreeName")
	private String diploma_HsscDegreeInstituteName;

	@Column(name = "sscDegreeName")
	private String sscDegreeInstituteName;

	@Column(name = "otherDegreeName")
	private String otherDegreeInstituteName;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "educationlevel")
	private int educationLevel;

	@Column(name = "institution_name")
	private String institutionName;

	@Column(name = "course")
	private String course;

	@Column(name = "from_date")
	private java.sql.Date fromDate;

	@Column(name = "to_date")
	private java.sql.Date toDate;

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
	
	
	@Column(name = "modifiedby")
	private int modifiedBy;
	
	@Column(name = "createdby")
	private int createdBy;


	
	


}
