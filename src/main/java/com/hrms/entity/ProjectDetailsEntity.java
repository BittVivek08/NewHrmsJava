package com.hrms.entity;


import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class ProjectDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projectid")
	private int projectId;

	@Column(name = "project_name")
	private String projectName;
	
	@Column(name="Project_status")
	private String projectstatus;
	
	@Column(name="project_description")
	private String description;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="client_id", referencedColumnName = "client_id")
	private ClientDetailsEntity client;
	
	@Column(name="currency_name")
	private String currencyname;
	
	private String projecttype;
	
	@Column(name="lead_approve_status")
	private Boolean leadapprove;
	
	@Column(name="estimated_hours")
	private int estimatedhours;
	
	@Column(name="start_date")
	private Date startdate;
	
	@Column(name="end_date")
	private Date enddate;
	
	@Column(name="is_active")
	private Boolean isactive;
	
	
	
	
	
	
	
	
	

}
