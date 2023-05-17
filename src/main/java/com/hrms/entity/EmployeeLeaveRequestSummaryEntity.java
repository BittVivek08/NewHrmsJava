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
@Table(name = "main_leaverequest_summary")
public class EmployeeLeaveRequestSummaryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "leave_req_id")
	private int leaveRequestId;

	private String  emp_id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "department_id")
	private int departmentId;

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

	@Column(name = "leavetypeid")
	private int leaveTypeId;

	@Column(name = "leavetype_name")
	private String leaveTypeName;

	@Column(name = "leaveday")
	private int leaveDay;

	@Column(name = "from_date")
	private Date fromDate;

	@Column(name = "to_date")
	private Date toDate;

	/*
	 * @Column(name="") public enum
	 * leaveStatus{pendingForApproval,Approved,Rejected,Cancel;};
	 */
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

	@Column(name = "appliedleavescount")
	private float appliedLeavesCount;

	@Column(name = "is_sat_holiday")
	private int isSatHoliday;

	@Column(name = "modifieddate")
	private Timestamp modifieddate;

	@Column(name = "modifiedby")
	private Integer modifiedBy;

	@Column(name = "createdby")
	private Integer createdBy;
}
