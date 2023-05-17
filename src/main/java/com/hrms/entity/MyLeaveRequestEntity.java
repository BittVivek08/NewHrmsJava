package com.hrms.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "main_leaverequest")
public class MyLeaveRequestEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "leavetypeid")
	private Integer leaveTypeId;

	@Column(name = "leaveType")
	private String leaveType;

	@Column(name = "reason")
	private String reason;

	@Column(name = "from_date")
	private Date fromDate;

	@Column(name = "to_date")
	private Date toDate;

	@Column(name = "leaveday")
	private String leaveFor;

	@Column(name = "no_of_days")
	private float days;

	@Column(name = "leavestatus")
	private String leaveStatus;

	@Column(name = "rep_mang_id")
	private Integer reportingManagerId;

	@Column(name = "reportingManager")
	private String reportingManager;

	@Column(name = "hr_id")
	private Integer hrId;

	@Column(name = "availableLeaves")/* ,nullable=false,insertable=true,updatable=true */
	private Double availableLeaves;

	/*
	 * @Column(name = "isactive") private byte isActive;
	 */

	@Column(name = "isactive")
	private int isactive;

	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

	@Column(name = "createddate")
	private Timestamp createdDdate;

	@Column(name = "modifiedby")
	private Integer modifiedBy;

	@Column(name = "createdby")
	private Integer createdBy;

	@Column(name = "appliedleavescount")
	private Integer appliedLeavescount;

	@Column(name = "email_address")
	private String email;

	@Column(name = "name")
	private String name;
}