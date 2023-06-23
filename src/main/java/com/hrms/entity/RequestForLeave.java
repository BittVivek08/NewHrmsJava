package com.hrms.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="req_leave_Details")
public class RequestForLeave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="orgId",referencedColumnName = "orgId")
	private OrganizationInfoEntity orgInfoEntity;
	
	private String leaveType;
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer totalLeaveDays;
	private Integer avalCasualLeave;
	private Integer avalSickLeave;
	private String reason;
	private String status;
	private String emp_id;
	

}
