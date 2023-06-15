package com.hrms.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class WorkFlowMngt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String feature;
	private String type;
	private int managerLevel;
	private Instant createdDate;
	private Instant modifiedDate;
	private String createdby;
	private String modifiedby;
}
