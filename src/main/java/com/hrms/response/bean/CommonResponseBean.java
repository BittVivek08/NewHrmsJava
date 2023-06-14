package com.hrms.response.bean;

import java.util.List;

import com.hrms.entity.EmployeeLeaveRequestSummaryEntity;

import com.hrms.entity.Privileges;

import lombok.Data;

@Data
public class CommonResponseBean {
	private String message;
	private boolean status;
//	private Object list;
//	private Object privilleges;
	private List<EmployeeLeaveRequestSummaryEntity> list;
	private List<Privileges> privilleges;
	
}
