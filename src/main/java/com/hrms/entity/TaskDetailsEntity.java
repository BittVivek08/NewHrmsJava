package com.hrms.entity;

//my changes
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "tm_tasks")
public class TaskDetailsEntity  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int taskid;
    
	@Column(name = "task")
	private String tasknmae;
	
	@Column(name = "is_default")
	private Boolean is_default;
	
	@Column(name = "created_by", updatable = false)
	private String created_by;
	
	@Column(name = "modified_by")
	private String modified_by;

	private int is_active;

	@Column(name = "created")
	private LocalDateTime createddate;

	@Column(name = "modified")
	private LocalDateTime modifieddate;
	
}
