package com.hrms.entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "main_leaverequest")
public class MyLeaveRequestEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	
	private String emp_id;

	@Column(name = "leaveType")
	private String leaveType;

	@Column(name = "reason")
	private String reason;

	@Column(name = "from_date")
	private LocalDate fromDate;

	@Column(name = "to_date")
	private LocalDate toDate;

	@Column(name = "no_of_days")
	private float days;

	@Column(name = "leavestatus")
	private String leaveStatus;

	@Column(name = "rep_mang_id")
	private String reportingManagerId;

	@Column(name = "reportingManager")
	private String reportingManager;

	@Column(name = "hr_id")
	private String hrId;

	@Column(name = "isactive")
	private int isactive;

	@Column(name = "modifieddate")
	private Instant modifiedDate;

	@Column(name = "createddate")
	private Instant createdDdate;

	@Column(name = "modifiedby")
	private int modifiedBy;

	@Column(name = "createdby")
	private Integer createdBy;

	@Column(name = "appliedleavescount")
	private Integer appliedLeavescount;

	@Column(name = "email_address")
	private String email;

	@Column(name = "name")
	private String name;
	
	private float availabelDays;
}







































