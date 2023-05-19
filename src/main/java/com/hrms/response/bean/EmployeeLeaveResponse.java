package com.hrms.response.bean;

import java.math.BigInteger;
import java.util.Date;
public class EmployeeLeaveResponse {
	private String employeeName;
	private BigInteger userId;
	private String employeeId;
	private Date leaveFromDate;
	private Date leaveToDate;
	private String reason;
	private String reportingManager;
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Date getLeaveFromDate() {
		return leaveFromDate;
	}
	public void setLeaveFromDate(Date leaveFromDate) {
		this.leaveFromDate = leaveFromDate;
	}
	public Date getLeaveToDate() {
		return leaveToDate;
	}
	public void setLeaveToDate(Date leaveToDate) {
		this.leaveToDate = leaveToDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getReportingManager() {
		return reportingManager;
	}
	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}
	
	

}
