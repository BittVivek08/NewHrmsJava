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
	private Integer id;
	private String empid;
	private int reqid;
	private String feature;
	private String status;
	private String approvalManagerId;
	private Instant createdDate;
	private Instant modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private String approverComment;
	
}







