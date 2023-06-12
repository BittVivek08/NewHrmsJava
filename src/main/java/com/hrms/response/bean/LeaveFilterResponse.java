package com.hrms.response.bean;

import lombok.Data;

@Data
public class LeaveFilterResponse {

   private String emp_id; 
  private  String name;
   private String leaveType;
   private String reason;
   private String fromDate;
   private String toDate;
   private float days;
   private float availabelDays;
   private String reportingManagerId;
   private int year;
   private int month;
   
   
   
	
	
	
}
