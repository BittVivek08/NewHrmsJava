package com.hrms.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class WorkFlow {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String emp_id;
	private int Req_id;
	private String feature;
	private String statu;
	private String reportingManagerId;
	private Instant createdDate;
	private Instant modifiedDate;
	private String createdBy;
	private String modifiedBy;
	
}
