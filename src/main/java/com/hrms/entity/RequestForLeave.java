package com.hrms.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name="Leave_Apply_Details")
public class RequestForLeave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int mappingId;
	private String leaveType;
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer totalLeaveDays;
	private Integer avalCasualLeave;
	private Integer avalSickLeave;
	private String reason;
	private String status;
	
	@ManyToOne
	@JoinColumn(name="emp_id", nullable = false)
	private EmployeeDetails details;
}
