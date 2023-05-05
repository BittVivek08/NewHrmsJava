package com.hrms.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name ="employee_attendance")
public class EmployeeAttendance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "emp_id")
	private String empId;
	
	@Column(name = "ip_Address")
	private String ipAddress;
	
	private String workFrom;

	@Column(name = "in_time")
	private LocalTime checkInTime;

	@Column(name = "out_time")
	private LocalTime checkOutTime;

	@Column(name = "date")
	private LocalDate date;
	
	private String status;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "date2" , referencedColumnName = "date")
	private HolidayCalenderEntity calendarEntity;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	private EmployeeDetails employee;

}
