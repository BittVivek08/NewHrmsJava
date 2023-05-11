package com.hrms.responsebean;

import org.springframework.stereotype.Component;

import com.hrms.entity.SaveTimeSheet;

import lombok.Data;

@Data
@Component
public class TimeSheetResponseForMonthYearWeek {
	String message;
		
	SaveTimeSheet saveTimeSheet;
	
	
}
