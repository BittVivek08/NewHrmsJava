package com.hrms.entity;

import java.io.Serializable;

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


}
