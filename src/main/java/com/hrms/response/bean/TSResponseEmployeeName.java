package com.hrms.response.bean;

import java.util.Date;

import lombok.Data;
@Data
public class TSResponseEmployeeName 
{  private int userId;
	private String employeeName;
	private int repManId;
	private Short weekNo;
	private Date startDate;
	private Date endDate;
	private Double totalHour;
	private String totalHours;
	private String status;
	private Short calWeek;
	private Double monTotalHours;
	private Date monDate;
	private int monday;
	private Double tueTotalHours;
	private Date tueDate;
	private int tuesday;
	private Double wedTotalHours;
	private Date wedDate;
	private int wednesday;
	private Double thursTotalHours;
	private Date thursDate;
	private int thrusday;
	private Double friTotalHours;
	private Date friDate;
	private int friday;
	private Double satTotalHours;
	private Date satDate;
	private int saturday;
	private Double sunTotalHours;
	private Date sunDate;
	private int sunday;
	private String TotalWeekHours;
	private String monthName;
	private int year;
	private short month;
}