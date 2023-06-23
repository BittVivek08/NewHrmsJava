package com.hrms.entity;



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
@Table(name="department_details")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne
	@JoinColumn(name="bid",referencedColumnName = "bid")
	private Businessunit businessunit;
	
	@Column(name="deptname")
	private String depName;
	
	@Column(name="deptcode")
	private String dipCode;
	
	@Column(name="description")
	private String description;
	
	@Column(name="startdate")
	private String startDate;
	
	@Column(name="country")
	private String country;
	
	@Column(name="state")
	private String state;
	
	@Column(name="city")
	private String city;
	
	@Column(name="depthead")
	private String depHead;
	
	@Column(name="businessunit_name")
	private String businessunitName;
	
	private String address1;
	
	private String address2;
	
	private String address3;
	
	private int createdby;
	
	private int modifiedby;
	
	private int timezone;
	
	private int unitid;
}
