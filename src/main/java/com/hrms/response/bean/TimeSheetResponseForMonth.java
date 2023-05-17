package com.hrms.response.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hrms.entity.SaveTimeSheet;

import lombok.Data;

@Data
public class TimeSheetResponseForMonth {
	String message;
	
	List<Object[]> list;	
}
