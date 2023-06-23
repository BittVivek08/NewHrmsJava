package com.hrms.entity;


import java.util.BitSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Data;
@Data
@Entity
@Table(name = "annoncement_details")
public class CompanyAnnouncement {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int announid;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="orgId",referencedColumnName = "orgId")
	private OrganizationInfoEntity orgInfoEntity;

	@Column(name = "Announ_Subject", length = 100)
	private String subject;

	@Column(name = "description", length = 2000)
	private String description;
	
	@Column(name="announ_startdate")
	private String startdate;
	
	@Column(name="announ_enddate")
	private String enddate;
	
	@Column(name="announcement_time")
    private String announcementtime;
	
	@Column(name="announcement_name")
    private String announcementname;
	
	@Column(name="business_unit_id")
    private int businessunitid;
	
	@Column(name="department_id")
    private int departmentid;
	
	@Column(name="employee_user_id")
    private String employeeuserid;
	
	
    private BitSet deleted;
    

	
}
