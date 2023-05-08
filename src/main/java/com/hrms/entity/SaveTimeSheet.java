package com.hrms.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class SaveTimeSheet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer empId;
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
	
	@Column(name = "project_task_id", updatable = false)
	private int projectTaskId;
	
	@Column(name="client_id")
	private int clientId;
	@Transient
	private String clientName;

	@Column(name = "project_id")
	private int projectId;
    private String task;
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
	
	

}
