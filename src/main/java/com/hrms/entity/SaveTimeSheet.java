package com.hrms.entity;


import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import javax.persistence.Table;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
	private EmployeeDetails emp;

	@ManyToOne(fetch = FetchType.LAZY)
	private ClientDetailsEntity client;

	@Column(name = "work_duration")
	private String workHours;

	@Column(name = "work_date")
	private Date workDate;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private ProjectDetailsEntity proj;

	@ManyToOne
	@JoinColumn(name = "project_task_id")
	private TaskDetailsEntity task;

	@Column(name = "status")
	private String status;

	@Column(name = "created_by", updatable = false)
	private String createdBy;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "created_date", updatable = false)
	private Date createdDate;

	@Column(name = "modified")
	private Date modifiedDate;
	
	private String comments;

	@Transient
	private String request;

}
