package com.hrms.response.bean;

import java.sql.Timestamp;

public class LeaveDetailResponse {

	private String canName;
	private String leaveType;
	private String reason;
	private Object toDate;
	private Object fromDate;
	private Timestamp applyDate;
	private Number days;
	private double abailLeave;
	

	public double getAbailLeave() {
		return abailLeave;
	}

	public void setAbailLeave(double abailLeave) {
		this.abailLeave = abailLeave;
	}

	private String status;
	private String approvelName;
	private String approvelPosName;
	private int idOfApprovel;

	public int getIdOfApprovel() {
		return idOfApprovel;
	}

	public void setIdOfApprovel(int idOfApprovel) {
		this.idOfApprovel = idOfApprovel;
	}

	public String getApprovelName() {
		return approvelName;
	}

	public void setApprovelName(String approvelName) {
		this.approvelName = approvelName;
	}

	public String getApprovelPosName() {
		return approvelPosName;
	}

	public void setApprovelPosName(String approvelPosName) {
		this.approvelPosName = approvelPosName;
	}

	public Timestamp getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Timestamp applyDate) {
		this.applyDate = applyDate;
	}

	public Number getDays() {
		return days;
	}

	public void setDays(Number days) {
		this.days = days;
	}

	

	public String getCanName() {
		return canName;
	}

	public void setCanName(String canName) {
		this.canName = canName;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Object getToDate() {
		return toDate;
	}

	public void setToDate(Object toDate) {
		this.toDate = toDate;
	}

	public Object getFromDate() {
		return fromDate;
	}

	public void setFromDate(Object fromDate) {
		this.fromDate = fromDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
