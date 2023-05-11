package com.hrms.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@Table(name = "tm_emp_timesheets")
public class SaveTimeSheet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "status")
	private String status;
	@Column(name="cal_week")
	private  Integer calweek;

	@Column(name = "ts_year")
	private Integer year;
	@Column(name = "ts_month")
    private Integer month;
	private  String customer;
	private String project;
	@Transient
	private String clientName;
    private String taskName;
    @Column(name = "ts_week")
	private Integer weekNo;
    @Column(name = "mon_duration")
	private String mon_hours;

	@Column(name = "mon_date")
	@Temporal(TemporalType.DATE)
	private Date mon_date;

	@Column(name = "tue_duration")
	private String tue_hours;

	@Column(name = "tue_date")
	@Temporal(TemporalType.DATE)
	private Date tue_date;

	@Column(name = "wed_duration")
	private String wed_hours;

	@Column(name = "wed_date")
	@Temporal(TemporalType.DATE)
	private Date wed_Date;

	@Column(name = "thu_duration")
	private String thurs_hours;

	@Column(name = "thu_date")
	@Temporal(TemporalType.DATE)
	private Date thurs_Date;

	@Column(name = "fri_duration")
	private String fri_hours;

	@Column(name = "fri_date")
	@Temporal(TemporalType.DATE)
	private Date fri_Date;

	@Column(name = "sat_duration")
	private String sat_hours;

	@Column(name = "sat_date")
	@Temporal(TemporalType.DATE)
	private Date sat_Date;

	@Column(name = "sun_duration")
	private String sun_hours;

	@Column(name = "sun_date")
	@Temporal(TemporalType.DATE)
	private Date sun_Date;

	@Column(name = "week_duration")
	private String TotalWeekHours;
	
	@Column(name = "created", updatable = false)
	private LocalDateTime created_Date;
	
	@Column(name = "modified")
	private LocalDateTime modifiedDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "emp_id",referencedColumnName = "emp_id")
	private EmployeeDetails emp;
	
	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name = "client_id",referencedColumnName = "client_id")
	private ClientDetailsEntity client;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	private ProjectDetailsEntity proj;
	
	@ManyToOne
	@JoinColumn(name = "project_task_id")
	private TaskDetailsEntity task;
    
	
	@Column(name = "is_active")
	private Boolean isActive;
	@Transient
	private String request;

	


}
