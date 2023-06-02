package com.hrms.response.bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.hrms.entity.SaveTimeSheet;

import lombok.Data;
@Data
public class TSResponseEmployeeName 
{  private Integer userId;
	private String employeeName;
	private String repManId;
	private Integer weekNo;
	private Date startDate;
	private Date endDate;
	private Double totalHour;
	private String totalHours;
	private String status;
	private Integer calWeek;
	private Double monTotalHours;
	private Date monDate;
	private Integer monday;
	private Double tueTotalHours;
	private Date tueDate;
	private Integer tuesday;
	private Double wedTotalHours;
	private Date wedDate;
	private Integer wednesday;
	private Double thursTotalHours;
	private Date thursDate;
	private Integer thrusday;
	private Double friTotalHours;
	private Date friDate;
	private Integer friday;
	private Double satTotalHours;
	private Date satDate;
	private Integer saturday;
	private Double sunTotalHours;
	private Date sunDate;
	private Integer sunday;
	private String TotalWeekHours;
	private String monthName;
	private Integer year;
	private Integer month;
	List<SaveTimeSheet> timeSheetListForEmployee;
	Set<SaveTimeSheetResponseClass> timeSheetListForEmployees;
	
	
}