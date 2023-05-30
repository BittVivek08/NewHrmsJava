package com.hrms.response.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class TSResponseObj 
{
	boolean status;
	String message;	
	
	List<TSResponseEmployeeName> timeSheetListForRM;
	Object timeSheetCountResponse=null;
}