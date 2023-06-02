package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.entity.SaveTimeSheet;
import com.hrms.response.bean.TimeSheetResponse;
import com.hrms.serviceImpl.TimeSheetDetailsImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/TimeSheet")
public class HrmsEmpTimeSheetController {
	@Autowired
	private TimeSheetDetailsImpl impl;

	@PostMapping("/timeSheetDetails")
	public ResponseEntity<TimeSheetResponse> saveTimeSheett(@RequestBody List<SaveTimeSheet> timesheet) {
		log.info("entered into saveTimeSheet method of HrmsEmpTimeSheetController class");
		try {
			TimeSheetResponse saveTimeSheett1 = this.impl.saveTimeSheett(timesheet);
			return new ResponseEntity<>(saveTimeSheett1, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/timeSheetDetails")
	public ResponseEntity<TimeSheetResponse> getTimeSheetDetails(@RequestBody List<SaveTimeSheet> timesheet) {
		log.info("entered into getTimeSheetDetails method of HrmsEmpTimeSheetController class");
		try {
			TimeSheetResponse saveTimeSheett1 = this.impl.saveTimeSheett(timesheet);
			return new ResponseEntity<>(saveTimeSheett1, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/timeSheetDetails")
	public ResponseEntity<TimeSheetResponse> updateTimeSheetDetails(@RequestBody List<SaveTimeSheet> timesheet) {
		log.info("entered into updateTimeSheetDetails method of HrmsEmpTimeSheetController class");
		try {
			TimeSheetResponse saveTimeSheett1 = this.impl.saveTimeSheett(timesheet);
			return new ResponseEntity<>(saveTimeSheett1, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}