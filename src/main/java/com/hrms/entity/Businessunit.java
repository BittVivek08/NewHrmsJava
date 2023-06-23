package com.hrms.entity;

import java.io.Serializable;
import java.util.Date;

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

@Entity
@Data
@Table(name = "business_unit")
public class Businessunit implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int bid;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="orgId",referencedColumnName = "orgId")
	private OrganizationInfoEntity orgInfoEntity;

	private String name;

	private String code;

	private String description;

	private String startdate;

	private String country;

	private String state;

	private String city;

	private String address1;

	private String address2;

	private String createdby;
	
	
	@Column(name = "modifiedby")
	private String modifiedBy;


	@Column(name = "modifieddate")
	private Date modifiedDate;
	
	
	private int isactive;
	
	private Date createddate;

	private Integer timezone;

}
