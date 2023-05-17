package com.hrms.entity;

import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table(name = "main_empskill")
@Data
public class SkillEnitity {
	

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private int id;
		
		@OneToOne
		@JoinColumn(name = "emp_id",referencedColumnName = "emp_id") 
		private EmployeeDetails employeeDetails;
		
         @Column(name = "skillname")
		private String skillName;

		@Column(name = "yearsofexp")
		private int yearsOfExp;

		@Column(name = "competencylevelid")
		private int competencyLevelId;

		@Column(name = "year_skill_last_used")
		private Date yearSkillLastUsed;

		@Column(name = "isactive", columnDefinition = "TINYINT default 1")
		private int isActive;

		@Column(name = "createddate")
		private Date createdDate;

		@Column(name = "modifieddate")
		private Date modifiedDate;

		@ManyToOne
		@JoinColumn(name = "state_id")
		private Tbl_StatesEntity state;

		@ManyToOne
		@JoinColumn(name = "country_id")
		private Tbl_CountriesEntity country;

		@ManyToOne
		@JoinColumn(name = "city_id")
		private Tbl_CitiesEntity city;
		
		@Column(name = "course_name")
		private String courseName;

		@Column(name = "description")
		private String description;

		@Column(name = "course_level")
		private String courselevel;

		@Column(name = "courseOfferedby")
		private String courseOfferedBy;

		@Column(name = "certification_name")
		private String certificationName;

		@Column(name = "issue_date")
		private Date issuedDate;



}
