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
@Table(name = "main_leaverequest_summary")
public class LeaveRequestEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int Id;

	@Column(name = "leave_req_id")
	private int leaveReqId;

	private String  emp_id;
	
	private int user_id;
	
	@Column(name = "user_name")
	private String userName;

	@Column(name = "reason")
	private String reason;

	@Column(name = "leavestatus")
	private String leaveStatus;

	@Column(name = "leavetypeid")
	private int leaveTypeId;

	@Column(name = "leavetype_name")
	private String leaveType;
 
	@Column(name = "from_date")
	private String fromDate;

	@Column(name = "to_date")
	private String toDate;

	@Column(name = "no_of_days")
	private int days;

	@Column(name = "createddate")
	private String appliedOn;

	@Column(name = "rep_mang_id")
	private String reportingManagerId;

	@Column(name = "rep_manager_name")
	private String reportingManager;

	@Column(name = "appliedleavescount")
	private Double appliedLeavesCount;

	@Column(name = "isactive")
	private int isActive;

	@Column(name = "createdby")
	private Integer createdBy;

	@Column(name = "modifiedby")
	private Integer modifiedBy;

	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

	
}
