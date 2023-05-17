package com.hrms.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name="main_holidaydates")
public class HolidayCalenderEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDate date;
	
	@Column(name = "description")
	private String holidaydescription;
	
	private String holidayname;
	
	@Column(name="holidayyear")
	private int holidayyear;
	
	private int groupid;
	
	//@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "India/Mumbai")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Kolkata")
	//private Timestamp createddate;
	
	private LocalDateTime createddate;
	
	//@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "India/Mumbai")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Kolkata")
	
	private LocalDateTime modifieddate;
	
	private int isactive;
	
	private int createdby;
	
	private int modifiedby;

	
	
	
	
	
}
