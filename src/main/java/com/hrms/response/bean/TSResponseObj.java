package com.hrms.response.bean;

import java.util.List;

import lombok.Data;

@Data
public class TSResponseObj 
{
	boolean status;
	String message;	
	
	List<TSResponseEmployeeName> timeSheetListForRM;
	Object timeSheetCountResponse=null;
}