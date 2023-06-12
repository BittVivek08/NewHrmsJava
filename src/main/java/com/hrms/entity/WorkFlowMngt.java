package com.hrms.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class WorkFlowMngt {

	@Id
	private int id;
	private String feature;
	private String type;
	private int managerLevel;
	private Instant creaDateTime;
	private Instant modifiedDateTime;
}
