package com.hrms.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.ws.rs.QueryParam;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.hrms.beans.EntityBeanResponse;
//import com.hrms.beans.SaveTimesheetRequestBean;
import com.hrms.entity.SaveTimeSheet;
import com.hrms.repository.HolidayCalenderRepository;
import com.hrms.responsebean.TimeSheetResponseForMonth;
import com.hrms.responsebean.TimeSheetResponseForMonthYearWeek;
import com.hrms.serviceImpl.TimeSheetDetailsImpl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HrmsEmpTimeSheetController {
	@Autowired
	private TimeSheetDetailsImpl impl;
	@Autowired
	private HolidayCalenderRepository holidayCalenderRepository;
	@PostMapping("/saveTimeSheett")
	public ResponseEntity<TimeSheetResponseForMonthYearWeek> saveTimeSheett(@RequestBody SaveTimeSheet timesheet ) {
		log.info("entered into saveTimeSheet method of HrmsEmpTimeSheetController class");
		try {
		
			TimeSheetResponseForMonthYearWeek saveTimeSheett1 = this.impl.saveTimeSheett(timesheet);
			return  new ResponseEntity<>(saveTimeSheett1,HttpStatus.OK);
		
		}catch(Exception e) {
			e.printStackTrace();
			return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);


		}
	} 
	@DeleteMapping("/DeleteTimeSheet")
	public ResponseEntity<String> deleteTimeSheetById(@QueryParam("id")int id) {
		
		log.info("entered into DeleteTimeSheet method of HrmsEmpTimeSheetController class");
		try { 
			this.impl.deleteTimeSheet(id);
			return new ResponseEntity<>("Successfully Deleted TimeSheet", HttpStatus.ACCEPTED);
		}catch(Exception e) {
			return  new ResponseEntity<>("user not present in data base",HttpStatus.NOT_FOUND);
		}
	} 
	@GetMapping("/getTimeSheetbyId")
	public ResponseEntity<SaveTimeSheet> getDetail(@QueryParam("id") int id) {
		log.info("entered into getTimeSheetbyId method of HrmsEmpTimeSheetController class");
		try {
			SaveTimeSheet  timeSheet= impl.getTimeSheetDetail(id);
			return new ResponseEntity<SaveTimeSheet>(timeSheet, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<SaveTimeSheet>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/UpdateTimeSheetData")
	public ResponseEntity<String> updateTimeSheet(@RequestBody SaveTimeSheet timesheet ,@QueryParam("id") int id) {
		log.info("entered into UpdateTimeSheetData method of HrmsEmpTimeSheetController class");
		try {
			this.impl.updateTimeSheet(timesheet,id);
			return new ResponseEntity<>("Successfully Updated data this id: "+id, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>("data not updated ",HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/getDetailUsingMonYearWeek")
	public ResponseEntity<TimeSheetResponseForMonthYearWeek> getEmployeeDetailByMonYearandWeek(@QueryParam("month")int month ,@QueryParam("year")int year,@QueryParam("calweek") int calweek,@QueryParam("id") int id) {
		log.info("entered into getDetailUsingMonYearWeek method of HrmsEmpTimeSheetController class");
		TimeSheetResponseForMonthYearWeek response =  new TimeSheetResponseForMonthYearWeek();
		SaveTimeSheet timesheet=impl.getTimeSheetDetails(month,year,calweek,id);
		if(timesheet!=null) {
			response.setMessage("timesheet detail retrived successfully");
			response.setSaveTimeSheet(timesheet);
			return new ResponseEntity<TimeSheetResponseForMonthYearWeek>(response,HttpStatus.OK);
		}else {
			response.setMessage("timesheet Detail not retrived");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
	}


	@GetMapping("/getTimeSheetDetailUsingMon")
	public ResponseEntity<TimeSheetResponseForMonth> getEmployeeDetailByMonYearandWeek(@QueryParam("month")int month,@QueryParam("empId") int empId ,@QueryParam("year") int year) {
		log.info("entered into getTimeSheetDetailUsingMon method of HrmsEmpTimeSheetController class");
		TimeSheetResponseForMonth response =  new TimeSheetResponseForMonth();
			List<Object[]> timesheet=impl.getTimeSheetDetailsByMonth(month,empId,year);
			if(timesheet !=null) {
				response.setMessage("timesheet detail retrived successfully");
				response.setList(timesheet);
				return new ResponseEntity<TimeSheetResponseForMonth>(response,HttpStatus.OK);
			}else {
				response.setMessage("timesheet detail not retrived");
				return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
			}	
	}

	//	   @GetMapping("/getEmployeesByReportingManagerId")
	//	   public ResponseEntity<List<SaveTimeSheet>> getEmpByRepID(@QueryParam("repId") int repId){
	//		   List<SaveTimeSheet> ob=impl.getEmpByReportingId(repId);
	//		   return new ResponseEntity<List<SaveTimeSheet>>(ob,HttpStatus.OK);
	//		   
	//	   }

	//	    @PostMapping("/timeSheetApproval")
	//	    public ResponseEntity<TimeSheetApprovalStatus> timeSheetApproval(@QueryParam("empId") int empid){
	//	    	TimeSheetApprovalStatus timesheet=impl.timeSheetApproval(empid);
	//	    	if(timesheet!=null) {
	//	    	return new ResponseEntity<TimeSheetApprovalStatus>(timesheet,HttpStatus.ACCEPTED)  ; 
	//	    	}else {
	//	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	//	    	}
	//	    }

}

