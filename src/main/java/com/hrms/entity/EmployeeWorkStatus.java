package com.hrms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_work_status_entity")
public class EmployeeWorkStatus implements Serializable{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Column(name="emp_id")
	private int empId;
	@Column(name = "status")
	private String status;
	@Column(name="cal_week")
	private short calweek;
	@Column(name="rep_id")
	private Integer repId;
	@Column(name = "ts_year")
	private int year;
	@Column(name = "ts_month")
	private Short month;
}