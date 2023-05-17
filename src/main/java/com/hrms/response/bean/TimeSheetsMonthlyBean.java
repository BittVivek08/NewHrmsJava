package com.hrms.response.bean;

import java.util.Date;
import java.util.List;

import com.hrms.entity.SaveTimeSheet;

import lombok.Data;

@Data
public class TimeSheetsMonthlyBean {
	private int userId;
	private String employeeId;
	private String employeeName;
	private int repManId;
	private String reportingManagerName;
	private String startDate;
	private String endDate;
	private int calWeek;
	private int month;
	private int year;
	private Date monDate;
	private float monTotalHours;
	private int monday;
	private Date tueDate;
	private float tueTotalHours;
	private int tuesday;
	private Date wedDate;
	private float wedTotalHours;
	private int wednesday;
	private Date thursDate;
	private float thursTotalHours;
	private int thrusday;
	private Date friDate;
	private float friTotalHours;
	private int friday;
	private Date satDate;
	private float satTotalHours;
	private int saturday;
	private Date sunDate;
	private float sunTotalHours;
	private int sunday;
	private String monthName;
	private float totalHour;
	private String status;
	private String approverName;
	private String rejectReason;
	List<SaveTimeSheet> timeSheetListForEmployee;
}
