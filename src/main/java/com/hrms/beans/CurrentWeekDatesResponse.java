package com.hrms.beans;

import lombok.Data;

@Data
public class CurrentWeekDatesResponse {

	private int year;
	private int month;
	private int monthlyWeek;
	private int calweek;
	private String currentWeek;
	private String previousWeek;
	private String nextWeek;
	private String monDate;
	private String tueDate;
	private String wedDate;
	private String thuDate;
	private String friDate;
	private String satDate;
	private String sunDate;
}