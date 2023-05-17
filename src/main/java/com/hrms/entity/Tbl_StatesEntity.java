package com.hrms.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "tbl_states")
public class Tbl_StatesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "country_id")
	private int countryId;
	
	@Column(name = "state_name")
	private String stateName;
	
	@Column(name = "state_code")
	private String stateCode;
	
	@Column(name = "isactive")
	private int isactive;
	
	@Column(name = "created")
	private Date createdDate;
	
	@Column(name = "modified")
	private Date modifiedDate;


}
