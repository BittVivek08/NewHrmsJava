package com.hrms.response.bean;

import org.springframework.stereotype.Component;

import com.hrms.entity.SaveTimeSheet;

import lombok.Data;

@Data
@Component
public class TimeSheetResponseForMonthYearWeek {
	String message;
		
	SaveTimeSheet saveTimeSheet;
	
	
}
