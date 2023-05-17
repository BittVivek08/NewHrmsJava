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
@Table(name = "main_leavemanagement_summary")
public class LeaveManagementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "leavemgmt_id")
	private int leaveMgmtId;

	@Column(name = "cal_startmonth")
	private int startMonth;

	@Column(name = "cal_startmonthname")
	private String startMonthName;

	@Column(name = "weekend_startday")
	private int satrtDay;

	@Column(name = "weekend_startdayname")
	private String startDayName;

	@Column(name = "weekend_endday")
	private int endDay;

	@Column(name = "weekend_enddayname")
	private String endDayName;

	@Column(name = "businessunit_name")
	private String businessUnit;

	@Column(name = "department_name")
	private String departmentName;

	@Column(name = "hours_day")
	private int workingHours;

	@Column(name = "is_satholiday")
	private String satHoliday;

	@Column(name = "is_halfday")
	private String isHalfDay;

	@Column(name = "is_leavetransfer")
	private String isLeaveTransfer;

	@Column(name = "is_skipholidays")
	private String isSkipHolidays;

	@Column(name = "description")
	private String description;

	@Column(name = "isactive")
	private int isActive;

	@Column(name = "createdby")
	private String createdBy;
	
	@Column(name = "modifiedby")
	private String modifiedBy;

	@Column(name = "createddate")
	private Timestamp createdDate;

	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

	
}
