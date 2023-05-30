package com.hrms.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
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
@Table(name = "main_leaverequest_summary")
public class EmployeeLeaveRequestSummaryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	private String  emp_id;
	
	private int user_id;

	@Column(name = "department_id")
	private int departmentId;

	@Column (name = "leave_status")
	private String leaveStatus;
	
	@Column(name = "department_name")
	private String departmentName;

	@Column(name = "bunit_id")
	private int businessUnitId;

	@Column(name = "buss_unit_name")
	private String businessUnitName;

	@Column(name = "reason")
	private String reason;

	@Column(name = "approver_comments")
	private String approverComments;


	@Column(name = "leavetype_name")
	private String leaveType;

	@Column(name = "from_date")
	private LocalDate fromDate;

	@Column(name = "to_date")
	private LocalDate toDate;

	@Column(name = "rep_mang_id")
	private int reportingManagerId;

	@Column(name = "rep_manager_name")
	private String reportingManagerName;

	@Column(name = "hr_id")
	private int hrId;

	@Column(name = "hr_name")
	private String hrName;

	@Column(name = "no_of_days")
	private float noOfDays;
	
	private Timestamp createddate; 

	@Column(name = "modifieddate")
	private Timestamp modifieddate;

	@Column(name = "modifiedby")
	private Integer modifiedBy;

	@Column(name = "createdby")
	private Integer createdBy;
}
