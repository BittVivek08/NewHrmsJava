package com.hrms.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "main_holidaygroups")
public class HolidaysEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "groupname")
	private String groupName;

	@Column(name = "description")
	private String description;

	@Column(name = "createdby")
	private int userId;
		
	@Column(name = "isactive")
	private byte isActive;

	@Column(name = "createddate")
	private Timestamp createdDate;

	@Column(name = "modifiedby")
	private Timestamp modifiedby;
	
	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

}
