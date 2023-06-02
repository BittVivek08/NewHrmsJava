package com.hrms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = " TimeSheet_ApprovalStatusEntity ")
public class TimeSheetApprovalStatusEntity 
{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="status_id")
	private int statusId;
	
	@Column(name="timesheet_id")
	private int timeSheetId;

	@Column(name="reject_reason")
	private String rejectReason;

	@Column(name="approver_role")
	private String approverRole;
	
	@Column(name="approver_name")
	private String approverName;
}