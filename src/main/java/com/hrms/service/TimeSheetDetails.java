package com.hrms.service;


import java.util.List;

import com.hrms.entity.SaveTimeSheet;
import com.hrms.response.bean.TimeSheetResponse;

public interface TimeSheetDetails {

	public TimeSheetResponse saveTimeSheett(List<SaveTimeSheet>  hrmss);

}