package com.hrms.entity;

import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "main_positions")
public class PositionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "positionname")
	private String positionName;

	// @Column(name = "jobtitleid")
	// private Integer jobTitleId;

	// @Column(name = "jobtitleid")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "jobtitleid", referencedColumnName = "id")
	private JobTitlesEntity jobTitleId;

	@JsonIgnore
	@Column(name = "isactive")
	private int isActive;

	@JsonIgnore
	@Column(name = "createdby", updatable = false)
	private int createdBy;

	@JsonIgnore
	@Column(name = "modifiedby")
	private int modifiedBy;

	@JsonIgnore
	@Column(name = "createddate", updatable = false)
	private Date createdDate;

	@JsonIgnore
	@Column(name = "modifieddate")
	private Date modifiedDate;

}
