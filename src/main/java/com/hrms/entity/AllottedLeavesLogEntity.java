package com.hrms.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "main_allottedleaveslog")
public class AllottedLeavesLogEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String  emp_id;

	@Column(name = "assignedleaves")
	private int assignedLeaves;

	@Column(name = "totalleaves")
	private String totalLeaves;

	@Column(name = "year")
	private String year;

	@Column(name = "createdby")
	private Integer createdBy;

	@Column(name = "modifiedby")
	private Integer modifiedBy;

	@Column(name = "createddate")
	private Date createdDate;

	@Column(name = "modifieddate")
	private Timestamp modifieddate;

	@Column(name = "isactive")
	private int isActive;

}
