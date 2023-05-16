package com.hrms.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
@Table(name = "main_jobtitles")
public class JobTitlesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "jobtitlecode")
    private String jobTitleCode;

	@Column(name = "jobtitlename")
	private String jobTitleName;

	@Column(name = "jobdescription")
	private String jobDescription;

	@Column(name = "minexperiencerequired")
	private String minExpReq;

	@Column(name = "jobpaygradecode")
	private String jobPayGradeCode;

	@Column(name = "jobpayfrequency")
	private String jobPayFrequency;

	@Column(name = "createdby", updatable = false)
	private int createdBy;

	@Column(name = "modifiedby")
	private int modifiedBy;

	@JsonIgnore
	@Column(name = "createddate", updatable = false)
	private Date createdDate;

	@JsonIgnore
	@Column(name = "modifieddate")
	private Date modifiedDate;

	@JsonIgnore
	@Column(name = "isactive")
	private int isActive;
}
