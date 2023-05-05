package com.hrms.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@Entity
@Table(name = "Company_Announcement")
@Component
public class CompanyAnnouncement {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int announid;

	@Column(name = "Announ_Subject", length = 100)
	private String subject;

	@Column(name = "Announ_description", length = 2000)
	private String description;
	
	@Column(name="announ_startdate")
	private String startdate;
	
	@Column(name="announ_enddate")
	private String enddate;
	


	
}
