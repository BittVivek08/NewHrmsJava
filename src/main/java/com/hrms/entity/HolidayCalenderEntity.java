package com.hrms.entity;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



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
	
	private String holidayDescription;
	
	private String holidayName;
	
}
