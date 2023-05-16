package com.hrms.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tbl_countries")
@Data
public class Tbl_CountriesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "country_name")
	private String countryName;
	
	@Column(name = "country_code")
	private String countryCode;
	 
	@Column(name = "country_code2")
	private String countryCode2;
	

	@Column(name = "is_active")
	private int isActive;
	

	@Column(name = "created")
	private Date createdDate;
	
	
	@Column(name = "modified")
	private Date modifieddate;

}
