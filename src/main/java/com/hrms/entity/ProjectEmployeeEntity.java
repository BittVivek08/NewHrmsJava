package com.hrms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="tm_project_employees")
public class ProjectEmployeeEntity {
    
	@Id
	@GeneratedValue
	private int id;
	
	
	//@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="projectId",referencedColumnName = "id")
	private ProjectDetailsEntity project;
	
	//@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="Emp_Id",referencedColumnName = "emp_id")
	private EmployeeDetails employee;
	
	private LocalDateTime created;
	
	private LocalDateTime modified;
	
	@Column(name="start_date")
	private LocalDate startdate;
	
	@Column(name="end_date")
	private LocalDate endDate;
	
	@Column(name="created_by")
	private String createdby;
	
	@Column(name="modified_by")
	private String modifiedby;
	
	private int billable;
	
	
	
	
}
