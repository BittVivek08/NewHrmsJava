package com.hrms.response.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hrms.entity.SaveTimeSheet;

import lombok.Data;

@Data
@Component
public class TimeSheetResponseForMonthYearWeek {
	String message;
		
	List<SaveTimeSheet> saveTimeSheet;
	List<Object[]> list;

	
	
	
}
