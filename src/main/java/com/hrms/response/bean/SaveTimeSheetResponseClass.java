package com.hrms.response.bean;

import java.util.Date;

import lombok.Data;

@Data
public class SaveTimeSheetResponseClass {
	private Integer saveId;

	private Integer employeeId;

	private Integer clientId;

	private String clientName;

	private Integer projectId;

	private String projectName;

	private Integer projectTaskId;

	private String projectTaskName;

	private Integer year;

	private Integer month;

	private Integer weekNo;

	private Integer cal_week;

	private String mon_hours;

	private Date mon_date;
	private Integer monday;

	private String tue_hours;

	private Date tue_date;
	private Integer tuesday;

	private String wed_hours;

	private Date wed_Date;
	private Integer wednesday;

	private String thurs_hours;
	private Integer thrusday;
	private Date thurs_Date;

	private String fri_hours;
	private Integer friday;
	private Date fri_Date;

	private String sat_hours;
	private Integer saturday;
	private Date sat_Date;

	private String sun_hours;
	private Integer sunday;
	private Date sun_Date;
	private String status;
}