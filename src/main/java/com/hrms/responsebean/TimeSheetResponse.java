package com.hrms.responsebean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class TimeSheetResponse {
     String msg;
     boolean status;
}